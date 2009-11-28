package dominio;

public class FichaNegra extends Ficha {

	private static final long serialVersionUID = -1680507609722064109L;

	public FichaNegra(){}
	public FichaNegra(int i) {
		this.setColor(Ficha.NEGRA);
		setEntityType(getEntityType());
		this.setIdEntity( this.getColor() + i);
	}
	@Override
	public String getEntityType() {
		return FichaNegra.class.getName();
	}
}
