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
	public List<Casillero> getVecinos(Ficha ficha,
			List<Casillero> casillerosNegros) {
		System.out.println("No tiene vecinos de interes");
		return null;
	}
	
	@Override
	public List<Casillero> vecinoDiagonalDerecha(
			List<Casillero> casillerosNegros, boolean soyContrincante) {
		throw new UnsupportedOperationException("under construction");
	}

	@Override
	public List<Casillero> vecinoDiagonalIzquierda(
			List<Casillero> casillerosNegros, boolean soyContrincante) {
		throw new UnsupportedOperationException("under construction");
	}

	@Override
	public List<Casillero> vecinoDiagonalDerechaAtras(
			List<Casillero> casillerosNegros, boolean soyContrincante) {
		throw new UnsupportedOperationException("under construction");
	}

	@Override
	public List<Casillero> vecinoDiagonalIzquierdaAtras(
			List<Casillero> casillerosNegros, boolean soyContrincante) {
		throw new UnsupportedOperationException("under construction");
	}

	@Override
	public List<Casillero> getCasillerosDisponibles() {
		throw new UnsupportedOperationException("under construction");
	}
	
}
