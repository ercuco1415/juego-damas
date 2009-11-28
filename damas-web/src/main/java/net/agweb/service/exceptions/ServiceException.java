/**
 * Excepción de servicio, no recuperable
 */
package net.agweb.service.exceptions;

/**
 * @author Ale
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private ServiceErrorCode errorCode;

	public ServiceException(Throwable t, ServiceErrorCode errorCode) {
		super(t);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode.toString();
	}

}
