package decorator.solucion.c.patronDecorator;

import decorator.problema.BusinessException;
import decorator.problema.Cliente;

/**
 * @author alejandro
 *
 */
public class ClientePosta implements Cliente{
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ClientePosta(String string) {
		// TODO Auto-generated constructor stub
	}

	public void comprar(double monto) throws BusinessException {
		// TODO Auto-generated method stub
	}

	public void pagarVencimiento(double monto) {
		// TODO Auto-generated method stub
		
	}

	
}
