package net.agweb.web.fg.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import servicios.IJuegoDamasListener;
import servicios.utils.ServiceLocator;

/**
 * Controller inicia el juego.
 *
 * @author alarosa
 *
 */
public class InitController extends MultiActionController {
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		IJuegoDamasListener damasListener = getDamasListener();
		ModelAndView model = new ModelAndView();
		model.addObject("fichasNegras",damasListener.dameFichasNegras() );
		model.addObject("fichasBlancas",damasListener.dameFichasBlancas() );
		model.addObject("casilleros",damasListener.dameCasillerosNegros() );
		
		return model;
	}

	private IJuegoDamasListener getDamasListener() {
		return (IJuegoDamasListener) ServiceLocator.getInstance().getService(IJuegoDamasListener.class);
	}

	
}
