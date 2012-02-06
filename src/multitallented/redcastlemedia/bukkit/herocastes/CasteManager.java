package multitallented.redcastlemedia.bukkit.herocastes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Multitallented
 */
public class CasteManager {
    
    private Map<String, Person> persons = new HashMap<String, Person>();
    
    public Person getPerson(String name) {
        return persons.get(name);
    }
    
    public void setCaste(String name, String type) {
        persons.get(name).setType(type);
        //TODO write code to save to file
    }
    
}
