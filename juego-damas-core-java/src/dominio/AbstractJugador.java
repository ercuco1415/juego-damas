package dominio;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import excepciones.NoHayFichaEnCasilleroException;


public abstract class AbstractJugador {
	protected String nombre;
	protected int puntajeTotal;
	protected boolean finalizoJuego=false;
	protected boolean soyGanador=false;
	protected List<Ficha> fichas;
	protected boolean tieneTurno;
	public abstract void poneFichas(List<Casillero> list);
	public List<Ficha> getFichas() {
		return fichas;
	}
	public void agregarJugador(AbstractJugador jugador) {
		contrincante = jugador;
	}
	public void tenesTurno(){
		this.tieneTurno();
		this.contrincante.esperaTurno();
	}
	public void finTurno() {
		this.esperaTurno();
		this.contrincante.tieneTurno();
	}
	private AbstractJugador contrincante;
	public Ficha dameFicha(Casillero casillero) throws NoHayFichaEnCasilleroException{
		PredicateFicha predicado = new PredicateFicha();
		predicado.x = casillero.getX();
		predicado.y = casillero.getY();
		Collection<Ficha> returnFichas = CollectionUtils.select(fichas, predicado);
		if(returnFichas != null && !returnFichas.isEmpty()){
			return returnFichas.iterator().next();
		}else{
			throw new NoHayFichaEnCasilleroException("No hay una ficha en el casillero!");
		}
	}
	public void comeFicha(Ficha miFicha, Casillero casilleroContrario){
		if(casilleroContrario.ficha.sosDelContrario(this)){
			
		}
	}
	public class PredicateFicha implements Predicate {
		public int x;
		public int y;
		
		public boolean evaluate(Object arg0) {
			Ficha ficha = (Ficha) arg0;
			if (ficha.getCasillero().getX() == x && ficha.getCasillero().getY() == y) {
				return true;
			}
			return false;
		}
	}
	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
	}
	public boolean isTieneTurno() {
		return tieneTurno;
	}
	public void esperaTurno() {
		System.out.println("ESPERA TURNO: " + this.getNombre());
		this.tieneTurno = false;
	}
	public void tieneTurno() {
		System.out.println("TIENE TURNO: " + this.getNombre());
		this.tieneTurno = true;
	}
	
	protected void setSoyGanador() {
		this.soyGanador = true;
	}
	protected boolean isFinalizoJuego() {
		return finalizoJuego;
	}
	protected void setFinalizoJuego() {
		this.finalizoJuego = true;
	}
	
	public int getPuntajeTotal() {
		return puntajeTotal;
	}
	protected void empiezaPartido(){
		this.puntajeTotal = 0;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
			this.nombre = nombre;
	}
	public abstract boolean soyContrincante() ;
	
	
}
