package decorator.solucion.c.patronDecorator;

import decorator.problema.BusinessException;
import decorator.problema.Cliente;

public abstract class ClienteCondicionComercial implements Cliente{

	protected Cliente cliente;
	public abstract void comprar(double monto)throws BusinessException;
	
	public void pagarVencimiento(double monto) {
		cliente.pagarVencimiento(monto);
	}
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
}
