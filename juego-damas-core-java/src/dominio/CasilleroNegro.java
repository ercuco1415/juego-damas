package dominio;

import java.util.ArrayList;
import java.util.List;

import servicios.ObjectPersistenceService;

public class CasilleroNegro extends Casillero {


	private static final long serialVersionUID = -3081298016886815927L;
	private static final String NEGRO = "NEGRO";
	private static final String BLANCO = "BLANCO";
	private boolean ocupado;
	
	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		this.ocupado = ocupado;
	}

	public CasilleroNegro(){
		setEntityType(CasilleroNegro.class.getName());
	}
	
	public boolean isOcupada(){
		if( obtenerFicha() == null && this.getOcupado().equals(Boolean.FALSE)) return false;
		return true;
	}
	public String getType() {
		return NEGRO;
	}
	@Override
	public String getEntityType() {
		return CasilleroNegro.class.getName();
	}
	public List<CasilleroNegro> getVecinos(Ficha ficha, List<CasilleroNegro> casillerosNegros) {
		List<CasilleroNegro> casilleros = vecinoDiagonalDerecha(casillerosNegros,!ficha.getColor().equals(BLANCO),ficha.getClass());
		casilleros.addAll(vecinoDiagonalIzquierda(casillerosNegros, !ficha.getColor().equals(BLANCO),ficha.getClass()));
		return casilleros;
	}
	
	public List<CasilleroNegro> getCasillerosDisponibles(){
		List<CasilleroNegro> casillerosList = new ArrayList<CasilleroNegro>();
		Ficha ficha = obtenerFicha();
		if(ficha!= null ){
			Tablero tablero = Tablero.dameTablero();
			casillerosList.addAll(vecinoDiagonalDerecha(tablero.getNegros(), ficha.getJugador().soyContrincante(),ficha.getClass()));
			casillerosList.addAll(vecinoDiagonalIzquierda(tablero.getNegros(), ficha.getJugador().soyContrincante(),ficha.getClass()));
			//TODO falta retornar los casilleros en caso de dama
//			if( !this.ficha.getJugador().soyContrincante()){
//				casillerosList.addAll(vecinoDiagonalDerecha(this.ficha.getJugador().getTablero().getNegros(), this.ficha.getJugador().soyContrincante()));
//				casillerosList.addAll(vecinoDiagonalIzquierda(this.ficha.getJugador().getTablero().getNegros(), this.ficha.getJugador().soyContrincante()));
//				
//			}else{
//				casillerosList.addAll(vecinoDiagonalDerechaAtras(this.ficha.getJugador().getTablero().getNegros(), this.ficha.getJugador().soyContrincante()));
//				casillerosList.addAll(vecinoDiagonalIzquierdaAtras(this.ficha.getJugador().getTablero().getNegros(), this.ficha.getJugador().soyContrincante()));
//			}
		}
		return casillerosList;
	}
	public List<CasilleroNegro> vecinoDiagonalDerecha(
			List<CasilleroNegro> casillerosNegros, boolean soyContrincante, Class class1) {

		int _x,_y;
		if (!soyContrincante) {
			_x = this.x - 1;
			_y = this.y - 1;
			
		}else{
			_x = this.x + 1;
			_y = this.y + 1;
			
		}
		return this.obtenerCasillerosDisponibles(_x, _y,class1);
	}

	public List<CasilleroNegro> vecinoDiagonalDerechaAtras(
			List<CasilleroNegro> casillerosNegros, boolean soyContrincante, Class class1) {
		int _x,_y;
		if (!soyContrincante) {
			_x = this.x - 1;
			_y = this.y + 1;
		}else{
			_x = this.x + 1;
			_y = this.y - 1;
		}
		return this.obtenerCasillerosDisponibles(_x, _y,class1);
	}
	public List<CasilleroNegro> vecinoDiagonalIzquierda(
			List<CasilleroNegro> casillerosNegros, boolean soyContrincante, Class class1) {
		int _x,_y;
		if (!soyContrincante) {
			_x = this.x + 1;
			_y = this.y - 1;
		}else{
			_x = this.x - 1;
			_y = this.y + 1;
		}
		return this.obtenerCasillerosDisponibles(_x, _y,class1);
	}

	public List<CasilleroNegro> vecinoDiagonalIzquierdaAtras(
			List<CasilleroNegro> casillerosNegros, boolean soyContrincante, Class class1){
		int _x,_y;
		if (!soyContrincante) {
			_x = this.x + 1;
			_y = this.y + 1;
		}else{
			_x = this.x - 1;
			_y = this.y - 1;
		}
		return this.obtenerCasillerosDisponibles(_x, _y,class1);
	}
	public List<CasilleroNegro> obtenerCasillerosDisponibles(int x,int y,Class clazz){
		ObjectPersistenceService objectPersistenceService= new ObjectPersistenceService();
		return objectPersistenceService.obtenerCasillerosDisponibles(x,y,clazz);
	}
	public Ficha obtenerFicha(){
		ObjectPersistenceService objectPersistenceService= new ObjectPersistenceService();
		return objectPersistenceService.obtenerFicha(this) ;
	}

	@Override
	protected void ocupado() {
		this.ocupado = Boolean.TRUE;
		ObjectPersistenceService objectPersistenceService = new ObjectPersistenceService();
		objectPersistenceService.guarda(this);
		
	}

	@Override
	protected void desOcupado() {
		this.ocupado = Boolean.FALSE;
		ObjectPersistenceService objectPersistenceService = new ObjectPersistenceService();
		objectPersistenceService.guarda(this);
	}
	
}
