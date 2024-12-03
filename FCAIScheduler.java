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
        // 1- make comparator
        Comparator<Process> arrivalTimeComparator = (p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime);
        Comparator<Process> FCAIFactorComparator = (p1, p2) -> {
            FCAIProcess a = (FCAIProcess) p1;
            FCAIProcess b = (FCAIProcess) p2;
            return Integer.compare(a.FCAIFactor, b.FCAIFactor);
        };
        // 2- make two priority queue
        PriorityQueue<Process> arrivalTimePQ = new PriorityQueue<>(arrivalTimeComparator);
        PriorityQueue<Process> FCAIFactorPQ = new PriorityQueue<>(FCAIFactorComparator);
        // 3- calculate v1 and v2
        calcV1_V2(processes);
        // 4- calculate FCAI factor and to arrival queue
        for (int i = 0; i < processes.size(); i++) {
            calcFCAIFactor((FCAIProcess) processes.get(i));
            arrivalTimePQ.add(processes.get(i));
        }
        time = 0;
        FCAIProcess currentProcess = null;
        // while there is process will be come or is waiting
        while (!arrivalTimePQ.isEmpty() || !FCAIFactorPQ.isEmpty()) {
            System.out.println("------------------------------------------------------");
            System.out.println(time + " --> " + (time + 1));
            // while there are processes come in this time
            while (!arrivalTimePQ.isEmpty() && arrivalTimePQ.peek().arrivalTime == time) {
                System.out.println(arrivalTimePQ.peek().name + " arrived");
                FCAIFactorPQ.add(arrivalTimePQ.poll());
            }
            if (currentProcess == null) { // if CPU is empty
                // System.out.println(time+ " " + currentProcess);
                if (!FCAIFactorPQ.isEmpty()) {
                    currentProcess = (FCAIProcess) FCAIFactorPQ.poll();
                    currentProcess.startPeriod = time;
                    processesExecutionOrderList.add(currentProcess.name);
                    System.out.println(currentProcess.name + " running");
                    currentProcess.remainingTime = currentProcess.remainingTime -1;
                } else {
                    processesExecutionOrderList.add("empty");
                    System.out.println("empty");
                }
            } else {// there is a process is executing
                if (currentProcess.remainingTime == 1) { // the process is completed
                    currentProcess.completionTime = time;
                    System.out.println(currentProcess.name +" --> completed");
                    // FCAIProcess temp = currentProcess;
                    if (!FCAIFactorPQ.isEmpty()) {
                        if(time == 10){
                            FCAIProcess p = (FCAIProcess) FCAIFactorPQ.poll();
                            FCAIProcess p2 = (FCAIProcess) FCAIFactorPQ.peek();
                            System.out.println(p.name +" "+p.FCAIFactor);
                            System.out.println(p2.FCAIFactor);
                            FCAIFactorPQ.add(p);
                            
                        }
                        currentProcess = (FCAIProcess) FCAIFactorPQ.poll();
                        currentProcess.startPeriod = time;
                        currentProcess.remainingTime = currentProcess.remainingTime -1;
                        processesExecutionOrderList.add(currentProcess.name);
                    } else {
                        currentProcess = null;
                        processesExecutionOrderList.add("empty");
                    }
                    // //System.out.println(currentProcess.name);
                    // System.out.println(time +currentProcess.name+"2");
                    // currentProcess.remainingTime = currentProcess.remainingTime -1;
                    // currentProcess = null;
                } else if (currentProcess.quantum == (time - currentProcess.startPeriod)) {// the process is completed
                                                                                           // his quantum
                    currentProcess.quantum += 2;
                    // currentProcess.remainingTime = currentProcess.remainingTime -1;
                    FCAIProcess temp = currentProcess;
                    if (!FCAIFactorPQ.isEmpty()) {
                        currentProcess = (FCAIProcess) FCAIFactorPQ.poll();
                        currentProcess.startPeriod = time;
                        processesExecutionOrderList.add(currentProcess.name);
                        currentProcess.remainingTime = currentProcess.remainingTime -1;
                        System.out.println(currentProcess.name + " "+ currentProcess.FCAIFactor +" replace "+temp.name +" "+temp.FCAIFactor);
                    } else {
                        currentProcess = null;
                    }
                    System.out.println("factor before: "+temp.FCAIFactor);
                    calcFCAIFactor(temp);
                    System.out.println("factor after: "+temp.FCAIFactor);
                    FCAIFactorPQ.add(temp);
                    // System.out.println(time +temp.name+"3");
                }
                else if((time - currentProcess.startPeriod) >= ((40 *currentProcess.quantum))/100){ //the process exceeded his non-preemptive time
                    System.out.println("time " + (40 *currentProcess.quantum)/100);
                    if(!FCAIFactorPQ.isEmpty()){
                        // System.out.println(555);
                        if(time == 8){
                            FCAIProcess p = (FCAIProcess) FCAIFactorPQ.poll();
                            FCAIProcess p2 = (FCAIProcess) FCAIFactorPQ.peek();
                            System.out.println(p.FCAIFactor);
                            System.out.println(p2.FCAIFactor);
                            FCAIFactorPQ.add(p);
                            
                        }
                        FCAIProcess p = (FCAIProcess) FCAIFactorPQ.peek();
                        if(p.FCAIFactor < currentProcess.FCAIFactor){
                            System.out.println("factor "+ p.FCAIFactor+" "+currentProcess.FCAIFactor);
                            currentProcess.quantum += (currentProcess.quantum -(time-currentProcess.startPeriod));
                            System.out.println("factor before: "+currentProcess.FCAIFactor);
                            calcFCAIFactor(currentProcess);
                            System.out.println("factor after: "+currentProcess.FCAIFactor);
                            FCAIProcess temp = (FCAIProcess) currentProcess;
                            currentProcess = (FCAIProcess)FCAIFactorPQ.poll();
                            currentProcess.startPeriod = time;
                            currentProcess.remainingTime = currentProcess.remainingTime -1;
                            FCAIFactorPQ.add(temp);
                        }
                        else{
                            currentProcess.remainingTime = currentProcess.remainingTime -1;
                        }
                // System.out.println(p.FCAIFactor);
                // System.out.println(currentProcess.FCAIFactor);
                // if(p.FCAIFactor < currentProcess.FCAIFactor){
                // System.out.println(444);
                // currentProcess.remainingTime -= (time -currentProcess.startPeriod);
                // FCAIFactorPQ.add(currentProcess);
                // currentProcess = (FCAIProcess)FCAIFactorPQ.poll();
                // processesExecutionOrderList.add(currentProcess.name);
                // System.out.println(time + currentProcess.name + " 8");
                // currentProcess.startPeriod = time;
                // }
                }else{
                    currentProcess.remainingTime = currentProcess.remainingTime -1;
                }
                }
                else {
                    currentProcess.remainingTime = currentProcess.remainingTime -1;
                }
            }
            // System.out.println(time + " "+currentProcess +" 9");
            time++;
            if (currentProcess != null) {
                System.out.println(currentProcess.name + " is running " + currentProcess.remainingTime);
            } else {
                System.out.println("is empty");
            }
            // if(time == 40){
            //     break;
            // }
        }
    }

}
