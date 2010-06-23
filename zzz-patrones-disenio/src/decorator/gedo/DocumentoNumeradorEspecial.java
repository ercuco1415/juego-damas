package decorator.gedo;

public class DocumentoNumeradorEspecial extends DocumentoEspecial{

	private String numeroGedo;
	
	public DocumentoNumeradorEspecial(Documento documentoEstandar) {
		this.documento=documentoEstandar;
	}

	public String getNumeroGedo() {
		return numeroGedo;
	}

	public void setNumeroGedo(String numeroGedo) {
		this.numeroGedo = numeroGedo;
	}

	@Override
	public void numera() {
		System.out.println("carga numera de" +  this.getClass().getName());
		super.numera();
	}

}
