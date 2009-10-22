package dominio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import excepciones.CasilleroOcupadoException;

public class Humano  extends Jugador{

	private static final String NEGRO = "NEGRO";
	
	public Humano(){
		this.nombre = "CHOMA";
	}
	public void poneFichas(List<Casillero> casillerosNegros){
		Object[] vecCasilleros = casillerosNegros.toArray();
		CollectionUtils.reverseArray(vecCasilleros);
		fichas = new ArrayList<Ficha>();
		try {
			int countFicha = 0;
			for (int i = 0; i < vecCasilleros.length; i++) {
				if (countFicha == 15) {
					break;
				}
				Casillero casillero = (Casillero) vecCasilleros[i];
				Ficha ficha = new Ficha();
				ficha.addCasillero(casillero);
				ficha.setColor(NEGRO);
				ficha.setJugador(this);
				fichas.add(ficha);
				countFicha++;
			}
		} catch (CasilleroOcupadoException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public boolean soyContrincante() {
		if(this.getNombre().equals("CHOMA")){
				return true;
		}
		return false;
	}
}
