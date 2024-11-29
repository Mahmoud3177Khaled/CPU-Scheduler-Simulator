public class Main {
    public static void main(String[] args) {
        CPU_Scheduler scheduler = new CPU_Scheduler();

        // Add processes
        scheduler.processes.add(new Process(1, 0, 8, 2)); // Process ID, arrival time, burst time, priority
        scheduler.processes.add(new Process(2, 1, 4, 1));
        scheduler.processes.add(new Process(3, 2, 9, 3));
        scheduler.processes.add(new Process(4, 3, 5, 0));
        // Run scheduling algorithms
        // scheduler.priorityScheduling(); // For Priority Scheduling
        scheduler.sjfScheduling(); // For SJF Scheduling
        // scheduler.srtfScheduling(); // For SRTF Scheduling

        // Calculate times
        scheduler.calculateTimes();
        scheduler.printResults();
        scheduler.calculateMetrics();
    }
}
