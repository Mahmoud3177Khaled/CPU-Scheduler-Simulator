import java.util.List;

public class MetricsCalculator {
    public static void calculateTimes(List<Process> processes) {
        for (Process p : processes) {
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
        }
    }
}
