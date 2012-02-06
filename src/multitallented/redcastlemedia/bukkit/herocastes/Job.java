package multitallented.redcastlemedia.bukkit.herocastes;

/**
 *
 * @author Multitallented
 */
public class Job {
    private final String employer;
    private final double salary;
    private final double raise;
    private final int repeatsRemaining;
    private final String target;
    private final String type;
    private final String employee;
    
    public Job(String employer, String employee, double salary, double raise, int repeatsRemaining, String target, String type) {
        this.employer = employer;
        this.employee = employee;
        this.salary = salary;
        this.raise = raise;
        this.repeatsRemaining = repeatsRemaining;
        this.target = target;
        this.type = type;
    }
    
    public String getEmployer() {
        return employer;
    }
    
    public String getEmployee() {
        return employee;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public double getRaise() {
        return raise;
    }
    
    public int getRepeatsRemaining() {
        return repeatsRemaining;
    }
    
    public String getTarget() {
        return target;
    }
    
    public String getType() {
        return type;
    }
}
