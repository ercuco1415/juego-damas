package decorator.solucion.c.patronDecorator;

import decorator.problema.BusinessException;

public class ClienteSafeShop extends ClienteCondicionComercial{
	
	private double montoMaximoSafeShop;
	public ClienteSafeShop(ClientePosta clientePosta) {
		this.cliente = clientePosta;
	}
	public ClienteSafeShop(ClientePromocion clientePromocion) {
		this.cliente = clientePromocion;
	}
	public void comprar(double monto) throws BusinessException {
		if (monto > montoMaximoSafeShop) {
			throw new BusinessException("Ha excedido el monto maximo");
		}
		cliente.comprar(monto);
	}
}
