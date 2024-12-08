import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityScheduler implements Scheduler {
    private List<Process> completedProcesses = new ArrayList<>();
    private List<processPeriod> processPeriods = new ArrayList<>();
    private int CONTEXT_SWITCH_TIME = 2; // Context switch time in time units
    public void setContextSwitch(int contextSwitch) {
        this.CONTEXT_SWITCH_TIME = contextSwitch;
    }

    @Override
    public void schedule(List<Process> processes) {
        // Sort by arrival time first, then by priority
        processes.sort(Comparator.comparingInt((Process p) -> p.arrivalTime)
                .thenComparingInt(p -> p.priority));

        int currentTime = 0;

        // Start executing processes
        while (!processes.isEmpty()) {
            // Find the next process with the highest priority (lowest priority value)
            Process nextProcess = null;
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime) {
                    if (nextProcess == null || p.priority < nextProcess.priority) {
                        nextProcess = p;
                    }
                }
            }

            if (nextProcess != null) {
                // Record the start period for the process
                int startPeriod = currentTime;

                // Simulate execution
                currentTime += nextProcess.burstTime; // Process runs to completion
                nextProcess.completionTime = currentTime;
                nextProcess.turnaroundTime = nextProcess.completionTime - nextProcess.arrivalTime;
                nextProcess.waitingTime = nextProcess.turnaroundTime - nextProcess.burstTime;

                // Record the process execution period
                processPeriods.add(new processPeriod(nextProcess, currentTime - startPeriod));

                // Remove the executed process from the list
                completedProcesses.add(nextProcess);
                processes.remove(nextProcess);

                // Add context switch time if there are still processes left
                if (!processes.isEmpty()) {
                    processPeriods.add(new processPeriod(new Process("CS",0,0, Color.gray), CONTEXT_SWITCH_TIME));
                    currentTime += CONTEXT_SWITCH_TIME;
                }
            } else {
                // No process ready to execute, just advance time
                currentTime++;
            }
        }
    }

    public List<Process> getCompletedProcesses() {
        return completedProcesses;
    }

    public List<processPeriod> getProcessPeriods() {
        return processPeriods;
    }
}