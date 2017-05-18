package vsg.rest.runner;

import com.sun.jersey.api.client.ClientResponse;
import vsg.rest.client.RestClient;
import vsg.rest.exception.SSOCustomException;

import static vsg.rest.runner.RESTConstants.*;


/**
 * Created by Denis Orlov.
 */
public class RESTRunner {
	public static void main(String[] args) throws SSOCustomException {
		RestClient client = new RestClient(WCS_BASE_URI, WCS_USER, WCS_PASSWORD);
		ClientResponse responseOneAsset = client.get("/sites/AHS/types/AHSArticle/assets/1460376624353");
		if (responseOneAsset.getStatus() == HTTP_STATUS_200) {
			String output = responseOneAsset.getEntity(String.class);
			System.out.println("One asset response = " + output);
		}

		ClientResponse listOfAssetsBySite = client.get("/sites/AHS/types/AHSArticle/search");
		if (responseOneAsset.getStatus() == HTTP_STATUS_200) {
			String output = listOfAssetsBySite.getEntity(String.class);
			System.out.println("List of assets by site = " + output);
		}


	}
}
