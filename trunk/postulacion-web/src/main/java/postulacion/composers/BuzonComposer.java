package postulacion.composers;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.api.Listbox;

public class BuzonComposer  extends GenericForwardComposer {

	private static final long serialVersionUID = 5399936646246614222L;
	Label usernameLbl;
	Button logoutButton;
	ListModel model;
	ListitemRenderer renderer;
	Label info;
	Listbox postulacionesLBox;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable(comp.getId() + "Ctrl", this, true);
		usernameLbl.setValue((String) this.session.getAttribute("username"));
		List<ItemPostulacion> items= new ArrayList<ItemPostulacion>();
		ItemPostulacion item= new ItemPostulacion();
		item.setSolicitudNro("1");
		item.setAsunto("HOLA");
		items.add(item);
		postulacionesLBox.setModel(new BindingListModelList((List<ItemPostulacion>) items, true));
	}
	public void onLogout(){
		
	}
	
	public void showSelection(){
		info.setValue("selected item:" + ((String[])model.getElementAt(postulacionesLBox.getSelectedIndex()))[1]);
	}
	
	public class ItemPostulacion{
		private String solicitudNro;
		private String  asunto;
		private String  estado;
		private String  vencimiento;
		private String accion;
		public String getSolicitudNro() {
			return solicitudNro;
		}
		public void setSolicitudNro(String solicitudNro) {
			this.solicitudNro = solicitudNro;
		}
		public String getAsunto() {
			return asunto;
		}
		public void setAsunto(String asunto) {
			this.asunto = asunto;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public String getVencimiento() {
			return vencimiento;
		}
		public void setVencimiento(String vencimiento) {
			this.vencimiento = vencimiento;
		}
		public String getAccion() {
			return accion;
		}
		public void setAccion(String accion) {
			this.accion = accion;
		}
		
	}
}
