public class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int priority; /// for priority scheduling
    int remainingTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;
    int effectiveBurstTime;

    public Process(String name, int arrivalTime, int burstTime) {
        this(name, arrivalTime, burstTime, 0);
    }

    public Process(String name, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.effectiveBurstTime = burstTime;
    }
}
