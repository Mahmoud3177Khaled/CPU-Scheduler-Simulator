import java.awt.Color;
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
        int contextSwitch;

        System.out.println("         ----------------------------");
        System.out.println("        |  CPU Schedulers Simulator  |");
        System.out.println("         ----------------------------");
        System.out.println();
        System.out.println("Choose the Scheduling algorithm: ");
        System.out.println("    1-Non-preemptive Priority Scheduling.");
        System.out.println("    2-Non-Preemptive Shortest- Job First.");
        System.out.println("    3-Shortest- Remaining Time First Scheduling.");
        System.out.println("    4-FCAI Scheduling.");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1 -> {
                scheduler = new PriorityScheduler();
                System.out.print("Enter the number of processes: ");
                numberOfProcesses = input.nextInt();
                System.out.println(
                        "Enter the processes in form(name - Arrival time - Burst time - Priority): ");
                while (numberOfProcesses != 0) {
                    name = input.next();
                    arrivalTime = input.nextInt();
                    burstTime = input.nextInt();
                    priority = input.nextInt();
                    processes.add(new Process(name, arrivalTime, burstTime, priority, Color.black));
                    numberOfProcesses--;
                }
                scheduler.schedule(processes);
                List<processPeriod> periods = ((PriorityScheduler) scheduler).getProcessPeriods();
                System.out.println("\n--------------------------");
                System.out.println("Processes execution order |");
                System.out.println("--------------------------");
                int time =periods.get(0).process.arrivalTime;
                for (processPeriod processPeriod : periods) {
                    System.out.println(
                            time + " --> " + (time + processPeriod.period) + " - " + processPeriod.process.name);
                    time += processPeriod.period;
                }
                List<Process> executedProcesses = scheduler.getCompletedProcesses();
                // MetricsCalculator.calculateTimes(executedProcesses);
                System.out.println("\n--------------------------------------------------");
                System.out.println("Waiting Time and Turnaround Time for each process |");
                System.out.println("--------------------------------------------------");
                ResultPrinter.printResults(executedProcesses);
                System.out.println("\n--------------------------------------------");
                System.out.println("Average Waiting Time and Average Turnaround |");
                System.out.println("--------------------------------------------");
                ResultPrinter.printMetrics(executedProcesses);

            }
            case 2 -> {
                scheduler = new ShortestJobFirstScheduler();
                System.out.print("Enter the number of processes: ");
                numberOfProcesses = input.nextInt();
                System.out.println(
                        "Enter the processes in form(name - Arrival time - Burst time): ");
                while (numberOfProcesses != 0) {
                    name = input.next();
                    arrivalTime = input.nextInt();
                    burstTime = input.nextInt();
                    processes.add(new Process(name, arrivalTime, burstTime, Color.black));
                    numberOfProcesses--;
                }
                scheduler.schedule(processes);
                List<processPeriod> periods = ((ShortestJobFirstScheduler) scheduler).getProcessPeriods();
                System.out.println("\n--------------------------");
                System.out.println("Processes execution order |");
                System.out.println("--------------------------");
                int time =periods.get(0).process.arrivalTime;
                for (processPeriod processPeriod : periods) {
                    System.out.println(
                            time + " --> " + (time + processPeriod.period) + " - " + processPeriod.process.name);
                    time += processPeriod.period;
                }
                List<Process> executedProcesses = scheduler.getCompletedProcesses();
                MetricsCalculator.calculateTimes(executedProcesses);
                System.out.println("\n--------------------------------------------------");
                System.out.println("Waiting Time and Turnaround Time for each process |");
                System.out.println("--------------------------------------------------");
                ResultPrinter.printResults(executedProcesses);
                System.out.println("\n--------------------------------------------");
                System.out.println("Average Waiting Time and Average Turnaround |");
                System.out.println("--------------------------------------------");
                ResultPrinter.printMetrics(executedProcesses);
            }
            case 3 -> {
                scheduler = new ShortestRemainingTimeFirst();
                System.out.print("Enter the number of processes: ");
                numberOfProcesses = input.nextInt();
                System.out.println(
                        "Enter the processes in form(name - Arrival time - Burst time): ");
                while (numberOfProcesses != 0) {
                    name = input.next();
                    arrivalTime = input.nextInt();
                    burstTime = input.nextInt();
                    processes.add(new Process(name, arrivalTime, burstTime, Color.black));
                    numberOfProcesses--;
                }
                System.out.print("Enter the context switch time: ");
                contextSwitch = input.nextInt();
                ((ShortestRemainingTimeFirst) scheduler).setContextSwitch(contextSwitch);
                scheduler.schedule(processes);
                List<processPeriod> periods = ((ShortestRemainingTimeFirst) scheduler).getProcessPeriods();
                System.out.println("\n--------------------------");
                System.out.println("Processes execution order |");
                System.out.println("--------------------------");
                int time =periods.get(0).process.arrivalTime;
                for (processPeriod processPeriod : periods) {
                    System.out.println(
                            time + " --> " + (time + processPeriod.period) + " - " + processPeriod.process.name);
                    time += processPeriod.period;
                }
                List<Process> executedProcesses = scheduler.getCompletedProcesses();
                // MetricsCalculator.calculateTimes(executedProcesses);
                System.out.println("\n--------------------------------------------------");
                System.out.println("Waiting Time and Turnaround Time for each process |");
                System.out.println("--------------------------------------------------");
                ResultPrinter.printResults(executedProcesses);
                System.out.println("\n--------------------------------------------");
                System.out.println("Average Waiting Time and Average Turnaround |");
                System.out.println("--------------------------------------------");
                ResultPrinter.printMetrics(executedProcesses);
            }
            case 4 -> {
                scheduler = new FCAIScheduler();
                System.out.print("Enter the number of processes: ");
                numberOfProcesses = input.nextInt();
                System.out.println(
                        "Enter the processes in form(name - Burst time - Arrival time - Priority - Quantum): ");
                while (numberOfProcesses != 0) {
                    name = input.next();
                    burstTime = input.nextInt();
                    arrivalTime = input.nextInt();
                    priority = input.nextInt();
                    quantum = input.nextInt();
                    processes.add(new FCAIProcess(name, arrivalTime, burstTime, priority, quantum, Color.black));
                    numberOfProcesses--;
                }
                scheduler.schedule(processes);
                List<processPeriod> periods = ((FCAIScheduler) scheduler).getProcessPeriods();
                System.out.println("\n--------------------------");
                System.out.println("Processes execution order |");
                System.out.println("--------------------------");
                int time =periods.get(0).process.arrivalTime;
                for (processPeriod processPeriod : periods) {
                    System.out.println(
                            time + " --> " + (time + processPeriod.period) + " - " + processPeriod.process.name);
                    time += processPeriod.period;
                }
                List<Process> executedProcesses = scheduler.getCompletedProcesses();
                MetricsCalculator.calculateTimes(executedProcesses);
                System.out.println("\n--------------------------------------------------");
                System.out.println("Waiting Time and Turnaround Time for each process |");
                System.out.println("--------------------------------------------------");
                ResultPrinter.printResults(executedProcesses);
                System.out.println("\n--------------------------------------------");
                System.out.println("Average Waiting Time and Average Turnaround |");
                System.out.println("--------------------------------------------");
                ResultPrinter.printMetrics(executedProcesses);
                System.out.println("\n-----------------------------------------------------");
                System.out.println("all history update of quantum time for each process: |");
                System.out.println("-----------------------------------------------------");
                ResultPrinter.printQuantumUpdates(executedProcesses);
            }
            default -> throw new AssertionError();
        }
    }
}