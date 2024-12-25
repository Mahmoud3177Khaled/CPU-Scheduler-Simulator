import java.awt.*;
import java.util.*;
import java.util.List;

public class ShortestRemainingTimeFirst implements Scheduler {

    private List<processPeriod> completedProcesses = new ArrayList<>();
    private List<Process> executedProcesses = new ArrayList<>();
    Process cs = new Process("cs", 0, 0, Color.gray);
    int contextSwitch = 0;

    public void setContextSwitch(int contextSwitch) {
        this.contextSwitch = contextSwitch;
    }

    public void schedule(List<Process> processes) {
        processPeriod contextPeriod = new processPeriod(cs, contextSwitch);

        executedProcesses.addAll(processes);

        int currentTime = 0;
        Process currentProcess = null;
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(
                Comparator.comparingInt(process -> process.remainingTime));
        Map<Process, Integer> waitingTimeMap = new HashMap<>();

        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        while (!readyQueue.isEmpty() || !processes.isEmpty() || currentProcess != null) {
            // Add arriving processes to the ready queue
            while (!processes.isEmpty() && processes.get(0).arrivalTime <= currentTime) {
                Process arrivingProcess = processes.remove(0);
                readyQueue.add(arrivingProcess);
                waitingTimeMap.put(arrivingProcess, 0); // Initialize waiting time
            }

            // Update waiting times for processes in the ready queue
            for (Process p : readyQueue) {
                waitingTimeMap.put(p, waitingTimeMap.get(p) + 1);

                // Apply aging: boost priority if waiting time exceeds threshold
                if (waitingTimeMap.get(p) > 10) { // Example threshold
                    p.remainingTime = Math.max(1, p.remainingTime - 1);
                }
            }

            // If no current process, fetch the next process
            if (currentProcess == null && !readyQueue.isEmpty()) {
                currentProcess = readyQueue.poll();
                completedProcesses.add(new processPeriod(currentProcess, 1));
            } else if (currentProcess != null) {
                if (!readyQueue.isEmpty() && currentProcess.remainingTime > readyQueue.peek().remainingTime) {
                    readyQueue.add(currentProcess);
                    currentProcess = readyQueue.poll();
                    completedProcesses.add(contextPeriod);
                    completedProcesses.add(new processPeriod(currentProcess, 1));
                    currentTime += contextSwitch;
                } else {
                    completedProcesses.get(completedProcesses.size() - 1).period++;
                }
            }

            // Process execution
            if (currentProcess != null) {
                currentProcess.remainingTime--;
            }
            currentTime++;

            // Process completion
            if (currentProcess != null && currentProcess.remainingTime == 0) {
                currentProcess.turnaroundTime = currentTime - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
                currentProcess.completionTime = currentTime;
                currentProcess = null;
                completedProcesses.add(contextPeriod);
                currentTime += contextSwitch;
            }
        }
    }

    @Override
    public List<Process> getCompletedProcesses() {
        return executedProcesses;
    }

    public List<processPeriod> getProcessPeriods() {
        completedProcesses.removeLast();
        return completedProcesses;
    }
}
