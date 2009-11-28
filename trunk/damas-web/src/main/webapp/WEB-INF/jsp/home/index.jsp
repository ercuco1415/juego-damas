<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
.slot {
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

#x1y10 {left:   0px;top:  0px;}
#x2y9  {left:  50px;top: 50px;}
#x3y10 {left: 100px;top:  0px;}
#x4y9 { left: 150px;top: 50px;}
#x5y10 {left: 200px;top:  0px;}
#x6y9 { left: 250px;top: 50px;}
#x7y10 {left: 300px;top:  0px;}
#x8y9 { left: 350px;top: 50px;}
#x9y10 {left: 400px;top:  0px;}
#x10y9 {left: 450px;top: 50px;}

#x1y8 {left:    0px;top: 100px;}
#x2y7 {left:   50px;top: 150px;}
#x3y8 {left:  100px;top: 100px;}
#x4y7 {left:  150px;top: 150px;}
#x5y8 {left:  200px;top: 100px;}
#x6y7 {left:  250px;top: 150px;}
#x7y8 {left:  300px;top: 100px;}
#x8y7 {left:  350px;top: 150px;}
#x9y8 {left:  400px;top: 100px;}
#x10y7{left:  450px;top: 150px;}

#x1y6 {left:    0px;top: 200px;}
#x2y5 {left:   50px;top: 250px;}
#x3y6 {left:  100px;top: 200px;}
#x4y5 {left:  150px;top: 250px;}
#x5y6 {left:  200px;top: 200px;}
#x6y5 {left:  250px;top: 250px;}
#x7y6 {left:  300px;top: 200px;}
#x8y5 {left:  350px;top: 250px;}
#x9y6 {left:  400px;top: 200px;}
#x10y5{left:  450px;top: 250px;}

#x1y4 {left:    0px;top: 300px;}
#x2y3 {left:   50px;top: 350px;}
#x3y4 {left:  100px;top: 300px;}
#x4y3 {left:  150px;top: 350px;}
#x5y4 {left:  200px;top: 300px;}
#x6y3 {left:  250px;top: 350px;}
#x7y4 {left:  300px;top: 300px;}
#x8y3 {left:  350px;top: 350px;}
#x9y4 {left:  400px;top: 300px;}
#x10y3 {left: 450px;top: 350px;}

#x1y2 {left:    0px;top: 400px;}
#x2y1 {left:   50px;top: 450px;}
#x3y2 {left:  100px;top: 400px;}
#x4y1 {left:  150px;top: 450px;}
#x5y2 {left:  200px;top: 400px;}
#x6y1 {left:  250px;top: 450px;}
#x7y2 {left:  300px;top: 400px;}
#x8y1 {left:  350px;top: 450px;}
#x9y2 {left:  400px;top: 400px;}
#x10y1 {left: 450px;top: 450px;}

#fn1 {left:   50px;top: 450px;}
#fn2 {left:  150px;top: 450px;}
#fn3 {left:  250px;top: 450px;}
#fn4 {left:  350px;top: 450px;}
#fn5 {left: 450px;top: 450px;}

#fn6 {left:    0px;top: 400px;}
#fn7 {left:  100px;top: 400px;}
#fn8 {left:  200px;top: 400px;}
#fn9 {left:  300px;top: 400px;}
#fn10 {left:  400px;top: 400px;}

#fn11 {left:   50px;top: 350px;}
#fn12 {left:  150px;top: 350px;}
#fn13 {left:  250px;top: 350px;}
#fn14 {left:  350px;top: 350px;}
#fn15 {left: 450px;top: 350px;}

#fb16 {left:   0px;top:  0px;}
#fb17 {left: 100px;top:  0px;}
#fb18 {left: 200px;top:  0px;}
#fb19 {left: 300px;top:  0px;}
#fb20 {left: 400px;top:  0px;}

#fb21  {left:  50px;top: 50px;}
#fb22 { left: 150px;top: 50px;}
#fb23 { left: 250px;top: 50px;}
#fb24 { left: 350px;top: 50px;}
#fb25 {left: 450px;top: 50px;}

#fb26 {left:    0px;top: 100px;}
#fb27 {left:  100px;top: 100px;}
#fb28 {left:  200px;top: 100px;}
#fb29 {left:  300px;top: 100px;}
#fb30 {left:  400px;top: 100px;}



#usercontrols {
	top: -36px;
}

