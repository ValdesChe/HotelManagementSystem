package com.alphahotel.controller;

import com.alphahotel.utils.FacesContextUtil;
import com.alphahotel.utils.JSFMessageUtil;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ValdoR on 2019-12-12.
 */
public class AbstractController {
    private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

    public AbstractController() {
        super();
    }

    protected void displayErrorMessage(String message){
        JSFMessageUtil jsfmu = new JSFMessageUtil();
        jsfmu.sendErrorMessage(message);
    }

    protected void displayInfoMessage(String message){
        JSFMessageUtil jsfmu = new JSFMessageUtil();
        jsfmu.sendInfoMessage(message);
    }



}
