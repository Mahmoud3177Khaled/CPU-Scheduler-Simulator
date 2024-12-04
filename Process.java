import java.awt.*;
public class Process {
    public String name;
    public int arrivalTime;
    public int burstTime;
    int priority; /// for priority scheduling
    int remainingTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;
    int effectiveBurstTime;
    Color color;

    public Process(String name, int arrivalTime, int burstTime) {
        this(name, arrivalTime, burstTime, 0, Color.black);
    }

    public Process(String name, int arrivalTime, int burstTime, Color color) {
        this(name, arrivalTime, burstTime, 0, color);
    }

    public Process(String name, int arrivalTime, int burstTime, int priority, Color color) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.effectiveBurstTime = burstTime;
        this.color = color;
    }
}
