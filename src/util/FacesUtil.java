package util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class FacesUtil {
	public static void addMessageInfo(String message){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}
	
	public static void addMessageError(String message){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}
	
	public static String getParam(String name){
		FacesContext facesContext 		= FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Map<String, String> queryParams = externalContext.getRequestParameterMap();
		
		return queryParams.get(name);
	}
}
