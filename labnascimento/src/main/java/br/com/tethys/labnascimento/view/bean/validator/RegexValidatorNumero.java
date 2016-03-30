package br.com.tethys.labnascimento.view.bean.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "regexValidatorNumero")
public class RegexValidatorNumero implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if(value == null){
			return;
		}
		
		String valor = (String) value;
		
		String regex = (String) component.getAttributes().get("regex");
		
		if(regex == null){
			return;
		}
		
		String label = (String) component.getAttributes().get("label");
		
		if(label == null){
			label = component.getClientId();
		}
		
		if(!isValid(regex,valor)){
			String mensagem = "";
			valor = valor.trim();
			
			if (valor.equals("")) {
				mensagem = "O preenchimento do campo <strong>%s</strong> é obrigatório.";
			}else{
				mensagem = "O campo <strong>%s</strong> possui caracteres inválidos.";
			}
			
			mensagem = String.format(mensagem, label);
			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
			throw new ValidatorException(fm);
		}
	}
	
	public boolean isValid(String regex, String value){
		return value.matches(regex);
	}
}
