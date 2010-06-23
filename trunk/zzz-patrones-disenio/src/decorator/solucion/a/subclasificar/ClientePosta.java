package decorator.solucion.a.subclasificar;

import decorator.problema.BusinessException;
import decorator.problema.Cliente;

/**
 * @author alejandro
 *
 * Esta alternativa separa claramente las responsabilidades de cada condición comercial. Ya no
se ensucia al ClientePosta, sino que cada subclase delega en la superclase el comportamiento
default del comprar y además hace su propio agregado. Parece una trivialidad pero no lo es, el
atributo montoMaximoSafeShop pasa a llamarse montoMaximo a secas.

No obstante esta opción presenta una clara desventaja: no sólo es complicado hacer que un
cliente posta habilite/deshabilite el servicio de Safe Shop: esta solución no permite que
coexistan un cliente en promoción que también tenga safe shop, y es el motivo que lo
convierte en la solución menos deseable de todas.

 */
public abstract class ClientePosta implements Cliente{

	private boolean usaSafeShop;
	private double puntosPremioPromocion;
	private double puntosAcumulados;
	public void comprar(double monto) throws BusinessException {
		puntosAcumulados += puntosPremioPromocion;
	}

	public boolean usaSafeShop() {
		return usaSafeShop;
	}

	public void pagarVencimiento(double monto) {
		// TODO Auto-generated method stub
		
	}

	
}
