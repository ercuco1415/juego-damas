package decorator.solucion.b.colleccionCondiciones;

import java.util.List;

import decorator.problema.BusinessException;
import decorator.problema.Cliente;

/**
 * @author alejandro
 *
 */
public abstract class ClientePosta implements Cliente{

	private boolean usaSafeShop;
	List<CondicionComercial> condicionesComerciales;
	
	public void comprar(double monto) throws BusinessException {
		
		for (CondicionComercial condicion : condicionesComerciales) {
			condicion.comprar(monto);
		}
	}

	public boolean usaSafeShop() {
		return usaSafeShop;
	}

	public void pagarVencimiento(double monto) {
		// TODO Auto-generated method stub
		
	}

	
}
