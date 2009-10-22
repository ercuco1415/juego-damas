package dominio;

import java.util.ArrayList;
import java.util.List;

import excepciones.CasilleroOcupadoException;

public class Maquina  extends Jugador{

	private static final String BLANCO = "BLANCO";
	
	
	
	public Maquina(){
		this.nombre = "MAQUINA";
	}
	public void poneFichas(List<Casillero> casillerosNegros){
		try{
		
		List<Casillero> misCasilleros = new ArrayList<Casillero>(casillerosNegros);
		fichas = new ArrayList<Ficha>();
		int countFicha = 0;
		for (Casillero casillero : misCasilleros) {
			if (countFicha == 15) {
				break;
			}
			Ficha ficha = new Ficha();
			ficha.addCasillero(casillero);
			ficha.setColor(BLANCO);
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
		if(this.getNombre().equals("MAQUINA")){
			return false;
		}
	return true;
	}
	
}
