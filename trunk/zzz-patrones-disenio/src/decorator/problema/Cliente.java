package decorator.problema;

public interface Cliente {

	public void comprar(double monto)throws BusinessException;
	public void pagarVencimiento(double monto);
}
