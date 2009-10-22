package dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import excepciones.CasilleroOcupadoException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoHayFichaEnCasilleroException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;


public abstract class Jugador {
	protected String nombre;
	protected int puntajeTotal;
	protected boolean finalizoJuego=false;
	protected boolean soyGanador=false;
	protected List<Ficha> fichas;
	protected boolean tieneTurno;
	private Tablero tablero;
	
	public Tablero getTablero() {
		return tablero;
	}
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	public abstract void poneFichas(List<Casillero> list);
	public List<Ficha> getFichas() {
		return fichas;
	}
	public void agregarJugador(Jugador jugador) {
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
	private Jugador contrincante;
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
	
	public  void moveFicha(Ficha ficha, Casillero casilleroSeleccionado) throws CasilleroOcupadoException {
		if(this.getFichas().contains(ficha)){
			ficha.movete(casilleroSeleccionado);
			this.finTurno();
		}
		
	}
	public List<Casillero> dameCasillerosLibres() throws CasilleroOcupadoException{
		List<Casillero> casillerosDisponibles = new ArrayList<Casillero>();
		for(Ficha ficha: this.getFichas()){
			casillerosDisponibles.addAll(ficha.dameCasillerosDisponibles(tablero.getNegros()));
		}
		return casillerosDisponibles;
	}
	
	public void comeFichaCon(Ficha ficha , Casillero casilleroSeleccionado) throws CasilleroOcupadoException, NoHayFichaEnCasilleroException, NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException{
		if(casilleroSeleccionado.isOcupada() && casilleroSeleccionado.ficha.getJugador().contrincante.equals(this)){
			boolean mueveDerecha = ficha.isDerechaCasillero(casilleroSeleccionado);
			Casillero casilleroContrincante=null;
			if(mueveDerecha){
				casilleroContrincante= ficha.dameCasilleroDerecha(tablero.getNegros());
			}else{
				casilleroContrincante= ficha.dameCasilleroIzquierda(tablero.getNegros());
			}
			Casillero casilleroNuevo = ficha.comeFicha(casilleroContrincante,tablero.getNegros());
			ficha.movete(casilleroNuevo);
			this.finTurno();
		}
	}
}
