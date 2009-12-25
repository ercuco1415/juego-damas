/* FUNCIONES GLOBALES :: BEGIN */

/**
 * Funci?n que hace un request asincr?nico a la url pasada como par?metro
 * y luego llama a la funci?n success de la variable callback en caso de ?xito
 * o muestra un error en caso contrario.
 * Antes de hacer la invocaci?n se muestra un panel que muestra el "Ejecutando..."
 * que desaparece antes de ejecutar el callback.
 */
function invoke(url,callback){
	wait =
		new YAHOO.widget.Panel("wait",
			{ width:"240px",
			  fixedcenter:true,
			  close:false,
			  draggable:false,
			  zindex:4,
			  modal:true,
			  visible:true
			}
		);
	wait.setHeader("Ejecutando ...");
	wait.setBody('<img src="http://us.i1.yimg.com/us.yimg.com/i/us/per/gr/gp/rel_interstitial_loading.gif" />');
	wait.render(document.body);
	var _callback =
	{
	  success:function(o){
	  	wait.hide();
	  	callback.success(o);
	  },
	  failure:function(o){
	  	wait.hide();
		error =
			new YAHOO.widget.SimpleDialog("error",
				{ width:"240px",
				  fixedcenter:true,
				  close:true,
				  draggable:true,
				  zindex:4,
				  modal:true,
				  visible:true,
				  icon: YAHOO.widget.SimpleDialog.ICON_WARN,
				  text: "Ha ocurrido un error inesperado, por favor intente nuevamente",
				  buttons: [ { text:"Cerrar",
							handler:function(){this.hide();},isDefault:true
						}]
				}
			);
		error.setHeader("Aviso");
		error.render(document.body);
	  },
	  argument:callback.arguments
	};
	var transaction = YAHOO.util.Connect.asyncRequest('GET', url, _callback);
}

/**
 * Funci?n que llama a YUILoader. Este m?dulo de YUI carga los scripts
 * dinamicamente de los m?dulos que necesitamos, incluyendo sus dependencias
 */
function initYUI(base, modules){
	var loader = new YAHOO.util.YUILoader({
	    require: modules,
	    loadOptional: true,
	    base: base+"/js/yui/build/",
	    onSuccess: function() {
	    },
	    onFailure: function(msg, xhrobj) {
	    }
		});
	loader.insert();
}
/* FUNCIONES GLOBALES :: END */

/* MENU :: BEGIN */
function cambiarSeleccion(id){
		var list = document.getElementById("tabs").getElementsByTagName("li");
		for(i=0;i<list.length;i++) {
			list[i].className="select";
		}
		var divId = document.getElementById(id);
		divId.className="current";
		var subDivId = document.getElementById(id+id);
		if(subDivId!= null){
			subDivId.className="select_sub show";
		}
		current=id;
}
/* MENU :: END */

/* OTRAS OPERACIONES :: BEGIN */
function mostrarTicket(nroPago,url){
	var responseSuccess = function(o){
		var panel = new YAHOO.widget.SimpleDialog("ticket_pago"+nroPago,
				{ 	width:"300px",
					height:"400px",
					visible:true,
					draggable:true,
					close:true,
					fixedcenter: true,
					constraintoviewport: false,
					buttons: [ { text:"Cerrar",
							handler:function(){this.hide();},isDefault:true
						}]
				} );
		panel.setHeader("Ticket");
		panel.setBody(o.responseText);
		panel.setFooter("");
		panel.render(document.body);
	};
	var callback =
	{
	  success:responseSuccess
	};
	invoke(url+'?nroPago='+nroPago, callback);
}
/* OTRAS OPERACIONES :: END */

/* CUENTAS :: BEGIN */
function mostrarCBU(id_cuenta, ctx){
	// Passing an example of array of arguments to both
	// the success and failure callback handlers.
	var args = [];

	var responseSuccess = function(o){
	/* Please see the Success Case section for more
	 * details on the response object's properties.
	 * o.tId
	 * o.status
	 * o.statusText
	 * o.getResponseHeader[ ]
	 * o.getAllResponseHeaders
	 * o.responseText
	 * o.responseXML
	 * o.argument
	 */
		var panel = new YAHOO.widget.SimpleDialog("cbu_panel_"+id_cuenta,
				{ 	width:"320px",
					visible:true,
					draggable:true,
					close:true,
					fixedcenter: true,
					constraintoviewport: true,
					buttons: [ { text:"Cerrar",
							handler:function(){this.hide();},isDefault:true
						}]
				} );
		panel.setHeader("Consulta de CBU");
		panel.setBody(o.responseText);
		panel.setFooter("");
		panel.render("contenido");
	};

	var responseFailure = function(o){
	// Access the response object's properties in the
	// same manner as listed in responseSuccess( ).
	// Please see the Failure Case section and
	// Communication Error sub-section for more details on the
	// response object's properties.
	}

	var callback =
	{
	  success:responseSuccess,
	  failure:responseFailure,
	  argument:args
	};

	invoke(ctx+'/cuentas/CBUDesdeCuenta.htm?id_cuenta='+id_cuenta, callback);
}

function comprobanteCBU(id_cuenta, ctx){
	// Passing an example of array of arguments to both
	// the success and failure callback handlers.
	var args = [];

	var responseSuccess = function(o){
	/* Please see the Success Case section for more
	 * details on the response object's properties.
	 * o.tId
	 * o.status
	 * o.statusText
	 * o.getResponseHeader[ ]
	 * o.getAllResponseHeaders
	 * o.responseText
	 * o.responseXML
	 * o.argument
	 */
		var panel = new YAHOO.widget.SimpleDialog("cbu_panel_"+id_cuenta,
				{ 	width:"320px",
					visible:true,
					draggable:true,
					close:true,
					fixedcenter: true,
					constraintoviewport: true,
					buttons: [ { text:"Cerrar",
							handler:function(){this.hide();},isDefault:true
						}]
				} );
		panel.setHeader("Comprobante CBU");
		panel.setBody(o.responseText);
		panel.setFooter("");
		panel.render("contenido");
	};

	var responseFailure = function(o){
	// Access the response object's properties in the
	// same manner as listed in responseSuccess( ).
	// Please see the Failure Case section and
	// Communication Error sub-section for more details on the
	// response object's properties.
	}

	var callback =
	{
	  success:responseSuccess,
	  failure:responseFailure,
	  argument:args
	};

	invoke(ctx+'/cuentas/comprobanteCBU.htm?id_cuenta='+id_cuenta, callback);
}

/* CUENTAS :: END */
