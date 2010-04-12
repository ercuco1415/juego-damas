package postulacion.composers;

import java.util.HashMap;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class LoginComposer  extends GenericForwardComposer {

	private static final long serialVersionUID = 5399936646246614222L;
	Textbox usernameTxt;
	Textbox passwordTxt;
	Label usuarioLbl;
	Label pwdLbl;
	Label msgLbl;
	Button loginButton;
	
	
	public void onLogin(){
		String username = usernameTxt.getValue();
		String pwd = passwordTxt.getValue();
		if(Strings.isBlank(username) || Strings.isEmpty(pwd)){
			msgLbl.setValue("*Need user name and password!");
			return;
		}
		if(!"1234".equals(pwd)){
			msgLbl.setValue("*Wrong password!");
			return;
		}
		session.setAttribute("username",username);
		msgLbl.setValue("");
		msgLbl.setVisible(false);
		usernameTxt.setVisible(false);
		passwordTxt.setVisible(false);
		usuarioLbl.setVisible(false);
		pwdLbl.setVisible(false);
		loginButton.setVisible(false);
		Executions.sendRedirect("buzon/livelistbox.zul");
//		Executions.createComponents("buzon/livelistbox.zul",null,new HashMap<String,Object>());
	}
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable(comp.getId() + "Ctrl", this, true);
		
	}
	public void onLogout(){
		
	}
}
