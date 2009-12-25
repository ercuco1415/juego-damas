package dominio;

import java.util.Date;

public class Partido extends Entidad{

	private static final long serialVersionUID = -3607399730557400357L;
	private String nombre;
	private Jugador humano;
	private Jugador maquina;
	private Tablero tablero;
	private int cantMovidasRealizadasMaquina;
	private int cantMovidasRealizadasSinComerMaquina;
	private Date inicioJuego;
	private Date inicioJugada;

	public Partido(){
		this.setEntityType(Partido.class.getName());
	}
	public Jugador getHumano() {
		return humano;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setHumano(Jugador humano) {
		this.humano = humano;
	}
	public Jugador getMaquina() {
		return maquina;
	}
	public void setMaquina(Jugador maquina) {
		this.maquina = maquina;
	}
	public Tablero getTablero() {
		return tablero;
	}
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	public int getCantMovidasRealizadasMaquina() {
		return cantMovidasRealizadasMaquina;
	}
	public void setCantMovidasRealizadasMaquina(int cantMovidasRealizadasMaquina) {
		this.cantMovidasRealizadasMaquina = cantMovidasRealizadasMaquina;
	}
	public int getCantMovidasRealizadasSinComerMaquina() {
		return cantMovidasRealizadasSinComerMaquina;
	}
	public void setCantMovidasRealizadasSinComerMaquina(
			int cantMovidasRealizadasSinComerMaquina) {
		this.cantMovidasRealizadasSinComerMaquina = cantMovidasRealizadasSinComerMaquina;
	}
	public Date getInicioJuego() {
		return inicioJuego;
	}
	public void setInicioJuego(Date inicioJuego) {
		this.inicioJuego = inicioJuego;
	}
	public Date getInicioJugada() {
		return inicioJugada;
	}
	public void setInicioJugada(Date inicioJugada) {
		this.inicioJugada = inicioJugada;
	}
	public void generateNombre() {
		this.nombre = this.getInicioJuego().toGMTString() +  "-" + this.getHumano().getId() + "-" + this.getMaquina().getId();
		setIdEntity(this.getNombre());
	}
	@Override
	public String getEntityType() {
		return Partido.class.getName();
	}
	
	
}
