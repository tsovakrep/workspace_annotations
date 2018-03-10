package by.htp.itacademy.controller.app;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {
	protected Map<String, Class<?>> map;
	
    public ControllerFactory() {
        map = defaultMap();
    }
    
    public Controller create (String controllerName) {
        Class ControllerClass = (Class) map.get(controllerName);
        if (ControllerClass == null) 
            throw new RuntimeException(getClass() + " was unable to find an controller named '" + controllerName + "'.");
        Controller controllerInstance = null;
        try {
            controllerInstance = (Controller) ControllerClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controllerInstance;
    }
        
    protected Map<String, Class<?>> defaultMap() {
        Map<String, Class<?>> map = new HashMap<String, Class<?>>();    
        map.put("index", Index.class);
        return map;
    }

}
