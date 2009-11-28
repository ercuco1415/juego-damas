package dominio;

import java.util.ArrayList;
import java.util.List;

import excepciones.CasilleroOcupadoException;

public class Humano extends Jugador {

	private static final long serialVersionUID = 3386459409473460634L;
	private Maquina contrincante;

	public void setContrincante(Maquina contrincante) {
		this.contrincante = contrincante;
	}

	public Humano() {
		this.nombre = "CHOMA";
		setIdEntity(this.getNombre());
		setEntityType(Humano.class.getName());
	}
	public void agregarJugador(Jugador jugador) {
		contrincante = (Maquina) jugador;
	}
	public void poneFichas(List<CasilleroNegro> casillerosNegros) {
		try {
			this.setEntityTypeFicha(FichaNegra.class);
			Object[] vecCasilleros = casillerosNegros.toArray();
			fichas = new ArrayList<Ficha>();
			for (int i = 0; i < 15; i++) {
				Casillero casillero = (Casillero) vecCasilleros[i];
				Ficha ficha = new FichaNegra(i+1);
				ficha.addCasillero(casillero);
				ficha.setJugador(this);
				fichas.add(ficha);
			}

		} catch (CasilleroOcupadoException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getEntityType() {
		return Humano.class.getName();
	}

	@Override
	public boolean soyContrincante() {
		if (this.getNombre().equals("CHOMA")) {
			return true;
		}
		return false;
	}

	public static Jugador dameJugador() {
		// com.db4o.query.Predicate predicate = new com.db4o.query.Predicate(){
		// public boolean match(Humano humano) {
		// return humano.getNombre().equals("CHOMA");
		// }
		// };
		List<Entidad> resultList = null;// managerDataBase.executeQuery(predicate);
		if (resultList != null && !resultList.isEmpty()) {
			System.out.println("cantidad de objetos: " + resultList.size());
			return (Jugador) (new ArrayList(resultList)).get(0);
		}
		return null;
	}

	@Override
	protected void finTurno() {
		this.esperaTurno();
		this.contrincante.tieneTurno();
	}

	@Override
	protected Jugador getContrincante() {
		return this.contrincante;
	}

	@Override
	public void tenesTurno(){
		this.tieneTurno();
		this.contrincante.esperaTurno();
	}
}
