package examples3;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import examples2.Page1;

public class Login extends WebPage {

	private TextField userIdField;
	private PasswordTextField passField;
	private Form form;

	public Login() {
		/**
		 * The first parameter to all Wicket component constructors is the same
		 * as the ID that is used in the template
		 */
		userIdField = new TextField("userId", new Model(""));
		passField = new PasswordTextField("password", new Model(""));
		/* Make sure that password field shows up during page re-render */
		passField.setResetPassword(false);
		form = new LoginForm("loginForm");
		form.add(userIdField);
		form.add(passField);
		add(form);
	}

	// Define your LoginForm and override onSubmit
	class LoginForm extends Form {
		public LoginForm(String id) {
			super(id);
		}

		@Override
		public void onSubmit() {
			String userId = Login.this.getUserId();
			String password = Login.this.getPassword();
			System.out.println("You entered User id " + userId
					+ " and Password " + password);
			Usuario usr = new Usuario();
			usr.setUsername(userId);
			getApplication().getSessionStore().setAttribute(this.getRequest(),Usuario.class.getName(),usr);
			setResponsePage(Page1.class);
		}
	}

	/** Helper methods to retrieve the userId and the password **/
	protected String getUserId() {
		return userIdField.getModelObjectAsString();
	}

	protected String getPassword() {
		return passField.getModelObjectAsString();
	}
}
