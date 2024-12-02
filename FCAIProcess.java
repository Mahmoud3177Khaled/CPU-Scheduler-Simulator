class FCAIProcess extends Process{
    int quantum;
    int FCAIFactor;
    int startPeriod;
    public FCAIProcess(String name, int burstTime, int arrivalTime, int priority,int quantum){
        super(name,burstTime,arrivalTime,priority);
        this.quantum = quantum;
        this.startPeriod = 0;
    }
}