#workarea {
	position: relative;
	height: 300px;
}
</style>

<body class="yui-skin-sam">

<script>
 
    function processMoveFicha(ficha,casillero){
		alert("Se movio la ficha : " + ficha + " Al casillero: " +casillero ); 
    }
</script>

<c:forEach var="casillero" items="${casilleros}">
	<div class="slot" id="${casillero.idEntity}" >${casillero.idEntity}</div>
</c:forEach>

<c:forEach var="ficha" items="${fichasNegras}">
	<div class="ficha" id="${ficha.idEntity}" >${ficha.idEntity}</div>
</c:forEach>
<c:forEach var="ficha" items="${fichasBlancas}">
	<div class="fichaBlanca" id="${ficha.idEntity}" >${ficha.idEntity}</div>
</c:forEach>

<script type="text/javascript">
<!--
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
        // reset the linked element styles
        this.resetTargets();
        YAHOO.util.Dom.setStyle(this.getEl(), "opacity", 1);

    },

    resetTargets: function() {

        // reset the target styles
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
        // get the drag and drop object that was targeted
        var oDD;
        
       
        if ("string" == typeof id) {
            oDD = YAHOO.util.DDM.getDDById(id);
        } else {
            oDD = YAHOO.util.DDM.getBestMatch(id);
        }
		
        var el = this.getEl();
        
        // check if the slot has a player in it already
        if (!oDD.ficha) {
        	YAHOO.util.DDM.moveToEl(el, oDD.getEl());
        	alert("id: " + id);
        	alert("el.id: " + el.id);
        	JuegoDamas.moveFicha(el.id,id,processMoveFicha);
            if (this.slot) {
                this.slot.ficha = null;
            }
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
    slots[0] = new YAHOO.util.DDTarget("x1y10", "topslots");
	slots[1] = new YAHOO.util.DDTarget("x2y9", "topslots");
	slots[2] = new YAHOO.util.DDTarget("x3y10", "topslots");
	slots[3] = new YAHOO.util.DDTarget("x4y9", "topslots");
	slots[4] = new YAHOO.util.DDTarget("x5y10", "topslots");
	slots[5 ] = new YAHOO.util.DDTarget("x6y9", "topslots");
	slots[6 ] = new YAHOO.util.DDTarget("x7y10", "topslots");
	slots[7 ] = new YAHOO.util.DDTarget("x8y9", "topslots");
	slots[8 ] = new YAHOO.util.DDTarget("x9y10", "topslots");
	slots[9 ] = new YAHOO.util.DDTarget("x10y9", "topslots");
	slots[10] = new YAHOO.util.DDTarget("x1y8", "topslots");
	slots[11] = new YAHOO.util.DDTarget("x2y7", "topslots");
	slots[12] = new YAHOO.util.DDTarget("x3y8", "topslots");
	slots[13] = new YAHOO.util.DDTarget("x4y7", "topslots");
	slots[14] = new YAHOO.util.DDTarget("x5y8", "topslots");
	slots[15] = new YAHOO.util.DDTarget("x6y7", "topslots");
	slots[16] = new YAHOO.util.DDTarget("x7y8", "topslots");
	slots[17] = new YAHOO.util.DDTarget("x8y7", "topslots");
	slots[18] = new YAHOO.util.DDTarget("x9y8", "topslots");
	slots[19] = new YAHOO.util.DDTarget("x10y7", "topslots");
	slots[20] = new YAHOO.util.DDTarget("x1y6", "topslots");
	slots[21] = new YAHOO.util.DDTarget("x2y5", "topslots");
	slots[22] = new YAHOO.util.DDTarget("x3y6", "topslots");
	slots[23] = new YAHOO.util.DDTarget("x4y5", "topslots");
	slots[24] = new YAHOO.util.DDTarget("x5y6", "topslots");
	slots[25] = new YAHOO.util.DDTarget("x6y5", "topslots");
	slots[26] = new YAHOO.util.DDTarget("x7y6", "topslots");
	slots[27] = new YAHOO.util.DDTarget("x8y5", "topslots");
	slots[28] = new YAHOO.util.DDTarget("x9y6", "topslots");
	slots[29] = new YAHOO.util.DDTarget("x10y5", "topslots");
	slots[30] = new YAHOO.util.DDTarget("x1y4", "topslots");
	slots[31] = new YAHOO.util.DDTarget("x2y3", "topslots");
	slots[32] = new YAHOO.util.DDTarget("x3y4", "topslots");
	slots[33] = new YAHOO.util.DDTarget("x4y3", "topslots");
	slots[34] = new YAHOO.util.DDTarget("x5y4", "topslots");
	slots[35] = new YAHOO.util.DDTarget("x6y3", "topslots");
	slots[36] = new YAHOO.util.DDTarget("x7y4", "topslots");
	slots[37] = new YAHOO.util.DDTarget("x8y3", "topslots");
	slots[38] = new YAHOO.util.DDTarget("x9y4", "topslots");
	slots[39] = new YAHOO.util.DDTarget("x10y3", "topslots");
	slots[40] = new YAHOO.util.DDTarget("x1y2", "topslots");
	slots[41] = new YAHOO.util.DDTarget("x2y1", "topslots");
	slots[42] = new YAHOO.util.DDTarget("x3y2", "topslots");
	slots[43] = new YAHOO.util.DDTarget("x4y1", "topslots");
	slots[44] = new YAHOO.util.DDTarget("x5y2", "topslots");
	slots[45] = new YAHOO.util.DDTarget("x6y1", "topslots");
	slots[46] = new YAHOO.util.DDTarget("x7y2", "topslots");
	slots[47] = new YAHOO.util.DDTarget("x8y1", "topslots");
	slots[48] = new YAHOO.util.DDTarget("x9y2", "topslots");
	slots[49] = new YAHOO.util.DDTarget("x10y1", "topslots");
    
    // players
    players[0 ] = new YAHOO.example.DDPlayer("fn1", "topslots");
	players[1 ] = new YAHOO.example.DDPlayer("fn2", "topslots");
	players[2 ] = new YAHOO.example.DDPlayer("fn3", "topslots");
	players[3 ] = new YAHOO.example.DDPlayer("fn4", "topslots");
	players[4 ] = new YAHOO.example.DDPlayer("fn5", "topslots");
	players[5 ] = new YAHOO.example.DDPlayer("fn6", "topslots");
	players[6 ] = new YAHOO.example.DDPlayer("fn7", "topslots");
	players[7 ] = new YAHOO.example.DDPlayer("fn8", "topslots");
	players[8 ] = new YAHOO.example.DDPlayer("fn9", "topslots");
	players[9 ] = new YAHOO.example.DDPlayer("fn10", "topslots");
	players[10] = new YAHOO.example.DDPlayer("fn11", "topslots");
	players[11] = new YAHOO.example.DDPlayer("fn12", "topslots");
	players[12] = new YAHOO.example.DDPlayer("fn13", "topslots");
	players[13] = new YAHOO.example.DDPlayer("fn14", "topslots");
	players[14] = new YAHOO.example.DDPlayer("fn15", "topslots");

	players[15] = new YAHOO.example.DDPlayer("fb16", "topslots");
	players[16] = new YAHOO.example.DDPlayer("fb17", "topslots");
	players[17] = new YAHOO.example.DDPlayer("fb18", "topslots");
	players[18] = new YAHOO.example.DDPlayer("fb19", "topslots");
	players[19] = new YAHOO.example.DDPlayer("fb20", "topslots");
	players[20] = new YAHOO.example.DDPlayer("fb21", "topslots");
	players[21] = new YAHOO.example.DDPlayer("fb22", "topslots");
	players[22] = new YAHOO.example.DDPlayer("fb23", "topslots");
	players[23] = new YAHOO.example.DDPlayer("fb24", "topslots");
	players[24] = new YAHOO.example.DDPlayer("fb25", "topslots");
	players[25] = new YAHOO.example.DDPlayer("fb26", "topslots");
	players[26] = new YAHOO.example.DDPlayer("fb27", "topslots");
	players[27] = new YAHOO.example.DDPlayer("fb28", "topslots");
	players[28] = new YAHOO.example.DDPlayer("fb29", "topslots");
	players[29] = new YAHOO.example.DDPlayer("fb30", "topslots");
	
	
    DDM.mode = document.getElementById("ddmode").selectedIndex;

    Event.on("ddmode", "change", function(e) {
            YAHOO.util.DDM.mode = this.selectedIndex;
        });
});

})();
--></script>


</body>
</html>
