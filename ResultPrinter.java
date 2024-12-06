import java.util.List;

public class ResultPrinter {
    public static void printResults(List<Process> processes) {
        for (Process p : processes) {
            System.out.println("Process " + p.name + " - Waiting Time: " + p.waitingTime
                    + ", Turnaround Time: " + p.turnaroundTime);
        }
    }

    public static void printQuantumUpdates(List<Process> processes) {
        for (Process p : processes) {
            System.out.println(p.name + ": ");
            if (((FCAIProcess) p).quantumUpdates.isEmpty()) {
                System.out.println("  the quantum don't changed.");
            } else {
                for (int i = 0; i < ((FCAIProcess) p).quantumUpdates.size(); i++) {
                    System.out.println("  in " + ((FCAIProcess) p).quantumUpdates.get(i).time + " time, the quantum: "
                            + ((FCAIProcess) p).quantumUpdates.get(i).prevQuantum
                            + " --> " + ((FCAIProcess) p).quantumUpdates.get(i).currentQuantum);

                }
            }
        }
    }

    public static void printMetrics(List<Process> processes) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Process p : processes) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }

        int n = processes.size();
        System.out.println("Average Waiting Time: " + (totalWaitingTime / (float) n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / (float) n));
    }
}
