package multitallented.redcastlemedia.bukkit.herocastes;

import java.util.Set;

/**
 *
 * @author Multitallented
 */
public class Person {
    private String type;
    private Set<Job> jobs;
    
    public Person(String type, Set<Job> jobs) {
        this.type = type;
        this.jobs = jobs;
    }
    
    public Set<Job> getJobs() {
        return jobs;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
