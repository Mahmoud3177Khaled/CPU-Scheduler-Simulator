public class Process {
    int id;
    int arrivalTime;
    int burstTime;
    int priority; /// for priority scheduling
    int remainingTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;
    int effectiveBurstTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this(id, arrivalTime, burstTime, 0);
    }

    public Process(int id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.effectiveBurstTime = burstTime;
    }
}
