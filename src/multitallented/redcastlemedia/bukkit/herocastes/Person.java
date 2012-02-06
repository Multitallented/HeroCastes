package multitallented.redcastlemedia.bukkit.herocastes;

import java.util.Set;

/**
 *
 * @author Multitallented
 */
public class Person {
    //private String type;
    private Set<Job> jobs;
    
    public Person(Set<Job> jobs) {
        //this.type = type;
        this.jobs = jobs;
    }
    
    public Set<Job> getJobs() {
        return jobs;
    }
    
    //No longer requiring players to be a certain caste
    /*public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }*/
}
