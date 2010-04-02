package examples2;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BoxBorder;

import examples3.Usuario;

public class NavomaticBorder extends Border {
	public NavomaticBorder(final String componentName) {
		super(componentName);
		add(new BoxBorder("navigationBorder"));
		add(new BoxBorder("bodyBorder"));
		Usuario usr = (Usuario) getSession().getApplication().getSessionStore().getAttribute(this.getRequest(),Usuario.class.getName());
		Label markupLabel = new Label("usernameLbl","Username: " + obtenerUsuario(usr.getId()).getUsername());
		markupLabel.setEscapeModelStrings(false);
		add(markupLabel);
	}

	private Usuario obtenerUsuario(String uriUsuario) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		usuario.setUsername("prueba");
		return usuario;
	}
}
