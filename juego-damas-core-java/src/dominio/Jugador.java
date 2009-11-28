package dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import servicios.ObjectPersistenceService;

import excepciones.CasilleroOcupadoException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoHayFichaEnCasilleroException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;


public abstract class Jugador extends Entidad{
	
	private static final long serialVersionUID = -4378068140511460081L;
	protected String nombre;
	protected int puntajeTotal;
	protected boolean finalizoJuego=false;
	protected boolean soyGanador=false;
	protected boolean tieneTurno;
	
	private Class entityTypeFicha;
	
	public Class getEntityTypeFicha() {
		return entityTypeFicha;
	}
	public void setEntityTypeFicha(Class entityTypeFicha) {
		this.entityTypeFicha = entityTypeFicha;
	}
	protected List<Ficha> fichas;
	
	
	public abstract void poneFichas(List<CasilleroNegro> list);
	public List<Ficha> getFichas() {
		return fichas;
	}
	
	public boolean isSoyGanador() {
		return soyGanador;
	}
	public void setSoyGanador(boolean soyGanador) {
		this.soyGanador = soyGanador;
	}
	
	public void setPuntajeTotal(int puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}
	public void setFinalizoJuego(boolean finalizoJuego) {
		this.finalizoJuego = finalizoJuego;
	}
	public void setTieneTurno(boolean tieneTurno) {
		this.tieneTurno = tieneTurno;
	}
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
			finTurno();
		}
		
	}
	protected abstract void finTurno();
	
	public List<Casillero> dameCasillerosLibres() throws CasilleroOcupadoException{
		List<Casillero> casillerosDisponibles = new ArrayList<Casillero>();
		for(Ficha ficha: this.getFichas()){
			casillerosDisponibles.addAll(ficha.dameCasillerosDisponibles());
		}
		return casillerosDisponibles;
	}
	
	public void comeFichaCon(Ficha ficha , Casillero casilleroSeleccionado) throws CasilleroOcupadoException, NoHayFichaEnCasilleroException, NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException{
		ObjectPersistenceService objectPersistenceService= new ObjectPersistenceService();
		Ficha fichaCasilleroSel = objectPersistenceService.obtenerFicha((CasilleroNegro) casilleroSeleccionado);
		if(casilleroSeleccionado.isOcupada() && fichaCasilleroSel.getJugador().getContrincante().equals(this)){
			boolean mueveDerecha = ficha.isDerechaCasillero(casilleroSeleccionado);
			Casillero casilleroContrincante=null;
			if(mueveDerecha){
				casilleroContrincante= ficha.dameCasilleroDerecha();
			}else{
				casilleroContrincante= ficha.dameCasilleroIzquierda();
			}
			Casillero casilleroNuevo = ficha.comeFicha(casilleroContrincante);
			ficha.movete(casilleroNuevo);
			this.finTurno();
		}
	}
	
	public static Tablero dameTablero(){
		ObjectPersistenceService objectPersistenceService= new ObjectPersistenceService();
		return objectPersistenceService.obtenerTablero();
	}
	protected abstract Jugador getContrincante() ;
	public abstract void agregarJugador(Jugador jugadorHumano);
	public abstract void tenesTurno() ;
	
}
