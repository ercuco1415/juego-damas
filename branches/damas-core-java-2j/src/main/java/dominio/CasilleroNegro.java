package dominio;

import java.util.ArrayList;
import java.util.List;

import servicios.IObjectPersistenceService;
import servicios.utils.ServiceLocator;

public class CasilleroNegro extends Casillero {

	private static final long serialVersionUID = -3081298016886815927L;
	private static final String NEGRO = "NEGRO";
	private boolean ocupado;

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	public CasilleroNegro() {
		setEntityType(CasilleroNegro.class.getName());
	}

	public boolean isOcupada() {
		if (obtenerFicha() == null && this.getOcupado().equals(Boolean.FALSE))
			return false;
		return true;
	}

	public String getType() {
		return NEGRO;
	}

	@Override
	public String getEntityType() {
		return CasilleroNegro.class.getName();
	}

	public List<CasilleroNegro> getCasillerosDisponibles() {
		List<CasilleroNegro> casillerosList = new ArrayList<CasilleroNegro>();
		Ficha ficha = obtenerFicha();
		if (ficha != null) {
			casillerosList.addAll(obtenerCasillerosDesocupadosDerecha(ficha.getColor(),true));
			List<CasilleroNegro> casillerosPosterioresDerecha = obtenerCasillerosOcupadosAdelanteDerecha(ficha.getColor());
			for(Casillero casillero: casillerosPosterioresDerecha){
				String color = (ficha.getColor().equals(Ficha.BLANCA))?Ficha.NEGRA:Ficha.BLANCA;
				casillerosList.addAll(casillero.obtenerCasillerosDesocupadosIzquierda(color, false));
			}
			casillerosList.addAll(obtenerCasillerosDesocupadosIzquierda(ficha.getColor(),true));
			List<CasilleroNegro> casillerosPosterioresIzquierda = obtenerCasillerosOcupadosAdelanteIzquierda(ficha.getColor());
			for(Casillero casillero: casillerosPosterioresIzquierda){
				String color = (ficha.getColor().equals(Ficha.BLANCA))?Ficha.NEGRA:Ficha.BLANCA;
				casillerosList.addAll(casillero.obtenerCasillerosDesocupadosDerecha(color, false));
			}
		}
		return casillerosList;
	}
	
	public List<CasilleroNegro> obtenerCasillerosDesocupadosDerecha(String color,boolean adelante) {
		List<CasilleroNegro> listResult= new ArrayList<CasilleroNegro>();
		Posicion pos = getPosicionDerecha(color, adelante);
		listResult.addAll(getObjectPersistenceService().obtenerCasillerosDisponibles(pos, color.getClass()));
		return listResult;
	}
	public List<CasilleroNegro> obtenerCasillerosDesocupadosIzquierda(String color,boolean adelante) {
		List<CasilleroNegro> listResult= new ArrayList<CasilleroNegro>();
		Posicion pos =  getPosicionIzquierda(color, adelante);
		listResult.addAll(getObjectPersistenceService().obtenerCasillerosDisponibles(pos, color.getClass()));
		return listResult;
	}
	
	public List<CasilleroNegro> obtenerCasillerosOcupadosAdelanteDerecha(String color) {
		List<CasilleroNegro> listResult= new ArrayList<CasilleroNegro>();

		Posicion pos = getPosicionDerecha(color, true);
		listResult.addAll(getObjectPersistenceService().obtenerCasillerosOcupadosOponente(pos, color));
		return listResult;
	}
	public List<CasilleroNegro> obtenerCasillerosOcupadosAdelanteIzquierda(String color) {
		List<CasilleroNegro> listResult= new ArrayList<CasilleroNegro>();
		Posicion pos =  getPosicionIzquierda(color, true);
		listResult.addAll(getObjectPersistenceService().obtenerCasillerosOcupadosOponente(pos, color));
		return listResult;
	}
	public Posicion getPosicionDerecha(String color, boolean adelante) {
		Posicion pos = new Posicion();
		if (adelante) {
			if (color.equals(Ficha.BLANCA)) {
				pos.setX(this.x - 1);
				pos.setY(this.y - 1);
			} else if (color.equals(Ficha.NEGRA)) {
				pos.setX(this.x + 1);
				pos.setY(this.y + 1);
			}
			return pos;
		}
		if (color.equals(Ficha.BLANCA)) {
			pos.setX(this.x - 1);
			pos.setY(this.y + 1);
		} else if (color.equals(Ficha.NEGRA)) {
			pos.setX(this.x + 1);
			pos.setY(this.y - 1);
		}
		return pos;
	}

	public Posicion getPosicionIzquierda(String color, boolean adelante) {
		Posicion pos = new Posicion();
		if (adelante) {
			if (color.equals(Ficha.BLANCA)) {
				pos.setX(this.x + 1);
				pos.setY(this.y - 1);
			} else if (color.equals(Ficha.NEGRA)) {
				pos.setX(this.x - 1);
				pos.setY(this.y + 1);
			}
			return pos;
		}
		if (color.equals(Ficha.BLANCA)) {
			pos.setX(this.x + 1);
			pos.setY(this.y + 1);
		} else if (color.equals(Ficha.NEGRA)) {
			pos.setX(this.x - 1);
			pos.setY(this.y - 1);
		}
		return pos;
	}
	public Ficha obtenerFicha() {
		return getObjectPersistenceService().obtenerFicha(this);
	}

	@Override
	protected void ocupado() {
		this.ocupado = Boolean.TRUE;
		getObjectPersistenceService().guarda(this);

	}

	@Override
	protected void desOcupado() {
		this.ocupado = Boolean.FALSE;
		getObjectPersistenceService().guarda(this);
	}
	
}
