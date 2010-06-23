package decorator.gedo;

import java.util.List;

public class DocumentoFirma extends DocumentoEspecial{

	private List<String> firmantes;
	private List<String> funcionarios;
	
	public DocumentoFirma(Documento documentoEstandar) {
		this.documento= documentoEstandar;
	}

	public List<String> getFirmantes() {
		return firmantes;
	}

	public void setFirmantes(List<String> firmantes) {
		this.firmantes = firmantes;
	}

	public List<String> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<String> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@Override
	public void firma() {
		System.out.println(" firma de" +  this.getClass().getName());
		super.firma();
	}
	
}
