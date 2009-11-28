
<html>

<title>Plugin example</title>
<body bgcolor="white">
<h3>Current time is :</h3>

<jsp:plugin type="applet" code="prueba.MiApplet"
	codebase="../applet/" archive="applet.jar" jreversion="1.2" width="200" height="200">
	
	<jsp:fallback>Error</jsp:fallback>
</jsp:plugin>


<p>
<h4><font color=red> The above applet is loaded using the
Java Plugin from a jsp page using the plugin tag. </font></h4>
</body>
</html>
