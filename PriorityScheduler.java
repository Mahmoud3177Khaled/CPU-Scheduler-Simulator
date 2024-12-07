import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityScheduler implements Scheduler {
    private List<Process> completedProcesses = new ArrayList<>();

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
                // Simulate execution
                currentTime += nextProcess.burstTime; // Process runs to completion
                nextProcess.completionTime = currentTime;
                nextProcess.turnaroundTime = nextProcess.completionTime - nextProcess.arrivalTime;
                nextProcess.waitingTime = nextProcess.turnaroundTime - nextProcess.burstTime;

                // Remove the executed process from the list
                completedProcesses.add(nextProcess);
                processes.remove(nextProcess);
            } else {
                // No process ready to execute, just advance time
                currentTime++;
            }
        }
    }
    public List<Process> getCompletedProcesses() {
        return completedProcesses;
    }
}
