package br.com.tethys.labnascimento.view.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

import com.sun.faces.component.visit.FullVisitContext;

@ManagedBean
@RequestScoped
public class MenuBean {
		
	
	@PostConstruct
	public void init(){
		System.out.println( " HEHEHE ");
		
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = context.getViewRoot();
		final String id = "menuPrincipal";
		final UIComponent[] found = new UIComponent[1];
		
		viewRoot.visitTree(new FullVisitContext(context), new VisitCallback() {     
	        @Override
	        public VisitResult visit(VisitContext context, UIComponent component) {
	            if(component.getId() != null && component.getId().equals(id)){
	                found[0] = component;
	                return VisitResult.COMPLETE;
	            }
	            return VisitResult.ACCEPT;              
	        }
	    });
		System.out.println( found[0] );
		
	}
	
	
}
