/**
 * Excepción de negocio
 */
package net.agweb.service.exceptions;

/**
 * @author Ale
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String PREFIX = "error.business.";

	private String errorCode;

	public BusinessException(String errorCode, String message) {
		super(message);
		this.errorCode = PREFIX + errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
