package decorator.gedo;

public abstract class DocumentoEspecial implements Documento{

	Documento documento;

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public void cargaTemplate() {
		documento.cargaTemplate();
	}

	public void firma() {
		documento.firma();
	}

	public void numera() {
		documento.numera();
	}
	
}
