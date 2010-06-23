package decorator.solucion.a.subclasificar;

import decorator.problema.BusinessException;

public class ClientePromocion extends ClientePosta{

	private double montoMinimoPromocion;
	private boolean tienePromocion;
	
	public void comprar(double monto) throws BusinessException {
		super.comprar(monto);
		if (tienePromocion && monto > montoMinimoPromocion) {
			throw new BusinessException("Ha excedido el monto maximo");
		}
	}
}
