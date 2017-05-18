package vsg.rest.client;

import com.fatwire.wem.sso.SSO;
import com.fatwire.wem.sso.SSOException;
import com.fatwire.wem.sso.SSOSession;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import vsg.rest.exception.SSOCustomException;

import javax.ws.rs.core.MediaType;


/**
 * Created by Denis Orlov.
 */
public class RestClient {

	private Client client;
	private String baseUri;
	private String multiticket;
	private WebResource webResource;
	private boolean connected = false;
	private String username;
	private String password;

	public RestClient(String baseUri, String username, String password) throws SSOCustomException{
		this.client = new Client();
		this.baseUri = baseUri;
		this.username = username;
		this.password = password;
		this.webResource = client.resource(baseUri + "REST/");
		authenticate();
	}

	public void authenticate() throws SSOCustomException {
		try {
			SSOSession session = SSO.getSSOSession(baseUri);
			String multiticket = session.getMultiTicket(username, password);
			webResource = webResource.queryParam("multiticket", multiticket);

		} catch (SSOException e) {
			throw new SSOCustomException(e);
		}
	}

	public ClientResponse get(String path) {
		WebResource getResource = this.webResource.path(getRelativePath(path));
		WebResource.Builder builder = getBuilder(getResource);
		return builder.get(ClientResponse.class);

	}

	private WebResource.Builder getBuilder(WebResource getResource) {
		WebResource.Builder builder = getResource.accept(MediaType.APPLICATION_XML);
		builder = builder.header("Pragma", "auth-redirect=false");
		builder = builder.header("X-CSRF-Token", multiticket);
		return builder;
	}


	private String getRelativePath(String path) {
		if (path.startsWith(baseUri)) {
			path = path.replace(baseUri + "REST", "");
		}
		return path;
	}

}
