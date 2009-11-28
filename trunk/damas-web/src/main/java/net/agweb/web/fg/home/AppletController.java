package net.agweb.web.fg.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Controller simple que lleva al login
 *
 * @author alarosa
 *
 */
public class AppletController implements Controller {
	// ~ Methods
	// ------------------------------------------------------------------------------------

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		ModelAndView model = new ModelAndView();
		model.addObject("prueba", "prueba Applet");
		return model;
	}
}
