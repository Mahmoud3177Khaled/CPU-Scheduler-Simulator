import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();
        Scheduler scheduler;
        int numberOfProcesses;
        String name;
        int burstTime;
        int arrivalTime;
        int priority;
        int quantum;

        System.out.println(" ----------------------------");
        System.out.println("|  CPU Schedulers Simulator  |");
        System.out.println(" ----------------------------");
        System.out.println();
        System.out.println("Choose the Scheduling algorithm: ");
        System.out.println("    1-Non-preemptive Priority Scheduling.");
        System.out.println("    2-Non-Preemptive Shortest- Job First.");
        System.out.println("    3-Shortest- Remaining Time First Scheduling.");
        System.out.println("    4-FCAI Scheduling.");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1 -> scheduler = new PriorityScheduler();
            case 2 -> scheduler = new ShortestJobFirstScheduler();
            case 4 -> {
                scheduler = new FCAIScheduler();
                System.out.print("Enter the number of processes: ");
                numberOfProcesses = input.nextInt();
                System.out.println("Enter the processes in form(name - Arrival time - Burst time - Priority - Quantum): ");
                while (numberOfProcesses != 0) { 
                    name = input.next();
                    burstTime = input.nextInt();
                    arrivalTime = input.nextInt();
                    priority = input.nextInt();
                    quantum = input.nextInt();
                    processes.add(new FCAIProcess(name,arrivalTime,burstTime,priority,quantum));
                    numberOfProcesses--;
                }
            }
            default -> throw new AssertionError();
        }
        scheduler.schedule(processes);
        List<processPeriod> periods =((FCAIScheduler) scheduler).getProcessPeriods();
        List<Process> executedProcesses =((FCAIScheduler) scheduler).getExecutedProcesses();
        for (processPeriod p : periods) {
            System.out.println(p.process.name +" "+p.period);
        }
        for (Process p : executedProcesses) {
            System.out.println(p.name +" "+p.completionTime);
        }
        MetricsCalculator.calculateTimes(executedProcesses);
        ResultPrinter.printResults(executedProcesses);
        ResultPrinter.printMetrics(executedProcesses);
        ResultPrinter.printQuantumUpdates(executedProcesses);
        //  // Calculate times after scheduling
        // ShortestJobFirstScheduler sjfScheduler = (ShortestJobFirstScheduler) scheduler;
        // List<Process> completedProcesses = sjfScheduler.getCompletedProcesses();
        // MetricsCalculator.calculateTimes(completedProcesses);
        // // Print results
        // ResultPrinter.printResults(completedProcesses);
        // ResultPrinter.printMetrics(completedProcesses);
    }
}
