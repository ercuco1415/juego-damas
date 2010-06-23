package decorator.solucion.b.colleccionCondiciones;

import decorator.problema.BusinessException;

public class Promocion extends CondicionComercial{

	private double montoMinimoPromocion;
	private boolean tienePromocion;
	private double puntosPremio;
	private double puntosAcumulados;
	
	public void comprar(double monto) throws BusinessException {
		if (tienePromocion && monto > montoMinimoPromocion) {
			puntosAcumulados += puntosPremio;
		}
	}
}

