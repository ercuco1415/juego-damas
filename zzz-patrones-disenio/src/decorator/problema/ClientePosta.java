package decorator.problema;
/**
   Ok, esta solución tiene algunos inconvenientes:
    * “Ensucia” la lógica del método comprar() cliente
	*  Agrega atributos que no todos los clientes necesitan: si el cliente no se adhiere a la
	promoción no necesita tener puntosAcumulados. 
	El problema no es guardar una referencia de más, 
	sino que el que lee la clase Cliente puede confundirse pensando
	que esa variable la necesita el cliente (esté o no en promoción).
 * @author alejandro
 *
 */
public class ClientePosta implements Cliente{

	private boolean usaSafeShop;
	private double montoMaximoSafeShop;
	private double montoMinimoPromocion;
	private boolean tienePromocion;
	private double puntosPremioPromocion;
	private double puntosAcumulados;

	public void comprar(double monto) throws BusinessException {
		if (usaSafeShop() && monto > montoMaximoSafeShop) {
			throw new BusinessException("Ha excedido el monto maximo");
		}
		if (tienePromocion && monto > montoMinimoPromocion) {
			puntosAcumulados += puntosPremioPromocion;
		}
	}

	public boolean usaSafeShop() {
		return usaSafeShop;
	}

	public void pagarVencimiento(double monto) {
		// TODO Auto-generated method stub
		
	}

	
}
