<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/build/fonts/fonts-min.css" />
<script type="text/javascript"
	src="${ctx}/js/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript"
	src="${ctx}/js/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/build/animation/animation-min.js"></script>
<script type='text/javascript' src='/damas/dwr/interface/JuegoDamas.js'></script>
<script type='text/javascript' src='/damas/dwr/engine.js'></script>
<script type='text/javascript' src='/damas/dwr/util.js'></script>


</head>
<style type="text/css">
.tablero {
	text-align: center;
	position: absolute;
	width: auto;
	height: auto;
	
}
.casillero {
	background-color: #cccccc;
	color: #cccccc;
	text-align: center;
	position: absolute;
	width: 50px;
	height: 50px;
}

.ficha {
	background: url(../images/circle.gif) 0 0 no-repeat;
	background-color: #cccccc;
	text-align: center;
	position: absolute;
	width: 50px;
	height: 50px;
}

.fichaBlanca {
	background: url(../images/circleBco.gif) 0 0 no-repeat;
	background-color: #cccccc;
	text-align: center;
	position: absolute;
	width: 50px;
	height: 50px;
}

.target {
	background-color: #cccccc;
	text-align: center;
	position: absolute;
	width: 50px;
	height: 50px;
}

#usercontrols {
	top: -36px;
}

#workarea {
	position: relative;
	height: 300px;
}
#header {
	-moz-background-clip: border;
	-moz-background-inline-policy: continuous;
	-moz-background-origin: padding;
	background: #333366 none repeat scroll 0 0;
	font-size: 160%;
	font-weight: bold;
	height: 46px;
	left: 0;
	padding: 2px 0;
	position: absolute;
	top: 0;
	width: 100%;
	
}
#header {
	height: 46px;
	left: auto;
	padding: 2px 0;
	position: static;
	top: auto;
	width: 100%;
	padding-left: 500px;
}
#breadcrumbs {
	padding-left: 20px;
}

#pagelinks a.currentLink {
	border: 0 none;
	color: #DDDDDD;
}

#pagelinks a {
	border: 0 none;
	color: #CCCCCC;
}

#nav:hover {
	-moz-border-radius-bottomleft: 5px;
	-moz-border-radius-bottomright: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
	border: 1px solid #DDDDDD;
	opacity: 0.95;
}

#nav {
	-moz-background-clip: border;
	-moz-background-inline-policy: continuous;
	-moz-background-origin: padding;
	-moz-border-radius-bottomleft: 5px;
	-moz-border-radius-bottomright: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
	background: #F8F8F8 none repeat scroll 0 0;
	border: 1px solid #DDDDDD;
	left:700px;
	padding:50px;
	position:absolute;
	top:150px;
}
#nav2 {
	-moz-background-clip: border;
	-moz-background-inline-policy: continuous;
	-moz-background-origin: padding;
	-moz-border-radius-bottomleft: 5px;
	-moz-border-radius-bottomright: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
	background: #F8F8F8 none repeat scroll 0 0;
	border: 1px solid #DDDDDD;
	left:10px;
	padding:50px;
	position:absolute;
	top:150px;
}
body {
	font-family: 'Calibri', 'Helvetica Neue', 'Arial', sans-serif;
	font-size: medium;
	font-size-adjust: none;
	font-style: normal ;
	font-variant: normal;
	font-weight: normal;
	line-height: 1.75;
	word-spacing: 0.1em;
	
	
}

.menu,.menu ul {
	list-style-image: none;
	list-style-position: outside;
	list-style-type: none;
	padding-left: 30px;
}

