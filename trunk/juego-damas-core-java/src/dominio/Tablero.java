package dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import persistence.daos.CasilleroDao;

public class Tablero extends Entidad{
	
	private static final long serialVersionUID = 2131207467858074405L;
	protected static final String NEGRO = "NEGRO";
	protected static final String BLANCO = "BLANCO";
	private List<Casillero> casilleros;
	private String nombre="TABLERO";
	
	public String getNombre() {
		return nombre;
	}
	@Override
	public String getEntityType() {
		return Tablero.class.getName();
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tablero() {
		setIdEntity(this.getNombre());
		setEntityType(Tablero.class.getName());
		casilleros = new ArrayList<Casillero>();
		for (int y = 1; y <= 10; y++) {
			for (int x = 1; x <= 10; x++) {
				Casillero casillero = null;
				if (!(((x + y) % 2) == 0)) {
					casillero = new CasilleroNegro();
					casillero.setX(x);
					casillero.setY(y);
					casillero.generateId();
					
//					CasilleroDao casilleroDao = new CasilleroDao();
//					casilleroDao.create(casillero);
//					
					casilleros.add(casillero);
				} 
//				
//				else {
//					casillero = new CasilleroBlanco();
//				}
			}
		}
	}

	public List<Casillero> getNegros() {
		Predicate pred = new Predicate() {
			public boolean evaluate(Object arg0) {
				Casillero casillero = (Casillero) arg0;
				if (casillero.getType().equals(NEGRO)) {
					return true;
				}
				return false;
			}
		};
		Collection reCollection = CollectionUtils.select(this.casilleros, pred);
		return new ArrayList<Casillero>(reCollection);
	}
	public List<Casillero> getCasillerosDesocupados() {
		Predicate pred = new Predicate() {
			public boolean evaluate(Object arg0) {
				Casillero casillero = (Casillero) arg0;
				if (casillero.getType().equals(NEGRO) && !casillero.isOcupada()) {
					return true;
				}
				return false;
			}
		};
		Collection reCollection = CollectionUtils.select(this.casilleros, pred);
		return new ArrayList<Casillero>(reCollection);
	}
	public List<Casillero> getBlancos() {
		Predicate pred = new Predicate() {
			public boolean evaluate(Object arg0) {
				Casillero casillero = (Casillero) arg0;
				if (casillero.getType().equals(BLANCO)) {
					return true;
				}
				return false;
			}
		};
		Collection reCollection = CollectionUtils.select(this.casilleros, pred);
		return new ArrayList<Casillero>(reCollection);
	}

	public List<Casillero> getCasilleros() {
		return casilleros;
	}

	public void setCasilleros(List<Casillero> casilleros) {
		this.casilleros = casilleros;
	}
	public static Tablero dameTablero(){
//		com.db4o.query.Predicate predicate = new com.db4o.query.Predicate(){
//		    public boolean match(Tablero tablero) {
//		        return tablero.getNombre().equals("TABLERO");
//		    }
//		};
		List<Entidad> resultList = null;//managerDataBase.executeQuery(predicate);
		if(resultList != null && !resultList.isEmpty()){
			return (Tablero) (new ArrayList(resultList)).get(0);
		}
		return null;
	}

}
