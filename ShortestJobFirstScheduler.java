import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShortestJobFirstScheduler implements Scheduler {
    private List<Process> completedProcesses = new ArrayList<>();
    private List<processPeriod> processPeriods = new ArrayList<>();
    private static final int AGING_INTERVAL = 5;
    private static final int AGING_BURST_REDUCTION = 1;

    @Override
    public void schedule(List<Process> processes) {
        // Sort processes by arrival time
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0;

        while (!processes.isEmpty()) {
            // Apply aging to processes
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime && (currentTime - p.arrivalTime) % AGING_INTERVAL == 0) {
                    p.burstTime = Math.max(1, p.burstTime - AGING_BURST_REDUCTION);

                }
            }

            Process nextProcess = null;

            for (Process p : processes) {
                if (p.arrivalTime <= currentTime) {
                    if (nextProcess == null || p.burstTime < nextProcess.burstTime) {
                        nextProcess = p;
                    }
                }
            }

            if (nextProcess != null) {
                // Execute the process
                int startTime = currentTime;
                currentTime += nextProcess.burstTime;
                nextProcess.completionTime = currentTime;
                nextProcess.turnaroundTime = nextProcess.completionTime - nextProcess.arrivalTime;
                nextProcess.waitingTime = nextProcess.turnaroundTime - nextProcess.burstTime;

                int period = nextProcess.burstTime;
                processPeriods.add(new processPeriod(nextProcess, period));

                completedProcesses.add(nextProcess);
                processes.remove(nextProcess);
            } else {
                currentTime++;
            }
        }
    }

    @Override
    public List<Process> getCompletedProcesses() {
        return completedProcesses;
    }

    public List<processPeriod> getProcessPeriods() {
        return processPeriods;
    }
}