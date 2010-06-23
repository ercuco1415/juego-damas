package decorator.gedo;

public class DocumentoEstandar implements Documento{

	private String numero;
	private String reparticion;
	private String nombre;
	
	public DocumentoEstandar(String nombre, String reparticion,String numero) {
		this.numero = numero;
		this.reparticion=reparticion;
		this.nombre=nombre;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getReparticion() {
		return reparticion;
	}
	public void setReparticion(String reparticion) {
		this.reparticion = reparticion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void subi(){
		System.out.println("subi estandar");
	}
	public void firma(){
		System.out.println("firma estandar");
	}
	public void numera(){
		System.out.println("numera estandar");
		
	}
	public void cargaTemplate() {
		System.out.println("cargaTemplate estandar");
	}
	
}
