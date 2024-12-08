import java.awt.*;
import java.util.*;
import java.util.List;


public class ShortestRemainingTimeFirst implements Scheduler {



    private List<processPeriod> completedProcesses = new ArrayList<>();
    private List<Process> executedProcesses = new ArrayList<>();
    Process cs = new Process("cs",0,0, Color.gray);
    int contextSwitch = 0;


    public void setContextSwitch(int contextSwitch) {
        this.contextSwitch = contextSwitch;
    }

    public void schedule(List<Process> processes){

        processPeriod contextPeriod = new processPeriod (cs,contextSwitch);

        executedProcesses.addAll(processes);

        int currentTime = 0;
        Process currentProcess = null;
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(process -> process.remainingTime));
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        while (!readyQueue.isEmpty() || !processes.isEmpty() || currentProcess != null) {

            if(!processes.isEmpty()) {
                while (processes.getFirst().arrivalTime <= currentTime) {
                    readyQueue.add(processes.getFirst());
                    processes.removeFirst();
                    if (processes.isEmpty()){break;}
                }
            }

            if (currentProcess == null) {
                currentProcess = readyQueue.poll();
                processPeriod processPeriod1 = new processPeriod (currentProcess, 1);
                completedProcesses.add(processPeriod1);
            }
            else {
                if(readyQueue.peek() != null) {
                    if (currentProcess.remainingTime > readyQueue.peek().remainingTime) {
                        readyQueue.add(currentProcess);
                        currentProcess = readyQueue.poll();


                        processPeriod processPeriod1 = new processPeriod (currentProcess, 1);
                        completedProcesses.add(contextPeriod);
                        completedProcesses.add(processPeriod1);
                        currentTime+= contextSwitch;

                    }
                    else{
                        completedProcesses.getLast().period++;
                    }
                }
                else if( currentProcess != null ){
                    completedProcesses.getLast().period++;
                }
            }

            currentProcess.remainingTime--;

            currentTime++;
            if(currentProcess.remainingTime == 0) {
                currentProcess.turnaroundTime = currentTime -  currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
                currentProcess.completionTime = currentTime;
                currentProcess = null;
                completedProcesses.add(contextPeriod);
                currentTime+= contextSwitch;
            }


        }
    }


    @Override
    public List<Process> getCompletedProcesses() {return executedProcesses;
    }

    public List<processPeriod> getProcessPeriods() {
        completedProcesses.removeLast();
        return completedProcesses;
    }
}

