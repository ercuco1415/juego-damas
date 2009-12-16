package presentacion.beans;

import java.io.Serializable;

public class ResultadoJugadaBean implements Serializable{

	private static final long serialVersionUID = -2950692402303763227L;
	private int fichasMaquina;
	private int fichasHumano;
	private int cantMovidasRealizadas;
	private int cantMovidasRealizadasSinComer;
	private String fichaQueMasComio;
	private int cantFichasComidasPorMejorFichas;
	private String tiempoJuego;
	private String tiempoEsperandoJugada;
	private String tiempoUtilizadoPorHumano;
	private String tiempoUtilizadoPorMaquina;
	public int getFichasMaquina() {
		return fichasMaquina;
	}
	public void setFichasMaquina(int fichasMaquina) {
		this.fichasMaquina = fichasMaquina;
	}
	public int getFichasHumano() {
		return fichasHumano;
	}
	public void setFichasHumano(int fichasHumano) {
		this.fichasHumano = fichasHumano;
	}
	public int getCantMovidasRealizadas() {
		return cantMovidasRealizadas;
	}
	public void setCantMovidasRealizadas(int cantMovidasRealizadas) {
		this.cantMovidasRealizadas = cantMovidasRealizadas;
	}
	public int getCantMovidasRealizadasSinComer() {
		return cantMovidasRealizadasSinComer;
	}
	public void setCantMovidasRealizadasSinComer(int cantMovidasRealizadasSinComer) {
		this.cantMovidasRealizadasSinComer = cantMovidasRealizadasSinComer;
	}
	public String getFichaQueMasComio() {
		return fichaQueMasComio;
	}
	public void setFichaQueMasComio(String fichaQueMasComio) {
		this.fichaQueMasComio = fichaQueMasComio;
	}
	public int getCantFichasComidasPorMejorFichas() {
		return cantFichasComidasPorMejorFichas;
	}
	public void setCantFichasComidasPorMejorFichas(
			int cantFichasComidasPorMejorFichas) {
		this.cantFichasComidasPorMejorFichas = cantFichasComidasPorMejorFichas;
	}
	public String getTiempoJuego() {
		return tiempoJuego;
	}
	public void setTiempoJuego(String tiempoJuego) {
		this.tiempoJuego = tiempoJuego;
	}
	public String getTiempoEsperandoJugada() {
		return tiempoEsperandoJugada;
	}
	public void setTiempoEsperandoJugada(String tiempoEsperandoJugada) {
		this.tiempoEsperandoJugada = tiempoEsperandoJugada;
	}
	public String getTiempoUtilizadoPorHumano() {
		return tiempoUtilizadoPorHumano;
	}
	public void setTiempoUtilizadoPorHumano(String tiempoUtilizadoPorHumano) {
		this.tiempoUtilizadoPorHumano = tiempoUtilizadoPorHumano;
	}
	public String getTiempoUtilizadoPorMaquina() {
		return tiempoUtilizadoPorMaquina;
	}
	public void setTiempoUtilizadoPorMaquina(String tiempoUtilizadoPorMaquina) {
		this.tiempoUtilizadoPorMaquina = tiempoUtilizadoPorMaquina;
	}
	
	
	
}
