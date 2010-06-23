package decorator.gedo;

public class DocumentoTemplate extends DocumentoEspecial{

	private byte[] data;
	
	
	public DocumentoTemplate(Documento documentoEstandar) {
		this.documento= documentoEstandar;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}


	@Override
	public void cargaTemplate() {
		System.out.println("carga template de" +  this.getClass().getName());
		super.cargaTemplate();
	}


	
}
