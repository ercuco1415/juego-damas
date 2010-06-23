package decorator.solucion.c.patronDecorator;

import junit.framework.TestCase;
import decorator.problema.Cliente;

public class ClientePostaTest extends TestCase {

	public void test(){
		Cliente bertolini = new ClienteSafeShop(new ClientePosta("bertolini"));
		Cliente amalita = new ClienteSafeShop(new ClientePromocion(15.0, 50.0,
		new ClientePosta("bertolini")));
		
	}
}
