package net.agweb.web.fg.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import servicios.JuegoDamasListener;
import dominio.Casillero;
import dominio.Ficha;

/**
 * Controller simple que lleva al login
 *
 * @author alarosa
 *
 */
public class IndexController implements Controller {
	// ~ Methods
	// ------------------------------------------------------------------------------------

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		JuegoDamasListener damasListener = new JuegoDamasListener();
		damasListener.init();
		
		ModelAndView model = new ModelAndView();
//		List<Casillero> casilleros = damasListener.dameCasillerosNegros();
//		System.out.println(casilleros);
//		
//		List<Ficha> fichasBlancas = damasListener.dameFichasBlancas();
//		List<Ficha> fichasNegras = damasListener.dameFichasNegras();
//		model.addObject("casilleros",casilleros );
//		model.addObject("fichasBlancas",fichasBlancas );
//		model.addObject("fichasNegras",fichasNegras );
		return model;
	}
}
