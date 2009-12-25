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
	public List<CasilleroNegro> getCasillerosDisponibles() {
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
	
	@Override
	public List<CasilleroNegro> obtenerCasillerosDesocupadosDerecha(
			String color, boolean adelante) {
		throw new UnsupportedOperationException("under construction");
	}
	@Override
	public List<CasilleroNegro> obtenerCasillerosDesocupadosIzquierda(
			String color, boolean adelante) {
		throw new UnsupportedOperationException("under construction");
	}
	
	
	
}
