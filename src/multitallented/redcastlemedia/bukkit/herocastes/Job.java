package multitallented.redcastlemedia.bukkit.herocastes;

import java.util.HashSet;

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
    private HashSet<String> whitelist;
    private HashSet<String> blacklist;
    
    public Job(String employer, String employee, double salary, double raise, int repeatsRemaining, String target, String type) {
        this.employer = employer;
        this.employee = employee;
        this.salary = salary;
        this.raise = raise;
        this.repeatsRemaining = repeatsRemaining;
        this.target = target;
        this.type = type;
    }
    public void getBlackList(HashSet<String> input) {
        this.blacklist= input;
    }
    
    public HashSet<String> getBlackList() {
        return blacklist;
    }
    
    public void setWhiteList(HashSet<String> input) {
        this.whitelist=input;
    }
    
    public HashSet<String> getWhiteList() {
        return whitelist;
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
