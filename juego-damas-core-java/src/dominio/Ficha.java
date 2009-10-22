package dominio;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import excepciones.CasilleroOcupadoException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;


public class Ficha {

	private int id;
	private String color;
	private Casillero casillero;
	private Jugador jugador;
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
		this.id = this.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		boolean returnboolean = EqualsBuilder.reflectionEquals(this, obj);
		return returnboolean;
	}
	@Override
	public int hashCode() {
		int returnint = HashCodeBuilder.reflectionHashCode(this);
		return returnint;
	}
	@Override
	public String toString() {
		return "COLOR-FICHA: " + this.color + " CASILLERO: " + this.casillero;
	}
	public int getId() {
		return id;
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
		casillero.ficha = this;
	}
	public List<Casillero> dameCasillerosDisponibles(List<Casillero> casilleros){
		return this.casillero.getVecinos(this,casilleros);
	}
	public Casillero dameCasilleroDerecha( List<Casillero> casillerosNegros) throws NoExisteCasilleroDisponibleException{
		List<Casillero> casilleros = this.casillero.vecinoDiagonalDerecha(casillerosNegros, this.jugador.soyContrincante());
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
	public Casillero dameCasilleroIzquierda( List<Casillero> casillerosNegros) throws NoExisteCasilleroDisponibleException{
		List<Casillero> casilleros = this.casillero.vecinoDiagonalIzquierda(casillerosNegros,this.jugador.soyContrincante());
		if(casilleros == null || casilleros.isEmpty()){
			throw new NoExisteCasilleroDisponibleException("No existe casillero disponible");
		}
		return casilleros.get(0);
	}
	public void movete(Casillero casillero) throws CasilleroOcupadoException {
		this.casillero.ficha = null;
		addCasillero(casillero);
	}
	public Casillero comeFicha(Casillero casillero, List<Casillero> casillasNegras) throws NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException {
		if(!casillero.tenesFichaContrario(this.jugador)){
			throw new NoTieneFichaContrarioException("No hay ficha contrario en la casilla");
		}
		boolean esDerecha = esCasilleroDerecha(casillasNegras);
		if(!casillero.ficha.tePuedoComer(casillasNegras,esDerecha)){
			throw new NoPuedoComerFichaException("No se puede comer la ficha");
		}
		Casillero returnCasillero=casillero.dameCasilleroAnterior(this.jugador.soyContrincante(),casillasNegras,esDerecha);
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
			Casillero miCasillero = casilleroSeleccionado.ficha.dameCasilleroDerecha(this.jugador.getTablero().getNegros());
			result = this.casillero.equals(miCasillero);
		}
		return result;
	}
}
