package dominio;

import java.util.List;

public class CasilleroBlanco extends Casillero {


	private static final long serialVersionUID = 7049810651060772262L;
	private static final String BLANCO = "BLANCO";
	
	public CasilleroBlanco(){
		setEntityType(CasilleroBlanco.class.getName());
	}
	public String getType(){
		return BLANCO;
	}
	@Override
	public String getEntityType() {
		return CasilleroBlanco.class.getName();
	}
	@Override
	public List<CasilleroNegro> getVecinos(Ficha ficha,
			List<CasilleroNegro> casillerosNegros) {
		System.out.println("No tiene vecinos de interes");
		return null;
	}
	
	
	
	@Override
	public List<CasilleroNegro> getCasillerosDisponibles() {
		throw new UnsupportedOperationException("under construction");
	}
	@Override
	public List<CasilleroNegro> vecinoDiagonalDerecha(
			List<CasilleroNegro> casillerosNegros, boolean soyContrincante,
			Class class1) {
		throw new UnsupportedOperationException("under construction");
	}
	@Override
	public List<CasilleroNegro> vecinoDiagonalDerechaAtras(
			List<CasilleroNegro> casillerosNegros, boolean soyContrincante,
			Class class1) {
		throw new UnsupportedOperationException("under construction");
	}
	@Override
	public List<CasilleroNegro> vecinoDiagonalIzquierda(
			List<CasilleroNegro> casillerosNegros, boolean soyContrincante,
			Class class1) {
		throw new UnsupportedOperationException("under construction");
	}
	@Override
	public List<CasilleroNegro> vecinoDiagonalIzquierdaAtras(
			List<CasilleroNegro> casillerosNegros, boolean soyContrincante,
			Class class1) {
		throw new UnsupportedOperationException("under construction");
	}
	@Override
	protected void ocupado() {
		throw new UnsupportedOperationException("under construction");
	}
	@Override
	protected void desOcupado() {
		throw new UnsupportedOperationException("under construction");
	}
	@Override
	public boolean isOcupada() {
		throw new UnsupportedOperationException("under construction");
	}
	@Override
	public Ficha obtenerFicha() {
		throw new UnsupportedOperationException("under construction");
	}
	
	
	
}
