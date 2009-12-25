
function processMoveFicha(ficha,casillero){
	try{
		if(ficha){
			//se deben actualizar datos de resultado
			DamasListener.botMueveACasillero(processBotMueve());
		}else{
			alert("No se puede mover a ese casillero");
			location.href=location.href;
		}

	}catch(err){
		alert(err);
	}
}
function processInit(tiempo){
	try{
		location.href=location.href;

	}catch(err){
		alert(err);
	}
}
function processBotMueve(){
	try{
		location.href=location.href;

	}catch(err){
		alert(err);
	}
}