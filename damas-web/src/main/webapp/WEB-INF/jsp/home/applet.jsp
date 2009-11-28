<%@page pageEncoding="ISO-8859-1" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
   <head>
     
      <script>
      var jsVar = "Hello World from jsVar";

      function jsFnct() {
        return "Hello World from jsFnct";
        }
        
         function imprime()
         {
            var applet = document.getElementById('idApplet'); //Objeto del applet embebido en la pagina
            var dato = document.getElementById('datito').value;
            applet.rellenaListaConFicheroDeDirectorioRaiz(dato);
         }
      </script>
   </head>
   <body>
      <!-- El applet, con un id que permita identificarlo -->
      <p><applet id="idApplet" code="prueba.AppletAccessor" codebase="../applet/" archive="applet.jar" width="200" height="100">
      		<PARAM NAME="first"  VALUE="&{jsVar};">
  			<PARAM NAME="second" VALUE="&{jsFnct()};">
  			<PARAM NAME="third"  VALUE="&{'hello world'.toUpperCase() + ' from js Expression'};">
      </applet></p>

      <!-- Un boton que al pulsarlo llame al metodo pulsado() de javascript -->
      <FORM name="formDisplay">
         <INPUT TYPE="button" NAME="boton" VALUE="Imprimir" onClick="imprime()"></INPUT>
         <input type="hidden" id="datito" value="${prueba}"/>
      </FORM>
   </body>
</html>
