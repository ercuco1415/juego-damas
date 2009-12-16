
var CronoID = null
var CronoEjecutandose = false
var decimas, segundos, minutos

function DetenerCrono (){
   	if(CronoEjecutandose)
   		clearTimeout(CronoID)
   	CronoEjecutandose = false
}

function InicializarCrono () {
	//inicializa contadores globales
	decimas = 0
	segundos = 0
	minutos = 0
	segundosJuego = 20 ; //document.getElementsByName('totalMin').item(0).value
	minutosJuego = 5; // document.getElementsByName('totalSeg').item(0).value
	//pone a cero los marcadores
	document.getElementsByName('display').item(0).value = '00:00:0'
}

function MostrarCrono () {
	     
   	//incrementa el crono
   	decimas++
	if ( decimas > 9 ) {
		decimas = 0
		segundos++
		if ( segundos > 59 ) {
			segundos = 0
			minutos++
			if ( minutos > 99 ) {
				alert('Fin de la cuenta')
				DetenerCrono()
				return true
			}
		}
	}
   	if ( decimas > 9 ) {
		decimas = 0
		segundosJuego++
		if ( segundosJuego > 59 ) {
			segundosJuego = 0
			minutosJuego++
			if ( minutosJuego > 99 ) {
				alert('Fin de la cuenta')
				DetenerCrono()
				return true
			}
		}
	}
	//configura la salida
	var ValorCrono = ""
	ValorCrono = (minutos < 10) ? "0" + minutos : minutos
	ValorCrono += (segundos < 10) ? ":0" + segundos : ":" + segundos
	ValorCrono += ":" + decimas	
	document.getElementsByName('display').item(0).value  = ValorCrono

	var ValorCronoJuego = ""
	ValorCronoJuego = (minutosJuego < 10) ? "0" + minutosJuego : minutosJuego
	ValorCronoJuego += (segundosJuego < 10) ? ":0" + segundosJuego : ":" + segundosJuego
	ValorCronoJuego += ":" + decimas	
	document.getElementsByName('displayJuego').item(0).value  = ValorCronoJuego
		
  	CronoID = setTimeout("MostrarCrono()", 100)
	CronoEjecutandose = true
	return true
}

function IniciarCrono () {
 	DetenerCrono()
 	InicializarCrono()
	MostrarCrono()
}
