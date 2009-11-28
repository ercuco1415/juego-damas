package dominio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import excepciones.CasilleroOcupadoException;

public class Maquina  extends Jugador{

	
	private static final long serialVersionUID = -5772012035090550171L;
	private Humano contrincante;
	public Maquina(){
		this.nombre = "MAQUINA";
		setIdEntity(this.getNombre());
		setEntityType(Maquina.class.getName());
	}
	public void agregarJugador(Jugador jugador) {
		contrincante = (Humano) jugador;
	}
	public void tenesTurno(){
		this.tieneTurno();
		this.contrincante.esperaTurno();
	}
	public Humano getContrincante() {
		return contrincante;
	}
	public void setContrincante(Humano contrincante) {
		this.contrincante = contrincante;
	}
	public void finTurno() {
		this.esperaTurno();
		this.contrincante.tieneTurno();
	}
	@Override
	public String getEntityType() {
		return Maquina.class.getName();
	}
	
	public void poneFichas(List<CasilleroNegro> casillerosNegros){
		Object[] vecCasilleros = casillerosNegros.toArray();
		CollectionUtils.reverseArray(vecCasilleros);
		this.setEntityTypeFicha(FichaBlanca.class);
		fichas = new ArrayList<Ficha>();
		try {
			for (int i = 0; i < 15; i++) {
				Casillero casillero = (Casillero) vecCasilleros[i];
				Ficha ficha = new FichaBlanca(i+1);
				ficha.addCasillero(casillero);
				ficha.setJugador(this);
				fichas.add(ficha);
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
	
	public static Jugador dameJugador(){
//		com.db4o.query.Predicate predicate = new com.db4o.query.Predicate(){
//		    public boolean match(Maquina maquina) {
//		        return maquina.getNombre().equals("MAQUINA");
//		    }
//		};
		List<Entidad> resultList = null;//managerDataBase.executeQuery(predicate);
		if(resultList != null && !resultList.isEmpty()){
			return (Jugador) (new ArrayList(resultList)).get(0);
		}
		return null;
	}
	
}
