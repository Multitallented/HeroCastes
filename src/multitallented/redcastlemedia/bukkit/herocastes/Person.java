package multitallented.redcastlemedia.bukkit.herocastes;

import java.util.Set;

/**
 *
 * @author Multitallented
 */
public class Person {
    //private String type;
    private Set<Job> jobs;
    private final String name;
    
    public Person(String name, Set<Job> jobs) {
        this.name = name;
        this.jobs = jobs;
    }
    
    public Set<Job> getJobs() {
        return jobs;
    }
    
    public String getName() {
        return name;
    }
    //No longer requiring players to be a certain caste
    /*public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }*/
}
