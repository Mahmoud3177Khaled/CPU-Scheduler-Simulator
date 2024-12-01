import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FCAIScheduler implements Scheduler {
    private double  V1;
    private double  V2;
    private int time;
    private void calcV1_V2(List<Process> processes) {
        int lastArrivalTime = processes.get(0).arrivalTime;
        int maxBurstTime =processes.get(0).burstTime;
        for (Process process : processes) {
            if (process.arrivalTime > lastArrivalTime) {
                lastArrivalTime = process.arrivalTime;
            }
            if (process.burstTime > maxBurstTime) {
                maxBurstTime = process.burstTime;
            }
        }
        V1 = lastArrivalTime /10.0;
        V2 =  maxBurstTime /10.0;

    }
    private void calcFCAIFactor(Process process){
            process.FCAIFactor =(int)((10 - process.priority) + Math.ceil(process.arrivalTime / V1) +Math.ceil(process.remainingTime / V2));
    }

    @Override
    public void schedule(List<Process> processes) {
        Comparator<Process> arrivalTimeComparator = (p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime);
        Comparator<Process> FCAIFactorComparator = (p1, p2) -> Integer.compare(p2.FCAIFactor, p1.FCAIFactor);
        PriorityQueue<Process> arrivalTimePQ = new PriorityQueue<>(arrivalTimeComparator);
        PriorityQueue<Process> FCAIFactorPQ = new PriorityQueue<>(FCAIFactorComparator);
        calcV1_V2(processes);
        while (!processes.isEmpty()){
            calcFCAIFactor(processes.get(0));
            arrivalTimePQ.add(processes.get(0));
            processes.remove(0);
        }

        time = 0;
        
        while (!arrivalTimePQ.isEmpty() || !FCAIFactorPQ.isEmpty()) { 
            while(arrivalTimePQ.element().arrivalTime == time){
                FCAIFactorPQ.add(arrivalTimePQ.poll());
            }
            // Process p = FCAIFactorPQ.poll();
            // System.out.println( p.name+" "+p.FCAIFactor);
            time++;
        }
    }

}
