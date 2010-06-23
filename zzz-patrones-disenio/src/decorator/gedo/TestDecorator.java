package decorator.gedo;

import junit.framework.TestCase;

public class TestDecorator extends TestCase {

	public void testDocumentoEstandar(){
		Documento doc = new DocumentoEstandar("nombre","reparticion","1234");
		doc.numera();
		System.out.println("testDocumentoEstandar OK");
	}
	public void testDocumentoFirma(){
		Documento doc = new DocumentoFirma( new DocumentoEstandar("nombre","reparticion","1234"));
		doc.firma();
		System.out.println("testDocumentoFirma OK");
	}
	public void testDocumentoNumeradorEspecial(){
		Documento doc = new DocumentoNumeradorEspecial(new DocumentoEstandar("nombre","reparticion","1234"));
		doc.numera();
		System.out.println("testDocumentoNumeradorEspecial OK");
	}
	public void testDocumentoTemplate(){
		Documento doc =new DocumentoTemplate( new DocumentoEstandar("nombre","reparticion","1234"));
		doc.numera();
		System.out.println("testDocumentoTemplate OK");
	}
	public void testDocumentoTemplateYNumeradorEspecial(){
		Documento doc =  new DocumentoNumeradorEspecial(new DocumentoTemplate( new DocumentoEstandar("nombre","reparticion","1234")));
		doc.numera();
		doc.cargaTemplate();
		System.out.println("testDocumentoTemplateYNumeradorEspecial OK");
	}
}