.menu {
}
#breadcrumbs {
	padding-left:20px;
}
#header a {
	border:0 none;
	color:white;
	padding-left:20px;
}
a:link {
	color:#1B67C9;
}
a:hover {
	border-bottom:1px solid;
}
a {
	-moz-background-clip:border;
	-moz-background-inline-policy:continuous;
	-moz-background-origin:padding;
	background:transparent none repeat scroll 0 0;
	border-bottom:1px dotted;
	text-decoration:none;
}
</style>
<script>
 
    function processMoveFicha(ficha,casillero){
		try{
			if(ficha){
				//se deben actualizar datos de resultado
				JuegoDamas.botMueveACasillero(processBotMueve());
				
			}else{
				alert("No se puede mover a ese casillero");
				location.href=location.href;
			}

		}catch(err){
			alert(err);
		}
    }
    function processInit(){
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

</script>
	
<body>
		<div id="header">
			<a>Juego de Damas By MEGAJANDRO</a>
		</div>
		<div id="breadcrumbs" style="border-style: solid">
			<a href="">Ayuda </a> » 
		</div>
		<ul id="nav" class="menu" style="padding-top: 10px; padding-left: 5px;">
			<li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">FICHAS MAQUINA               :   500</a></li>
			<li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">FICHAS HUMANO                :   500</a></li>
			<li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">MOVIDAS REALIZADAS TOTAL     :   500</a></li>
			<li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">MOVIDAS REALIZADAS SIN COMER :   500</a></li>
			<li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">FICHA QUE MAS COMIO Y CANT   :   500</a></li>
		    <li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">TIEMPO DEL JUEGO             :   500</a></li>
		    <li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">TIEMPO EN ESPERA DE JUGADA   :   500</a></li>
		    <li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">TIEMPO UTILIZADO POR HUMANO  :   500</a></li>
		    <li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">TIEMPO UTILIZADO POR MAQUINA :   500</a></li>
		    <br></br>
			<li><button onfocus="this.blur();"  type="submit" id="iniciaJuego" onclick="javascript:JuegoDamas.init(processInit)">
				<span>
					<spring:message  text="iniciar Otro Juego ." />
				</span>
				</button>
			</li>
			<li><button  onfocus="this.blur();"  type="submit" id="GuardaJuego" onclick="">
				<span>
					<spring:message  text="Guardar Jugada    ." />
				</span>
				</button>
			</li>
		</ul>
		<div >
				<form action="/index.htm" method="post">
					<input type="hidden" id="redireccionar" name="redireccionar" value="${ctx}/index.htm/action_movioHumano" /> 
					<c:forEach var="casillero" items="${casilleros}">
						<div class="casillero" id="${casillero.idEntity}" style="${casillero.style}">${casillero.idEntity}</div>
					</c:forEach> 
					<c:forEach var="ficha" items="${fichasNegras}">
						<div class="ficha" id="${ficha.idEntity}" style="${ficha.casillero.style}">${ficha.idEntity}</div>
					</c:forEach> 
					<c:forEach var="ficha" items="${fichasBlancas}">
						<div class="fichaBlanca" id="${ficha.idEntity}" style="${ficha.casillero.style}">${ficha.idEntity}</div>
					</c:forEach> 
				
				</form>
		</div>
</body>
<script type="text/javascript"><!--

(function() {

YAHOO.example.DDPlayer = function(id, sGroup, config) {
    YAHOO.example.DDPlayer.superclass.constructor.apply(this, arguments);
    this.initPlayer(id, sGroup, config);
};

YAHOO.extend(YAHOO.example.DDPlayer, YAHOO.util.DDProxy, {

    TYPE: "DDPlayer",

    initPlayer: function(id, sGroup, config) {
        if (!id) { 
            return; 
        }
		
        var el = this.getDragEl()
        YAHOO.util.Dom.setStyle(el, "borderColor", "transparent");
        YAHOO.util.Dom.setStyle(el, "opacity", 0.76);

        // specify that this is not currently a drop target
        this.isTarget = false;

        this.originalStyles = [];

        this.type = YAHOO.example.DDPlayer.TYPE;
        this.slot = null;

        this.startPos = YAHOO.util.Dom.getXY( this.getEl() );
        YAHOO.log(id + " startpos: " + this.startPos, "info", "example");
    },

    startDrag: function(x, y) {
        YAHOO.log(this.id + " startDrag", "info", "example");
        var Dom = YAHOO.util.Dom;

        var dragEl = this.getDragEl();
        var clickEl = this.getEl();
        dragEl.innerHTML = clickEl.innerHTML;
        dragEl.className = clickEl.className;

        Dom.setStyle(dragEl, "color",  Dom.getStyle(clickEl, "color"));
        Dom.setStyle(dragEl, "backgroundColor", Dom.getStyle(clickEl, "backgroundColor"));

        Dom.setStyle(clickEl, "opacity", 0.1);

        var targets = YAHOO.util.DDM.getRelated(this, true);
        YAHOO.log(targets.length + " targets", "info", "example");
        for (var i=0; i<targets.length; i++) {
            
            var targetEl = this.getTargetDomRef(targets[i]);
			
            if (!this.originalStyles[targetEl.id]) {
                this.originalStyles[targetEl.id] = targetEl.className;
            }

            targetEl.className = "target";
        }
    },

    getTargetDomRef: function(oDD) {
        if (oDD.ficha) {
            return oDD.ficha.getEl();
        } else {
            return oDD.getEl();
        }
    },
    
    endDrag: function(e) {
        this.resetTargets();
        YAHOO.util.Dom.setStyle(this.getEl(), "opacity", 1);

    },

    resetTargets: function() {

        var targets = YAHOO.util.DDM.getRelated(this, true);
        for (var i=0; i<targets.length; i++) {
            var targetEl = this.getTargetDomRef(targets[i]);
            var oldStyle = this.originalStyles[targetEl.id];
            if (oldStyle) {
                targetEl.className = oldStyle;
            }
        }
    },

    onDragDrop: function(e, id) {
        var oDD;
        
        try{
        if ("string" == typeof id) {
            oDD = YAHOO.util.DDM.getDDById(id);
        } else {
            oDD = YAHOO.util.DDM.getBestMatch(id);
        }
		
        var el = this.getEl();
        
        if (!oDD.ficha) {
        	YAHOO.util.DDM.moveToEl(el, oDD.getEl());
        	JuegoDamas.moveFicha(el.id,id,processMoveFicha);
        	
            if (this.slot) {
                this.slot.ficha = null;
            }
        }
        }catch(err){
			alert(err);
        }
        this.resetTargets();
    },

    onDragOver: function(e, id) {
    },

    onDrag: function(e, id) {
    }
    
});

var slots = [], players = [],
    Event = YAHOO.util.Event, DDM = YAHOO.util.DDM;

Event.onDOMReady(function() { 
    // slots
    var elements = document.getElementsByClassName('casillero');
    
    var i=0;
    for (i=0;i<50;i++)
    {
     
    	slots[i] = new YAHOO.util.DDTarget(elements.item(i).id, "topslots");
  	}
    var fichasNegras = document.getElementsByClassName('ficha');
    var j=0;
    for (j=0;j<15;j++)
    {
    	players[j ] = new YAHOO.example.DDPlayer(fichasNegras.item(j).id, "topslots");
  	}
    var fichasBlancas = document.getElementsByClassName('fichaBlanca');
    j=0;
    for (j=0;j<15;j++)
    {
    	players[j ] = new YAHOO.example.DDPlayer(fichasBlancas.item(j).id, "topslots");
  	}
	
    DDM.mode = document.getElementById("ddmode").selectedIndex;

    Event.on("ddmode", "change", function(e) {
            YAHOO.util.DDM.mode = this.selectedIndex;
        });
});

})();
--></script>
</html>
