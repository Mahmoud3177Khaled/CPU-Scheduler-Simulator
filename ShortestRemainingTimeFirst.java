import java.util.*;


public class ShortestRemainingTimeFirst implements Scheduler {



    private List<processPeriod> completedProcesses = new ArrayList<>();
    private List<Process> executedProcesses = new ArrayList<>();


    public void schedule(List<Process> processes){

        executedProcesses.addAll(processes);

        int currentTime = 0;
        Process currentProcess = null;
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(process -> process.remainingTime));
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        
        while (!readyQueue.isEmpty() || !processes.isEmpty() || currentProcess != null) {

            if(!processes.isEmpty()) {
                while (processes.getFirst().arrivalTime == currentTime) {
                    readyQueue.add(processes.getFirst());
                    processes.removeFirst();
                    if (processes.isEmpty()){break;}
                }
            }

            if (currentProcess == null) {
                currentProcess = readyQueue.poll();
            }
            else {
                if(readyQueue.peek() != null) {
                    if (currentProcess.remainingTime > readyQueue.peek().remainingTime) {
                        readyQueue.add(currentProcess);
                        currentProcess = readyQueue.poll();
                    }
                }
            }

            assert currentProcess != null;
            if(!completedProcesses.isEmpty()){
                if(Objects.equals(completedProcesses.getLast().process.name, currentProcess.name))
                completedProcesses.getLast().period++;
                else{
                    processPeriod processPeriod1 = new processPeriod (currentProcess, 1);
                    completedProcesses.add(processPeriod1);
                }
            }
            else{
                processPeriod processPeriod1 = new processPeriod (currentProcess, 1);
                completedProcesses.add(processPeriod1);
            }

            for (Process p : readyQueue) {
                p.waitingTime++;
            }

            currentProcess.remainingTime--;

            if(currentProcess.remainingTime == 0) {
                currentProcess.turnaroundTime = currentProcess.waitingTime+currentProcess.burstTime;
                currentProcess.completionTime = currentTime;
                currentProcess = null;
            }
            currentTime++;


        }

        for(processPeriod  i : completedProcesses){
            System.out.println("Process " + i.process.name + " - Time: " + i.period+ " waiting time : "+i.process.waitingTime);
        }

    }


    @Override
    public List<Process> getCompletedProcesses() {
        return executedProcesses;
    }


    public List<processPeriod> getProcessPeriods() {
        return completedProcesses;
    }
}

