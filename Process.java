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
    int quantum;
    int FCAIFactor;

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
    public Process(String name, int burstTime, int arrivalTime, int priority,int quantum) {
        this.name = name;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.effectiveBurstTime = burstTime;
        this.quantum = quantum;
        this.FCAIFactor = 0;
    }
}
