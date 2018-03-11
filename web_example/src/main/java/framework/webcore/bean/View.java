package framework.webcore.bean;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class View {
	
	private String path;
    private Map<String, Object> data;
    
    public View(String path) {
        this.path = path;
    }
    
    public boolean isRedirect() {
        return this.path.startsWith("/");
    }
}
