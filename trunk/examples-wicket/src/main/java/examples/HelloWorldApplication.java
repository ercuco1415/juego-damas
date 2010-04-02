package examples;

import org.apache.wicket.protocol.http.WebApplication;

import examples3.Login;

public class HelloWorldApplication extends WebApplication {
	/**
	 * Constructor.
	 */
	public HelloWorldApplication() {

	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class getHomePage() {
		return Login.class;
	}
}
