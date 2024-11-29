import java.util.List;

public class ResultPrinter {
    public static void printResults(List<Process> processes) {
        for (Process p : processes) {
            System.out.println("Process " + p.id + " - Waiting Time: " + p.waitingTime
                    + ", Turnaround Time: " + p.turnaroundTime + ", Completion Time: " + p.completionTime);
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
