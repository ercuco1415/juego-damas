package net.agweb.web;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;

public class CustomControllerClassNameHandlerMapping extends
		ControllerClassNameHandlerMapping {

	private static final Logger logger = Logger
			.getLogger(CustomControllerClassNameHandlerMapping.class);

	@Override
	protected String generatePathMapping(Class clazz) {
		// Es un controlador de forms?
		// x.y.zController => /y/z*
		// i.e. net.technisys.defaultapp.controllers.pagos.ElegirPagoController
		// => pagos/elegirpago*
		if (!MultiActionController.class.isAssignableFrom(clazz)) {
			String package_ = clazz.getPackage().toString();
			String prefix = package_.substring(package_.lastIndexOf(".") + 1);
			String controllerName = clazz.getSimpleName();
			controllerName = controllerName.substring(1, controllerName
					.indexOf("Controller"));
			String result = new StringBuffer()
					.append("/")
					.append(prefix)
					.append("/")
					.append(clazz.getSimpleName().substring(0, 1).toLowerCase())
					.append(controllerName).append("*").toString();
			logger.info("Class->" + clazz + "    mapping->" + result);
			
			return result;
		}
		return super.generatePathMapping(clazz);
	}

}
