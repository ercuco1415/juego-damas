package dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class Tablero {
	protected static final String NEGRO = "NEGRO";
	protected static final String BLANCO = "BLANCO";
	private List<Casillero> casilleros;

	public Tablero() {
		casilleros = new ArrayList<Casillero>();
		for (int y = 1; y <= 10; y++) {
			for (int x = 1; x <= 10; x++) {
				Casillero casillero = null;
				if (((x + y) % 2) == 0) {
					casillero = new CasilleroNegro();
				} else {
					casillero = new CasilleroBlanco();
				}
				casillero.setX(x);
				casillero.setY(y);
				casilleros.add(casillero);
			}
		}
	}

	public List<Casillero> getNegros() {
		Predicate pred = new Predicate() {
			public boolean evaluate(Object arg0) {
				Casillero casillero = (Casillero) arg0;
				if (casillero.getType().equals(NEGRO)) {
					return true;
				}
				return false;
			}
		};
		Collection reCollection = CollectionUtils.select(this.casilleros, pred);
		return new ArrayList<Casillero>(reCollection);
	}
	public List<Casillero> getCasillerosDesocupados() {
		Predicate pred = new Predicate() {
			public boolean evaluate(Object arg0) {
				Casillero casillero = (Casillero) arg0;
				if (casillero.getType().equals(NEGRO) && !casillero.isOcupada()) {
					return true;
				}
				return false;
			}
		};
		Collection reCollection = CollectionUtils.select(this.casilleros, pred);
		return new ArrayList<Casillero>(reCollection);
	}
	public List<Casillero> getBlancos() {
		Predicate pred = new Predicate() {
			public boolean evaluate(Object arg0) {
				Casillero casillero = (Casillero) arg0;
				if (casillero.getType().equals(BLANCO)) {
					return true;
				}
				return false;
			}
		};
		Collection reCollection = CollectionUtils.select(this.casilleros, pred);
		return new ArrayList<Casillero>(reCollection);
	}

	public List<Casillero> getCasilleros() {
		return casilleros;
	}

	public void setCasilleros(List<Casillero> casilleros) {
		this.casilleros = casilleros;
	}


}
