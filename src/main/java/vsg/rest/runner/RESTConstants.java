package vsg.rest.runner;

/**
 * Created by Denis Orlov.
 */
abstract class RESTConstants {
	private RESTConstants(){
		throw new UnsupportedOperationException("Class should not be initialized");

	}
	static final String WCS_BASE_URI = "http://192.168.1.39:7003/cs/";
	static final String WCS_USER = "fwadmin";
	static final String WCS_PASSWORD = "xceladmin";
	static final int HTTP_STATUS_200 = 200;

}
