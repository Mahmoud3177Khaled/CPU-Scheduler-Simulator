class FCAIProcess extends Process{
    int quantum;
    int FCAIFactor;
    int startPeriod;
    public FCAIProcess(String name,int arrivalTime , int burstTime, int priority,int quantum){
        super(name,arrivalTime,burstTime,priority);
        this.quantum = quantum;
        this.startPeriod = 0;
    }
}