package dominio;

public class FichaNegra extends Ficha {

	private static final long serialVersionUID = -1680507609722064109L;

	public FichaNegra() {
		this.setColor(Ficha.NEGRA);
		setEntityType(getEntityType());
		this.setIdEntity( this.getColor() + incrementaID());
	}
	@Override
	public String getEntityType() {
		return FichaNegra.class.getName();
	}
}
