package decorator.solucion.c.patronDecorator;

import decorator.problema.BusinessException;
import decorator.problema.Cliente;

public class ClientePromocion extends ClienteCondicionComercial{

	private double montoMinimoPromocion;
	private boolean tienePromocion;
	private double puntosPremio;
	private double puntosAcumulados;
	

	public ClientePromocion(double d, double e, Cliente cliente) {
		this.montoMinimoPromocion = d;
		this.puntosPremio= e;
		this.cliente=cliente;
	}


	public void comprar(double monto) throws BusinessException {
		cliente.comprar(monto);
		if (tienePromocion && monto > montoMinimoPromocion) {
			puntosAcumulados += puntosPremio;
		}
	}

	
}

