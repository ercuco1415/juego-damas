package dominio;

import java.util.List;

import servicios.ObjectPersistenceService;

import excepciones.CasilleroOcupadoException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;


public abstract class Ficha extends Entidad{

	
	private static final long serialVersionUID = -6295947014468466302L;
	public static final String BLANCA = "fb";
	public static final String NEGRA = "fn";
	private String color;
	private Casillero casillero;
	private Jugador jugador;
	private ObjectPersistenceService objectPersistenceService= new ObjectPersistenceService();
	public void setCasillero(Casillero casillero) {
		this.casillero = casillero;
	}
	
	
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public boolean sosDelContrario(Jugador jugador){
		if(!jugador.equals(this.jugador)){
			return true;
		}
		return false;
	}
	public Ficha(){
		
	}
	

	

	@Override
	public boolean equals(Object obj) {
		if(!obj.getClass().equals(Ficha.class)){
			return false;
		}
		Ficha ficha = (Ficha) obj;
		if(!ficha.getId().equals(this.getId())){
			return false;
		}
		
		return true;
	}
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	@Override
	public String toString() {
		return this.getIdEntity();
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Casillero getCasillero() {
		return casillero;
	}
	public void addCasillero(Casillero casillero2) throws CasilleroOcupadoException {
		if(this.casillero != null && casillero2.isOcupada()){
			throw new CasilleroOcupadoException("El casillero se encuentra ocupado");
		}
		this.casillero = casillero2;
		this.casillero.ocupado();
	}
	public void cambiaCasillero(Casillero casillero2,Class clazz) throws CasilleroOcupadoException {
		if(this.casillero != null && casillero2.isOcupada()){
			throw new CasilleroOcupadoException("El casillero se encuentra ocupado");
		}
		this.casillero.desOcupado();
		casillero2.ocupado();
		this.casillero = casillero2;
		objectPersistenceService.guarda(this);
	}
	
	public List<CasilleroNegro> dameCasillerosDisponibles(){
		return this.casillero.getCasillerosDisponibles();
	}
	public CasilleroNegro dameCasilleroDerecha() throws NoExisteCasilleroDisponibleException{
		List<CasilleroNegro> casilleros = this.casillero.obtenerCasillerosDesocupadosDerecha(this.getColor(),true);
		if(casilleros == null || casilleros.isEmpty()){
			throw new NoExisteCasilleroDisponibleException("No existe casillero disponible");
		}
		return casilleros.get(0);
	}
	public boolean tePuedoComer( List<CasilleroNegro> casillerosNegros, boolean esDerecha) {
		List<CasilleroNegro> casilleros=null;
		if(esDerecha){
			casilleros = this.casillero.obtenerCasillerosDesocupadosIzquierda(this.getColor(),false);
			if(casilleros == null || casilleros.isEmpty()){
				return false;
			}
			return true;
		}
		casilleros = this.casillero.obtenerCasillerosDesocupadosDerecha(this.getColor(),false);
		if(casilleros == null || casilleros.isEmpty()){
			return false;
		}
		return true;
	}
	public Casillero dameCasilleroIzquierda() throws NoExisteCasilleroDisponibleException{
		List<CasilleroNegro> casilleros = this.casillero.obtenerCasillerosDesocupadosIzquierda(this.getColor(),true);
		if(casilleros == null || casilleros.isEmpty()){
			throw new NoExisteCasilleroDisponibleException("No existe casillero disponible");
		}
		return casilleros.get(0);
	}
	public void movete(Casillero casillero) throws CasilleroOcupadoException {
		cambiaCasillero(casillero,this.getJugador().getClass());
	}
	public Casillero comeFicha(Casillero casillero) throws NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException {
		if(!casillero.tenesFichaContrario(this.jugador)){
			throw new NoTieneFichaContrarioException("No hay ficha contrario en la casilla");
		}
		Tablero tablero = Tablero.dameTablero();
		boolean esDerecha = esCasilleroDerecha(casillero);
		Ficha ficha = objectPersistenceService.obtenerFicha((CasilleroNegro) casillero);
		if(!ficha.tePuedoComer(tablero.getNegros(),esDerecha)){
			throw new NoPuedoComerFichaException("No se puede comer la ficha");
		}
		Casillero returnCasillero=casillero.dameCasilleroAnterior(this.jugador.soyContrincante(),tablero.getNegros(),esDerecha,this.jugador.getEntityTypeFicha());
		casillero.eliminaFicha();
		return returnCasillero;
	}
	private boolean esCasilleroDerecha(Casillero casillero) {
		List<CasilleroNegro> casilleros = this.casillero.obtenerCasillerosDesocupadosDerecha(this.getColor(),true);
		if(casilleros != null && !casilleros.isEmpty()){
			return true;
		}
		return false;
	}
	public void eliminate() {
		this.jugador.getFichas().remove(this);
		ObjectPersistenceService objectPersistenceService = new ObjectPersistenceService();
		this.casillero.desOcupado();
		objectPersistenceService.guarda(this.jugador);
		this.casillero = null;
		objectPersistenceService.guarda(this);
	}
	public boolean esDeContrincante() {
		return this.jugador.soyContrincante();
	}
	public boolean isDerechaCasillero(Casillero casilleroSeleccionado) throws NoExisteCasilleroDisponibleException{
		boolean result=false;
		Ficha ficha = objectPersistenceService.obtenerFicha((CasilleroNegro) casilleroSeleccionado);
		if(casilleroSeleccionado.isOcupada() && !ficha.jugador.equals(this.jugador)){
			Casillero miCasillero = ficha.dameCasilleroDerecha();
			result = this.casillero.equals(miCasillero);
		}
		return result;
	}
}
