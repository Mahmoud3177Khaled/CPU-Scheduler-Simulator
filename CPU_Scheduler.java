import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CPU_Scheduler {
    List<Process> processes = new ArrayList<>();
    List<Process> completedProcesses = new ArrayList<>();
    List<Process> readyQueue = new ArrayList<>();

    public void priorityScheduling() {
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

    public void sjfScheduling() {
        // Sort processes by arrival time
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;

        // Execute until all processes are completed
        while (!processes.isEmpty()) {
            // Filter processes that have arrived
            List<Process> readyQueue = new ArrayList<>();
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime) {
                    readyQueue.add(p);
                }
            }

            if (!readyQueue.isEmpty()) {
                // Select the process with the shortest burst time
                readyQueue.sort(Comparator.comparingInt(p -> p.burstTime));
                Process nextProcess = readyQueue.get(0);

                // Execute the selected process
                currentTime += nextProcess.burstTime;
                nextProcess.completionTime = currentTime;
                nextProcess.turnaroundTime = nextProcess.completionTime - nextProcess.arrivalTime;
                nextProcess.waitingTime = nextProcess.turnaroundTime - nextProcess.burstTime;

                // Move the process to the completed list and remove from the main list
                completedProcesses.add(nextProcess);
                processes.remove(nextProcess);
            } else {
                // If no process is ready, advance time
                currentTime++;
            }
        }
    }

    public void srtfScheduling() {
        Collections.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));
        // Execute process with preemption
    }

    public void calculateTimes() {
        for (Process p : processes) {
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
        }
    }

    // Calculate average waiting time and turnaround time
    public void calculateMetrics() {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Process p : completedProcesses) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }

        int n = completedProcesses.size();
        System.out.println("Average Waiting Time: " + (totalWaitingTime / (float) n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / (float) n));
    }

    // Print results for each process
    public void printResults() {
        for (Process p : completedProcesses) {
            System.out.println("Process " + p.id + " - Waiting Time: " + p.waitingTime
                    + ", Turnaround Time: " + p.turnaroundTime + ", Completion Time: " + p.completionTime);
        }
    }
}