package com.alphahotel.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by ValdoR on 2019-12-11.
 */

public class JSFMessageUtil {
    public void sendInfoMessage(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
        addMessageToJsfContext(facesMessage);
    }

    public void sendErrorMessage(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_ERROR, message);
        addMessageToJsfContext(facesMessage);
    }

    private FacesMessage createMessage(FacesMessage.Severity severity, String errorMessage) {
        return new FacesMessage(severity, errorMessage, errorMessage);
    }

    private void addMessageToJsfContext(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
