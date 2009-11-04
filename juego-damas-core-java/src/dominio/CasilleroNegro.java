package dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class CasilleroNegro extends Casillero {

	private static final String NEGRO = "NEGRO";

	public String getType() {
		return NEGRO;
	}

	public List<Casillero> getVecinos(Ficha ficha,
			List<Casillero> casillerosNegros) {
		List<Casillero> casilleros = vecinoDiagonalDerecha(casillerosNegros,
				!ficha.getColor().equals(BLANCO));
		casilleros.addAll(vecinoDiagonalIzquierda(casillerosNegros, !ficha
				.getColor().equals(BLANCO)));
		return casilleros;
	}
	
	public List<Casillero> getCasillerosDisponibles(){
		List<Casillero> casillerosList = new ArrayList<Casillero>();
		if(this.ficha != null ){
			casillerosList.addAll(vecinoDiagonalDerecha(this.ficha.getJugador().getTablero().getNegros(), this.ficha.getJugador().soyContrincante()));
			casillerosList.addAll(vecinoDiagonalDerechaAtras(this.ficha.getJugador().getTablero().getNegros(), this.ficha.getJugador().soyContrincante()));
			casillerosList.addAll(vecinoDiagonalIzquierdaAtras(this.ficha.getJugador().getTablero().getNegros(), this.ficha.getJugador().soyContrincante()));
			casillerosList.addAll(vecinoDiagonalIzquierda(this.ficha.getJugador().getTablero().getNegros(), this.ficha.getJugador().soyContrincante()));
		}
		return casillerosList;
	}
	public List<Casillero> vecinoDiagonalDerecha(
			List<Casillero> casillerosNegros, boolean soyContrincante) {

		PredicateCasilleroVecino predicado = new PredicateCasilleroVecino();
		if (!soyContrincante) {
			predicado.x = this.x + 1;
			predicado.y = this.y + 1;
			Collection casillerosVecinosMios = CollectionUtils.select(
					casillerosNegros, predicado);
			return new ArrayList<Casillero>(casillerosVecinosMios);
		}
		predicado.x = this.x - 1;
		predicado.y = this.y - 1;
		Collection casillerosVecinosContrincante = CollectionUtils.select(
				casillerosNegros, predicado);
		return new ArrayList<Casillero>(casillerosVecinosContrincante);
	}

	public List<Casillero> vecinoDiagonalDerechaAtras(
			List<Casillero> casillerosNegros, boolean soyContrincante) {

		PredicateCasilleroVecino predicado = new PredicateCasilleroVecino();
		if (!soyContrincante) {
			predicado.x = this.x + 1;
			predicado.y = this.y - 1;
			Collection casillerosVecinosMios = CollectionUtils.select(
					casillerosNegros, predicado);
			return new ArrayList<Casillero>(casillerosVecinosMios);
		}
		predicado.x = this.x - 1;
		predicado.y = this.y + 1;
		Collection casillerosVecinosContrincante = CollectionUtils.select(
				casillerosNegros, predicado);
		return new ArrayList<Casillero>(casillerosVecinosContrincante);
	}
	public List<Casillero> vecinoDiagonalIzquierda(
			List<Casillero> casillerosNegros, boolean soyContrincante) {
		PredicateCasilleroVecino predicado = new PredicateCasilleroVecino();
		if (!soyContrincante) {
			predicado.x = this.x - 1;
			predicado.y = this.y + 1;
			Collection casillerosVecinosMios = CollectionUtils.select(
					casillerosNegros, predicado);
			return new ArrayList<Casillero>(casillerosVecinosMios);
		}
		predicado.x = this.x + 1;
		predicado.y = this.y - 1;
		Collection casillerosVecinosContrincante = CollectionUtils.select(
				casillerosNegros, predicado);
		return new ArrayList<Casillero>(casillerosVecinosContrincante);
	}

	public List<Casillero> vecinoDiagonalIzquierdaAtras(
			List<Casillero> casillerosNegros, boolean soyContrincante) {
		PredicateCasilleroVecino predicado = new PredicateCasilleroVecino();
		if (!soyContrincante) {
			predicado.x = this.x - 1;
			predicado.y = this.y - 1;
			Collection casillerosVecinosMios = CollectionUtils.select(
					casillerosNegros, predicado);
			return new ArrayList<Casillero>(casillerosVecinosMios);
		}
		predicado.x = this.x + 1;
		predicado.y = this.y + 1;
		Collection casillerosVecinosContrincante = CollectionUtils.select(
				casillerosNegros, predicado);
		return new ArrayList<Casillero>(casillerosVecinosContrincante);
	}
	public class PredicateCasilleroVecino implements Predicate {
		public int x;
		public int y;

		public boolean evaluate(Object arg0) {
			Casillero casillero = (Casillero) arg0;
			if (casillero.getX() == x && casillero.getY() == y) {
				return true;
			}
			return false;
		}
	}
	
}
