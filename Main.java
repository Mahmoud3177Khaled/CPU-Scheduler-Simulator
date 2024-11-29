import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 0, 8, 2)); // Process ID, Arrival Time, Burst Time, Priority
        processes.add(new Process(2, 1, 4, 1));
        processes.add(new Process(3, 2, 9, 3));
        processes.add(new Process(4, 3, 5, 0));

        // Use SJF Scheduler
        Scheduler scheduler = new ShortestJobFirstScheduler();
        scheduler.schedule(processes);

        // Calculate times after scheduling
        ShortestJobFirstScheduler sjfScheduler = (ShortestJobFirstScheduler) scheduler;
        List<Process> completedProcesses = sjfScheduler.getCompletedProcesses();
        MetricsCalculator.calculateTimes(completedProcesses);

        // Print results
        ResultPrinter.printResults(completedProcesses);
        ResultPrinter.printMetrics(completedProcesses);
    }
}
