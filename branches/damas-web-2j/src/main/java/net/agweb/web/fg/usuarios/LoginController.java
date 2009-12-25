package net.agweb.web.fg.usuarios;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Controller simple que lleva al login
 * @author pantusap
 *
 */
public class LoginController implements Controller {
    //~ Methods ------------------------------------------------------------------------------------
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
		ModelAndView mav=new ModelAndView();
    	return mav;
    }
}
