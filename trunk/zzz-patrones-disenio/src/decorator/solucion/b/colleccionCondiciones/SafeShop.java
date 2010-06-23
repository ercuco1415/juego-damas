package decorator.solucion.b.colleccionCondiciones;

import decorator.problema.BusinessException;

public class SafeShop extends CondicionComercial{
	
	private double montoMaximoSafeShop;
	public void comprar(double monto) throws BusinessException {
		if (monto > montoMaximoSafeShop) {
			throw new BusinessException("Ha excedido el monto maximo");
		}
	}
}
