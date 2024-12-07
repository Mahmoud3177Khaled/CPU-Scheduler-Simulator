import java.util.List;

public interface Scheduler {
    void schedule(List<Process> processes);
    List<Process> getCompletedProcesses();
}
