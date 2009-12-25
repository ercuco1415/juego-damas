<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@include file="../common/includes.jsp"%>
<html>
<script type="text/javascript"  src="${ctx}/js/damas/manejoCasilleros.js"></script>
<script type="text/javascript"  src="${ctx}/js/damas/crono.js"></script>
<script type="text/javascript"  src="${ctx}/js/damas/damas-dwr.js"></script>
<%--
se debe iniciar el crono al inciar el partido que puede no ser cuando se inicia la pagina 
 --%>  
<body onload="javascript:IniciarCrono();">
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
		    <li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">TIEMPO EN ESPERA DE JUGADA</a>
		    	<form name="crono">
					<input type="text"  disabled="disabled" size="8" name="display" value="00:00:0"> 
				</form>
		    </li>
			<li class="noChildren" ><a style="padding-top: 10px; padding-left: 5px;">TIEMPO DEL JUEGO</a>
				<form name="crono">
					<input type="text"  disabled="disabled" size="8" name="displayJuego" value="00:00:0"> 
				</form>
			</li>
		    <br></br>
			<li><button onfocus="this.blur();"  type="submit" id="iniciaJuego" onclick="javascript:DamasListener.inicializar('00:00:00',processInit)">
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
			<li> 
				
			</li>
		</ul>
		<div >
				<form action="/index.htm" method="post">
					
					<!--<input type="hidden" id="totalMin" name="totalMin" value="${jugadaBean.minutosJuego}" /> 
					<input type="hidden" id="totalSeg" name="totalSeg" value="${jugadaBean.segundosJuego}" /> 
					
					--><c:forEach var="casillero" items="${casilleros}">
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

</html>
