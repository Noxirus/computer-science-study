package Creational.Singleton;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private Map<String, Object> settings = new HashMap<>();
    private static ConfigManager instance = new ConfigManager();

    private ConfigManager(){}

    public static ConfigManager getInstance() {
        return instance;
    }

    public void set(String key, Object value){
        settings.put(key, value);
    }

    public Object get(String key){
        return settings.get(key);
    }
}
// There are times where we only ever want one of an object, this is that case

        /*
        ConfigManager manager = ConfigManager.getInstance();
        manager.set("name", "Hammy Ham");


        ConfigManager other = ConfigManager.getInstance();
        System.out.println(manager.get("name"));

         */