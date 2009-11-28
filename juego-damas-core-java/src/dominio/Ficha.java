package dominio;

import java.util.List;

import excepciones.CasilleroOcupadoException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;


public abstract class Ficha extends Entidad{

	
	private static final long serialVersionUID = -6295947014468466302L;
	public static final String BLANCA = "fb";
	public static final String NEGRA = "fn";
	private static int ids;
	private String color;
	private Casillero casillero;
	private Jugador jugador;
	
	public void setCasillero(Casillero casillero) {
		this.casillero = casillero;
	}
	
	public static void initIds(){
		ids = 0;
	}
	protected static int incrementaID(){
		ids = ids+1;
		return ids;
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
		casillero.setFicha(this);
	}
	public void cambiaCasillero(Casillero casillero2) throws CasilleroOcupadoException {
		if(this.casillero != null && casillero2.isOcupada()){
			throw new CasilleroOcupadoException("El casillero se encuentra ocupado");
		}
		this.casillero = casillero2;
		casillero.cambiarFicha(this);
	}
	@Deprecated
	public List<Casillero> dameCasillerosDisponibles(List<Casillero> casilleros){
		return this.casillero.getVecinos(this,casilleros);
	}
	public List<Casillero> dameCasillerosDisponibles(){
		return this.casillero.getCasillerosDisponibles();
	}
	public Casillero dameCasilleroDerecha() throws NoExisteCasilleroDisponibleException{
		List<Casillero> casilleros = this.casillero.vecinoDiagonalDerecha(Tablero.dameTablero().getNegros(), this.jugador.soyContrincante());
		if(casilleros == null || casilleros.isEmpty()){
			throw new NoExisteCasilleroDisponibleException("No existe casillero disponible");
		}
		return casilleros.get(0);
	}
	public boolean tePuedoComer( List<Casillero> casillerosNegros, boolean esDerecha) {
		List<Casillero> casilleros=null;
		if(esDerecha){
			casilleros = this.casillero.vecinoDiagonalIzquierdaAtras(casillerosNegros, this.jugador.soyContrincante());
			if(casilleros == null || casilleros.isEmpty()){
				return false;
			}
			return true;
		}
		casilleros = this.casillero.vecinoDiagonalDerechaAtras(casillerosNegros, this.jugador.soyContrincante());
		if(casilleros == null || casilleros.isEmpty()){
			return false;
		}
		return true;
	}
	public Casillero dameCasilleroIzquierda() throws NoExisteCasilleroDisponibleException{
		List<Casillero> casilleros = this.casillero.vecinoDiagonalIzquierda(Tablero.dameTablero().getNegros(),this.jugador.soyContrincante());
		if(casilleros == null || casilleros.isEmpty()){
			throw new NoExisteCasilleroDisponibleException("No existe casillero disponible");
		}
		return casilleros.get(0);
	}
	public void movete(Casillero casillero) throws CasilleroOcupadoException {
		this.casillero.ficha = null;
		cambiaCasillero(casillero);
	}
	public Casillero comeFicha(Casillero casillero) throws NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException {
		if(!casillero.tenesFichaContrario(this.jugador)){
			throw new NoTieneFichaContrarioException("No hay ficha contrario en la casilla");
		}
		Tablero tablero = Tablero.dameTablero();
		boolean esDerecha = esCasilleroDerecha(tablero.getNegros());
		if(!casillero.ficha.tePuedoComer(tablero.getNegros(),esDerecha)){
			throw new NoPuedoComerFichaException("No se puede comer la ficha");
		}
		Casillero returnCasillero=casillero.dameCasilleroAnterior(this.jugador.soyContrincante(),tablero.getNegros(),esDerecha);
		casillero.eliminaFicha();
		return returnCasillero;
	}
	private boolean esCasilleroDerecha(List<Casillero> casillerosNegros) {
		List<Casillero> casilleros = casillero.vecinoDiagonalDerecha(casillerosNegros, this.jugador.soyContrincante());
		if(casilleros != null && !casilleros.isEmpty()){
			return true;
		}
		return false;
	}
	public void eliminate() {
		this.jugador.getFichas().remove(this);
	}
	public boolean esDeContrincante() {
		return this.jugador.soyContrincante();
	}
	public boolean isDerechaCasillero(Casillero casilleroSeleccionado) throws NoExisteCasilleroDisponibleException{
		boolean result=false;
		if(casilleroSeleccionado.isOcupada() && !casilleroSeleccionado.ficha.jugador.equals(this.jugador)){
			Casillero miCasillero = casilleroSeleccionado.ficha.dameCasilleroDerecha();
			result = this.casillero.equals(miCasillero);
		}
		return result;
	}
}
