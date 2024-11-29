import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShortestJobFirstScheduler implements Scheduler {
    private List<Process> completedProcesses = new ArrayList<>();

    @Override
    public void schedule(List<Process> processes) {
        // Sort processes by arrival time
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0;

        while (!processes.isEmpty()) {
            Process nextProcess = null;

            // Find the process with the shortest burst time among ready processes
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime) {
                    if (nextProcess == null || p.burstTime < nextProcess.burstTime) {
                        nextProcess = p;
                    }
                }
            }

            if (nextProcess != null) {
                // Execute the process
                currentTime += nextProcess.burstTime;
                nextProcess.completionTime = currentTime;
                nextProcess.turnaroundTime = nextProcess.completionTime - nextProcess.arrivalTime;
                nextProcess.waitingTime = nextProcess.turnaroundTime - nextProcess.burstTime;

                // Move the process to completed list
                completedProcesses.add(nextProcess);
                processes.remove(nextProcess);
            } else {
                // No process ready, advance time
                currentTime++;
            }
        }
    }

    public List<Process> getCompletedProcesses() {
        return completedProcesses;
    }
}
