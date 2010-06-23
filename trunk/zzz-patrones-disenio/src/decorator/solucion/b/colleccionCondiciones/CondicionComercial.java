package decorator.solucion.b.colleccionCondiciones;

import decorator.problema.BusinessException;

public abstract class CondicionComercial {

	public abstract void comprar(double monto)throws BusinessException;
	
	
}
