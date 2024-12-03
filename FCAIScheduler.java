import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FCAIScheduler implements Scheduler {
    private double V1;
    private double V2;
    private int time;
    private List<String> processesExecutionOrderList = new ArrayList<>();

    private void calcV1_V2(List<Process> processes) {
        int lastArrivalTime = processes.get(0).arrivalTime;
        int maxBurstTime = processes.get(0).burstTime;
        for (Process process : processes) {
            if (process.arrivalTime > lastArrivalTime) {
                lastArrivalTime = process.arrivalTime;
            }
            if (process.burstTime > maxBurstTime) {
                maxBurstTime = process.burstTime;
            }
        }
        V1 = lastArrivalTime / 10.0;
        V2 = maxBurstTime / 10.0;

    }

    private void calcFCAIFactor(FCAIProcess process) {
        process.FCAIFactor = (int) ((10 - process.priority) + Math.ceil(process.arrivalTime / V1)
                + Math.ceil(process.remainingTime / V2));
    }

    @Override
    public void schedule(List<Process> processes) {
        //1- make comparator
        Comparator<Process> arrivalTimeComparator = (p1, p2) -> Integer.compare(p2.arrivalTime, p1.arrivalTime);
        Comparator<Process> FCAIFactorComparator = (p1, p2) -> {
            FCAIProcess a = (FCAIProcess) p1;
            FCAIProcess b = (FCAIProcess) p2;
            return Integer.compare(a.FCAIFactor, b.FCAIFactor);
        };
        //2- make two priority queue
        PriorityQueue<Process> arrivalTimePQ = new PriorityQueue<>(arrivalTimeComparator);
        PriorityQueue<Process> FCAIFactorPQ = new PriorityQueue<>(FCAIFactorComparator);
        //3- calculate v1 and v2
        calcV1_V2(processes);
        //4- calculate FCAI factor and to arrival queue
        for (int i = 0; i < processes.size(); i++ ){
            calcFCAIFactor((FCAIProcess) processes.get(i));
            arrivalTimePQ.add(processes.get(i));
        }
        time = 0;
        FCAIProcess currentProcess = null;
        //while there is process will be come or is waiting
        while (!arrivalTimePQ.isEmpty() || !FCAIFactorPQ.isEmpty()) {
            //while there are processes come in this time
            while (!arrivalTimePQ.isEmpty() && arrivalTimePQ.peek().arrivalTime == time) {
                FCAIFactorPQ.add(arrivalTimePQ.poll());
            }
            if(currentProcess == null){ // if CPU is empty 
                if(!FCAIFactorPQ.isEmpty()){
                    currentProcess = (FCAIProcess)FCAIFactorPQ.poll();
                    currentProcess.startPeriod = time;
                    processesExecutionOrderList.add(currentProcess.name);
                    System.out.println(currentProcess.name);
                    currentProcess.remainingTime--;
                }else{
                    processesExecutionOrderList.add("empty");
                    System.out.println("empty");
                }
            }else{//there is a process is executing
                if(currentProcess.remainingTime == 0){ //the process is completed
                    processesExecutionOrderList.add(currentProcess.name);
                    System.out.println(currentProcess.name);
                    currentProcess.completionTime= time;
                    currentProcess = null;
                }
                else if (currentProcess.quantum == (time-currentProcess.startPeriod)) {//the process is completed his quantum
                    currentProcess.quantum += 2;
                    FCAIFactorPQ.add(currentProcess);
                    currentProcess = null;
                }
                else if((time - currentProcess.startPeriod) >= ((40 * currentProcess.quantum))/100){ //the  process exceeded his non-preemptive time
                    calcFCAIFactor(currentProcess);
                    if(!FCAIFactorPQ.isEmpty()){
                        FCAIProcess p = (FCAIProcess)FCAIFactorPQ.peek();
                        if(p.FCAIFactor > currentProcess.FCAIFactor){
                            currentProcess.quantum += (currentProcess.quantum - (time-currentProcess.startPeriod));
                            FCAIFactorPQ.add(currentProcess);
                            currentProcess = (FCAIProcess)FCAIFactorPQ.poll();
                            processesExecutionOrderList.add(currentProcess.name);
                            System.out.println(currentProcess.name);
                            currentProcess.startPeriod = time;
                        }
                    }
                    currentProcess.remainingTime--;
                }
                else{
                    currentProcess.remainingTime--;
                }
            }
            time++;
        }
    }

}
