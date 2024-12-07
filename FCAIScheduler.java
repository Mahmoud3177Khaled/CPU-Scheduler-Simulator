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

    private Process getHigherProcess(ArrayList<Process> arr) {
        FCAIProcess high = (FCAIProcess) arr.get(0);
        FCAIProcess p;
        for (int i = 1; i < arr.size(); i++) {
            p = (FCAIProcess) arr.get(i);
            if (p.FCAIFactor < high.FCAIFactor) {
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
            while (!arrivalTimePQ.isEmpty() && arrivalTimePQ.peek().arrivalTime == time) {
                waitingList.add(arrivalTimePQ.poll());
            }
            if (currentProcess == null) { // if CPU is empty
                if (!waitingList.isEmpty()) {
                    currentProcess = (FCAIProcess) waitingList.get(0);
                    waitingList.remove(0);
                    currentProcess.startPeriod = time;
                    currentProcess.remainingTime = currentProcess.remainingTime - 1;
                    calcFCAIFactor(currentProcess);
                }
            } else {// there is a process is executing
                if (currentProcess.remainingTime == 0) { // the process is completed
                    currentProcess.completionTime = time;
                    processPeriods.add(new processPeriod(currentProcess, time - currentProcess.startPeriod));
                    executedProcesses.add(currentProcess);
                    if (!waitingList.isEmpty()) {
                        currentProcess = (FCAIProcess) waitingList.get(0);
                        waitingList.remove(0);
                        currentProcess.startPeriod = time;
                        calcFCAIFactor(currentProcess);
                        currentProcess.remainingTime = currentProcess.remainingTime - 1;
                    } else {
                        currentProcess = null;
                    }
                } else if (currentProcess.quantum == (time - currentProcess.startPeriod)) {// the process is completed his quantum
                    currentProcess.quantum += 2;
                    currentProcess.quantumUpdates
                            .add(new quantumUpdate(currentProcess.quantum - 2, currentProcess.quantum, time));
                    FCAIProcess temp = currentProcess;
                    if (!waitingList.isEmpty()) {
                        currentProcess = (FCAIProcess) waitingList.get(0);
                        waitingList.remove(0);
                        currentProcess.startPeriod = time;
                        currentProcess.remainingTime = currentProcess.remainingTime - 1;
                        calcFCAIFactor(currentProcess);
                    } else {
                        currentProcess = null;
                    }
                    processPeriods.add(new processPeriod(temp, time - temp.startPeriod));
                    calcFCAIFactor(temp);
                    waitingList.add(temp);
                } else if ((time - currentProcess.startPeriod) >= Math.ceil((40 * currentProcess.quantum)) / 100) { // the process exceeded his non-preemptive time
                    if (!waitingList.isEmpty()) {
                        FCAIProcess p = (FCAIProcess) getHigherProcess(waitingList);
                        if (p.FCAIFactor <= currentProcess.FCAIFactor) {
                            int add = (currentProcess.quantum - (time - currentProcess.startPeriod));
                            currentProcess.quantumUpdates
                                    .add(new quantumUpdate(currentProcess.quantum, currentProcess.quantum + add, time));
                            currentProcess.quantum += add;
                            calcFCAIFactor(currentProcess);
                            processPeriods.add(new processPeriod(currentProcess, time - currentProcess.startPeriod));
                            FCAIProcess temp = (FCAIProcess) currentProcess;
                            currentProcess = p;
                            waitingList.remove(p);
                            currentProcess.startPeriod = time;
                            currentProcess.remainingTime = currentProcess.remainingTime - 1;
                            calcFCAIFactor(temp);
                            waitingList.add(temp);
                        } else {
                            currentProcess.remainingTime = currentProcess.remainingTime - 1;
                            calcFCAIFactor(currentProcess);
                        }
                    } else {
                        currentProcess.remainingTime = currentProcess.remainingTime - 1;
                        calcFCAIFactor(currentProcess);
                    }
                } else {
                    currentProcess.remainingTime = currentProcess.remainingTime - 1;
                    calcFCAIFactor(currentProcess);
                }
            }
            time++;
        }
        while (currentProcess != null && currentProcess.remainingTime > 0) {
            time++;
            currentProcess.remainingTime--;
            if (currentProcess.remainingTime == 0) {
                currentProcess.completionTime = time;
                processPeriods.add(new processPeriod(currentProcess, time - currentProcess.startPeriod));
                executedProcesses.add(currentProcess);
            }
        }

    }

    @Override
    public List<Process> getCompletedProcesses() {
        return executedProcesses;
    }

    public List<processPeriod> getProcessPeriods() {
        return processPeriods;
    }

}
