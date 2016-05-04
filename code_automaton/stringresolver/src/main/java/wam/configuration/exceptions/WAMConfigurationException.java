package wam.configuration.exceptions;


public class WAMConfigurationException extends Exception {

	public WAMConfigurationException(Exception e) {
		this.initCause(e);
	}

}
