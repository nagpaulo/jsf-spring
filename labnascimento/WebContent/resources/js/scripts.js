/**
 * Created by luiz.alberto on 11/11/2014.
 */
function createWaitModal(){
    var waitModal = $('<div class="modal static" id="pleaseWaitDialog" tabindex="-1" role="dialog" aria-hidden="true"><div class="modal-dialog modal-sm"><div class="modal-content"><div class="modal-header"><h4>Aguarde...</h4></div><div class="modal-body"><div class="progress progress-striped active"><div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">Carregando...</div></div></div></div></div></div>');
    var dialogEx = $.extend(waitModal, {
        show: function() { waitModal.modal({ backdrop: 'static', keyboard: false }); },
        hide: function(){ waitModal.modal('hide'); }
    });
    return dialogEx;
}

function createModal(fnExecute, size){
    var dialog  = $('<div class="modal fade" id="modalComponent" tabindex="-1" role="dialog" aria-hidden="true"> <div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title"></h4></div><div class="modal-body"></div><div class="modal-footer"><button type="button" class="btn btn-success btn-sim"><span class="glyphicon glyphicon-ok-circle"></span> <span class="confirm_button">Sim</span></button><button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> <span class="negative_button">Não</span></button></div></div></div></div>').appendTo('body');
    dialog.find('.btn-sim').click(fnExecute);
    dialog.addClass("bs-example-modal-"+size);
    dialog.find('.modal-dialog').addClass("modal-"+size);

    var dialogEx = $.extend(dialog, {
        setTitle: function(title) { dialog.find('.modal-title').text(title); },
        setMessage: function(message) { dialog.find('.modal-body').text(message); },
        setContent: function(content) {
            dialog.find('.modal-title').text(content.find('legend').text());
            content.find('legend').remove();
            dialog.find('.modal-body').html(content);
        },
        setConfirmText: function(text) { dialog.find('.confirm_button').text(text); },
        setNegativeText: function(text) { dialog.find('.negative_button').text(text); },
        hideConfirmButton: function(hide) { dialog.find('.btn-sim').hide(); },
        show: function() { dialog.modal('show');  },
        hide: function(){ dialog.modal('hide'); }
    });

    return dialogEx;
};


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

			this.elem.unbind('keypress.text').bind('keypress.text', function(){
				var componente = $(this);
				if(componente.val().length >=  obj.config.maxLength){
					return false;
				}
			})
		}
		this.init();		
	}

	$.fn.textmaxlength = function(options){
		return this.each(function(){			
			var element = $(this);
			if (element.data('textmaxlength')){//Se este elemento ja possui o plugins
				return ;	
			}
			var textmaxlength = new TextMaxLength(this, options);
			element.data('textmaxlength', textmaxlength);	
		});
	};
})(jQuery);


$(function(){
	$('[data-toggle="tooltip"]').tooltip();
	
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
});
