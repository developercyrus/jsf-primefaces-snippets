package javaee.jsf.primefaces.example1.input.editor;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
 
@ManagedBean(name = "EditorBean")
public class EditorBean {
 
    private String value = "Hello World";
 
    public String getValue() {
        return value;
    }
 
    public void setValue(String value) {
        this.value = value;
    }
    
    public void printId(ActionEvent event){
    	List<UIComponent> lists = event.getComponent().getParent().getChildren();
    	for (UIComponent u : lists) {
    		System.out.println(u.getClientId());
    		if (u.getClientId().equals("form:editor")) {
    			System.out.println("by ActionListener: " + u.getAttributes().get("value"));
    		}
    	}
	}
    
    public String goTo() {
		return "result";
	}
}
