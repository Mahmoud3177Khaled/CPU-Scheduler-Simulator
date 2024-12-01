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
            process.FCAIFactor =(10 - process.priority) + (int)(process.arrivalTime / V1) +(int)(process.remainingTime / V2);
    }

    @Override
    public void schedule(List<Process> processes) {
        Comparator<Process> FCAIFactorComparator = (p1, p2) -> Integer.compare(p2.FCAIFactor, p1.FCAIFactor);
        PriorityQueue<Process> pq = new PriorityQueue<>(FCAIFactorComparator);
        calcV1_V2(processes);
        while (!processes.isEmpty()){
            calcFCAIFactor(processes.get(0));
            pq.add(processes.get(0));
            processes.remove(0);
        }
        time = 0;
        while (!pq.isEmpty()) { 
            Process p = pq.poll();
            System.out.println( p.name+" "+p.FCAIFactor);
        }
    }

}
