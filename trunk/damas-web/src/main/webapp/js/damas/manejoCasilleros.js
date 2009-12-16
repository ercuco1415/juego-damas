
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
        	DamasListener.moveFicha(el.id,id,processMoveFicha);
        	
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