import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FCAIScheduler implements Scheduler {
    private double V1;
    private double V2;
    private int time;
    private List<Process> executedProcesses = new ArrayList<>();
    private List<processPeriod> processPeriods = new ArrayList<>();

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
    private Process getHigherProcess(ArrayList<Process> arr){
        FCAIProcess high =(FCAIProcess)arr.get(0);
        FCAIProcess p ;
        for (int i = 1; i < arr.size(); i++) {
            p = (FCAIProcess) arr.get(i);
            if(p.FCAIFactor < high.FCAIFactor){
                high = p;
            }
        }
        return high;
    }

    @Override
    public void schedule(List<Process> processes) {
        // 1- make comparator
        Comparator<Process> arrivalTimeComparator = (p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime);
        // 2- make a priority queue and waiting list
        PriorityQueue<Process> arrivalTimePQ = new PriorityQueue<>(arrivalTimeComparator);
        ArrayList<Process> waitingList = new ArrayList<>();
        // 3- calculate v1 and v2
        calcV1_V2(processes);
        // 4- calculate FCAI factor and add to arrival queue
        for (int i = 0; i < processes.size(); i++) {
            calcFCAIFactor((FCAIProcess) processes.get(i));
            arrivalTimePQ.add(processes.get(i));
        }
        time = 0;
        FCAIProcess currentProcess = null;
        // while there is process will be come or is waiting
        while (!arrivalTimePQ.isEmpty() || !waitingList.isEmpty()) {
           // System.out.println("------------------------------------------------------");
            //System.out.println(time + " --> " + (time + 1));
            // while there are processes come in this time
            while (!arrivalTimePQ.isEmpty() && arrivalTimePQ.peek().arrivalTime == time) {
                // System.out.println(arrivalTimePQ.peek().name + " arrived");
                waitingList.add(arrivalTimePQ.poll());
            }
            if (currentProcess == null) { // if CPU is empty
                // System.out.println(time+ " " + currentProcess);
                if (!waitingList.isEmpty()) {
                    currentProcess = (FCAIProcess) waitingList.get(0);//-----------------------------
                    waitingList.remove(0);
                    currentProcess.startPeriod = time;
                    // processesExecutionOrderList.add(currentProcess.name);
                    //System.out.println(currentProcess.name + " running");
                    currentProcess.remainingTime = currentProcess.remainingTime -1;
                    calcFCAIFactor(currentProcess);
                 } //else {
                //    // processesExecutionOrderList.add("empty");
                //     System.out.println("empty");
                // }
            } else {// there is a process is executing
                if (currentProcess.remainingTime == 0) { // the process is completed
                    currentProcess.completionTime = time;
                    processPeriods.add(new processPeriod(currentProcess,time-currentProcess.startPeriod));
                    executedProcesses.add(currentProcess);
                   // System.out.println(currentProcess.name +" --> completed");
                    // FCAIProcess temp = currentProcess;
                    if (!waitingList.isEmpty()) {
                        currentProcess = (FCAIProcess) waitingList.get(0);
                        waitingList.remove(0);
                        currentProcess.startPeriod = time;
                        calcFCAIFactor(currentProcess);
                        currentProcess.remainingTime = currentProcess.remainingTime -1;
                        //processesExecutionOrderList.add(currentProcess.name);
                    } else {
                        currentProcess = null;
                        //processesExecutionOrderList.add("empty");
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
                    if (!waitingList.isEmpty()) {
                        currentProcess = (FCAIProcess) waitingList.get(0);
                        waitingList.remove(0);
                        currentProcess.startPeriod = time;
                        //processesExecutionOrderList.add(currentProcess.name);
                        currentProcess.remainingTime = currentProcess.remainingTime -1;
                        //System.out.println(currentProcess.name + " "+ currentProcess.FCAIFactor +" replace "+temp.name +" "+temp.FCAIFactor);
                        calcFCAIFactor(currentProcess);
                    } else {
                       currentProcess = null;
                   }
                    processPeriods.add(new processPeriod(temp,time-temp.startPeriod));
                   // System.out.println("factor before: "+temp.FCAIFactor);
                    calcFCAIFactor(temp);
                    //System.out.println("factor after: "+temp.FCAIFactor);
                    waitingList.add(temp);
                    // System.out.println(time +temp.name+"3");
                }
                else if((time - currentProcess.startPeriod) >= Math.ceil((40 *currentProcess.quantum))/100){ //the process exceeded his non-preemptive time
                    System.out.println("time " + (40 *currentProcess.quantum)/100);
                    if(!waitingList.isEmpty()){
                        FCAIProcess p = (FCAIProcess) getHigherProcess(waitingList);
                        if(p.FCAIFactor <= currentProcess.FCAIFactor){
                            //System.out.println("factor "+ p.FCAIFactor+" "+currentProcess.FCAIFactor);
                            currentProcess.quantum += (currentProcess.quantum -(time-currentProcess.startPeriod));
                            //System.out.println("factor before: "+currentProcess.FCAIFactor);
                            calcFCAIFactor(currentProcess);
                           // System.out.println("factor after: "+currentProcess.FCAIFactor);
                           processPeriods.add(new processPeriod(currentProcess,time-currentProcess.startPeriod));
                           FCAIProcess temp = (FCAIProcess) currentProcess;
                            currentProcess = p;
                            waitingList.remove(p);
                            currentProcess.startPeriod = time;
                            currentProcess.remainingTime = currentProcess.remainingTime -1;
                            calcFCAIFactor(temp);
                            waitingList.add(temp);
                        }
                        else{
                            currentProcess.remainingTime = currentProcess.remainingTime -1;
                            calcFCAIFactor(currentProcess);
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
                    calcFCAIFactor(currentProcess);
                }
                }
                else {
                    currentProcess.remainingTime = currentProcess.remainingTime -1;
                    calcFCAIFactor(currentProcess);
                }
            }
            // System.out.println(time + " "+currentProcess +" 9");
            time++;
            if (currentProcess != null) {
                //calcFCAIFactor(currentProcess);
                System.out.println(currentProcess.name + " is running " + currentProcess.remainingTime);
            } else {
                System.out.println("is empty");
            }
            // if(time == 40){
            //     break;
            // }
        }
        System.out.println(currentProcess.remainingTime);
        while(currentProcess != null && currentProcess.remainingTime > 0){
            System.out.println("---------------------------");
            System.out.println(time + " --> " +(time+1));
            System.out.println(currentProcess.name);
            time++;
            currentProcess.remainingTime--;
            if(currentProcess.remainingTime == 0){
                currentProcess.completionTime = time;
                    processPeriods.add(new processPeriod(currentProcess,time-currentProcess.startPeriod));
                    executedProcesses.add(currentProcess);
            }
        }

    }
    public List<Process> getExecutedProcesses(){
        return executedProcesses;
    }
    public List<processPeriod> getProcessPeriods(){
        return processPeriods;
    }

}
