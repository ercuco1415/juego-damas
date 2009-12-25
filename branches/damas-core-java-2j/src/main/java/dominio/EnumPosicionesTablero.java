package dominio;



public enum EnumPosicionesTablero {
	x1y10("x1y10","display: inline; opacity: 1; left:  100px;top: 100px;"),
	x2y9 ("x2y9","display: inline; opacity: 1; left:   150px;top: 150px;"),
	x3y10("x3y10","display: inline; opacity: 1; left:  200px;top: 100px;"),
	x4y9 ("x4y9","display: inline; opacity: 1; left:   250px;top: 150px;"),
	x5y10("x5y10","display: inline; opacity: 1; left: 300px;top:  100px;"),
	x6y9 ("x6y9","display: inline; opacity: 1; left:  350px;top:  150px;"),
	x7y10("x7y10","display: inline; opacity: 1; left: 400px;top:  100px;"),
	x8y9 ("x8y9","display: inline; opacity: 1; left:  450px;top:  150px;"),
	x9y10("x9y10","display: inline; opacity: 1; left: 500px;top:  100px;"),
	x10y9("x10y9","display: inline; opacity: 1; left: 550px;top:  150px;"),

	x1y8  ("x1y8","display: inline; opacity: 1; left:  100px;top: 200px;"),
	x2y7  ("x2y7","display: inline; opacity: 1; left:  150px;top: 250px;"),
	x3y8  ("x3y8","display: inline; opacity: 1; left:  200px;top: 200px;"),
	x4y7  ("x4y7","display: inline; opacity: 1; left:  250px;top: 250px;"),
	x5y8  ("x5y8","display: inline; opacity: 1; left:  300px;top: 200px;"),
	x6y7  ("x6y7","display: inline; opacity: 1; left:  350px;top: 250px;"),
	x7y8  ("x7y8","display: inline; opacity: 1; left:  400px;top: 200px;"),
	x8y7  ("x8y7","display: inline; opacity: 1; left:  450px;top: 250px;"),
	x9y8  ("x9y8","display: inline; opacity: 1; left:  500px;top: 200px;"),
	x10y7 ("x10y7","display: inline; opacity: 1; left: 550px;top: 250px;"),

	x1y6  ("x1y6","display: inline; opacity: 1; left:  100px;top: 300px;"),
	x2y5  ("x2y5","display: inline; opacity: 1; left:  150px;top: 350px;"),
	x3y6  ("x3y6","display: inline; opacity: 1; left:  200px;top: 300px;"),
	x4y5  ("x4y5","display: inline; opacity: 1; left:  250px;top: 350px;"),
	x5y6  ("x5y6","display: inline; opacity: 1; left:  300px;top: 300px;"),
	x6y5  ("x6y5","display: inline; opacity: 1; left:  350px;top: 350px;"),
	x7y6  ("x7y6","display: inline; opacity: 1; left:  400px;top: 300px;"),
	x8y5  ("x8y5","display: inline; opacity: 1; left:  450px;top: 350px;"),
	x9y6  ("x9y6","display: inline; opacity: 1; left:  500px;top: 300px;"),
	x10y5 ("x10y5","display: inline; opacity: 1; left: 550px;top: 350px;"),

	x1y4  ("x1y4","display: inline; opacity: 1; left:  100px;top: 400px;"),
	x2y3  ("x2y3","display: inline; opacity: 1; left:  150px;top: 450px;"),
	x3y4  ("x3y4","display: inline; opacity: 1; left:  200px;top: 400px;"),
	x4y3  ("x4y3","display: inline; opacity: 1; left:  250px;top: 450px;"),
	x5y4  ("x5y4","display: inline; opacity: 1; left:  300px;top: 400px;"),
	x6y3  ("x6y3","display: inline; opacity: 1; left:  350px;top: 450px;"),
	x7y4  ("x7y4","display: inline; opacity: 1; left:  400px;top: 400px;"),
	x8y3  ("x8y3","display: inline; opacity: 1; left:  450px;top: 450px;"),
	x9y4  ("x9y4","display: inline; opacity: 1; left:  500px;top: 400px;"),
	x10y3 ("x10y3","display: inline; opacity: 1; left: 550px;top: 450px;}"),
	x1y2  ("x1y2","display: inline; opacity: 1; left:  100px;top: 500px;"),
	x2y1  ("x2y1","display: inline; opacity: 1; left:  150px;top: 550px;"),
	x3y2  ("x3y2","display: inline; opacity: 1; left:  200px;top: 500px;"),
	x4y1  ("x4y1","display: inline; opacity: 1; left:  250px;top: 550px;"),
	x5y2  ("x5y2","display: inline; opacity: 1; left:  300px;top: 500px;"),
	x6y1  ("x6y1","display: inline; opacity: 1; left:  350px;top: 550px;"),
	x7y2  ("x7y2","display: inline; opacity: 1; left:  400px;top: 500px;"),
	x8y1  ("x8y1","display: inline; opacity: 1; left:  450px;top: 550px;"),
	x9y2  ("x9y2","display: inline; opacity: 1; left:  500px;top: 500px;"),
	x10y1 ("x10y1","display: inline; opacity: 1; left: 550px;top: 550px;");
	
    private final String name;
    private final String value;
    EnumPosicionesTablero(final String name,final String value) {
        this.name = name;
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
    public String getName() {
        return this.name;
    }
    public static EnumPosicionesTablero get(String name) {
        for (EnumPosicionesTablero e : values()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
	
}