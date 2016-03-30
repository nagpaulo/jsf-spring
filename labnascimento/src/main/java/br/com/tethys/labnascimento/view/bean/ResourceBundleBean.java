package br.com.tethys.labnascimento.view.bean;

import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@SuppressWarnings("rawtypes")
@Lazy
@Component("msgs")
public class ResourceBundleBean extends HashMap{
	
	private static final long serialVersionUID = -8702453609254356349L;
	
	@Autowired
    private MessageSource messageSource;
	
    @Override
    public String get(Object key) {
        ServletRequest request = (ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String message;
        try {
            message = messageSource.getMessage((String) key, null, request.getLocale());
        } catch (NoSuchMessageException e) {
            message = "???" + key + "???";
        }
        return message;
    }

}
