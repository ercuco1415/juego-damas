package dominio;

public class FichaBlanca extends Ficha {

	private static final long serialVersionUID = -1680507609722064109L;

	@Override
	public String getEntityType() {
		return FichaBlanca.class.getName();
	}
	public FichaBlanca(){}
	public FichaBlanca(int i) {
		this.setColor(Ficha.BLANCA);
		setEntityType(getEntityType());
		this.setIdEntity( this.getColor() + i);
	}

}
