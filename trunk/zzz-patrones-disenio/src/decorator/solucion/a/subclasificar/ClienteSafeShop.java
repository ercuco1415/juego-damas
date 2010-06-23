package decorator.solucion.a.subclasificar;

import decorator.problema.BusinessException;

public class ClienteSafeShop extends ClientePosta{
	
	private double montoMaximoSafeShop;
	public void comprar(double monto) throws BusinessException {
		if (monto > montoMaximoSafeShop) {
			throw new BusinessException("Ha excedido el monto maximo");
		}
		super.comprar(monto);
	}
}
