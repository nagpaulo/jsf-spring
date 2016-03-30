/**
 * @author paulo.roberto
 * 
 */

$(function(){
	$('[data-toggle="tooltip"]').tooltip();	
	$.fn.upper = function(options){
		var config = {
		};
		var options = $.extend(config, options);
		$(this).keyup(function(){
			var posInicial = this.selectionStart;
			$(this).val($(this).val().toUpperCase());
			this.selectionStart = posInicial;
			this.selectionEnd = posInicial;
		})
		$(this).css( "text-transform", "uppercase" );
	}	
});

(function($){
	function TextMaxLength(element, options){
		this.element = element;
		this.elem = $(element);		
		var obj = this;
		this.config = {
		  maxLength:options
		}
		this.init = function (){
			this.elem.unbind('paste.text').bind('paste.text', function(e) {
			    // common browser -> e.originalEvent.clipboardData
			    // uncommon browser -> window.clipboardData
			    var clipboardData = e.clipboardData || e.originalEvent.clipboardData || window.clipboardData;
			    var pastedData = clipboardData.getData('text/plain');
			    var textarea = $(this);
			    var lengthPaste = pastedData.length;
			    var lengthTextArea = textarea.val().length;
			    if(lengthTextArea < obj.config.maxLength){
			    	var missing = obj.config.maxLength - lengthTextArea;
			      	if(lengthPaste <= missing){
			      		return true;
			      	}else{
			      		var newPaste = pastedData.substring(0,missing);
			      		textarea.val(textarea.val()+newPaste);
			      		return false;
			      	}
			    }else{
			      return false;
			    }
			});

			this.elem.unbind('keydown.text').bind('keydown.text', function(e){
				var componente = $(this);
				var vdc = (e.ctrlKey || e.which == 8 || e.which == 46);
				if(!vdc){
					if(componente.val().length >=  obj.config.maxLength){
						return false;
					}
				}
			})
		}
		this.init();		
	}

	$.fn.textmaxlength = function(options){
		return this.each(function(){			
			var element = $(this);
			if (element.data('textmaxlength')){//Se este elemento ja possui o plugin
				return ;	
			}
			var textmaxlength = new TextMaxLength(this, options);
			element.data('textmaxlength', textmaxlength);	
		});
	};
})($);

function scrollUpper(){
	setTimeout(function(){
		$(document).find("html, body").animate({ scrollTop: 0 }, "slow");
	}, 200);
}

function openModalDiscAtend(){
	$("#dialogAtendimento\\:professor_modal option").tooltip()
}

function numeroNota(campo, e){
	var tecla = window.event ? e.keyCode : e.which; 
	var valor = campo.value;
	//alert(tecla);
	if((tecla == 8) || (tecla == 0)){
		return true;	
	}	
	var selecionado = false;
	if(campo.selectionStart!= campo.selectionEnd){
		selecionado = true;
	}else if (document.selection){	
		if (document.selection.createRange().text != ''){
			selecionado = true;
		}
	}
	if(valor.length <=2){
		if(campo.selectionEnd == 1){
			var v = parseInt(valor.substr(0,1));
			if(v != 1){
				if(tecla == 46 || tecla == 44){
					return true;
				}else{
					return false;
				}
			}else{
				if(tecla == 46 || tecla == 44 || tecla == 48){
					return true;
				}else{
					return false;
				}
			}		
		}else if(campo.selectionEnd == 2){
			var v = valor.substr(1,1);
			if(v == "." || v == ","){
				if((tecla > 47 && tecla < 58) || (selecionado)){
					return true;
				}else{
					return false;
				}
			}else if(v == "0"){
					return false;
			}

		}
	}else{
		return false;
	}	
	if((tecla > 47 && tecla < 58) || (selecionado)){ // números de 0 a 9
	   return true;
	}
	else{ 
        return false;
    }
    return true;
}