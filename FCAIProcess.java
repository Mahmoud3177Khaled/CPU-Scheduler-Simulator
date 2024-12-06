
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
class FCAIProcess extends Process{
    int quantum;
    int FCAIFactor;
    int startPeriod;
    List<quantumUpdate> quantumUpdates;
    public FCAIProcess(String name,int arrivalTime , int burstTime, int priority,int quantum ,Color color){
        super(name,arrivalTime,burstTime,priority,color);
        this.quantum = quantum;
        this.startPeriod = 0;
        this.quantumUpdates = new ArrayList<>();
    }
}