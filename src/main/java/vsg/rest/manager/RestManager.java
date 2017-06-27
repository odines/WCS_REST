package vsg.rest.manager;

import com.sun.jersey.api.client.ClientResponse;
import org.apache.commons.lang3.StringUtils;
import vsg.rest.client.RestClient;
import vsg.rest.exception.SSOCustomException;

/**
 * Created by Denis Orlov.
 */
public class RestManager {

	private  RestClient client;

	public RestManager(RestClient client) {
		this.client = client;
	}

	public ClientResponse lookupSites() throws SSOCustomException {
		client.authenticate();
		ClientResponse response = client.get("/sites");
		return response;
	}

	public ClientResponse lookupAssetsByType(AssetTypes assetType) throws SSOCustomException {
		String url;
		ClientResponse response = null;
		switch (assetType) {
			case AHS_ARTICLE:
				url = "/types/AHSArticle";
				break;
			case AHS_GUIDE:
				url = "/types/AHSGuide";
				break;
			case AHS_VIDEO:
				url = "/types/AHSVideo";
				break;
			default:
				throw new AssertionError("Unknow Asset Type");
		}

		if (StringUtils.isNotBlank(url)) {
			client.authenticate();
			response = client.get(url);
		}
		return response;
	}

	public ClientResponse lookupAssetById(String assetId, AssetTypes assetType) throws SSOCustomException {
		String url;
		ClientResponse response = null;
		switch (assetType) {
			case AHS_ARTICLE:
				url = "/sites/AHS/types/AHSArticle/assets/";
				break;
			case AHS_GUIDE:
				url = "/sites/AHS/types/AHSGuide/assets/";
				break;
			case AHS_VIDEO:
				url = "/sites/AHS/types/AHSVideo/assets/";
				break;
			default:
				throw new AssertionError("Unknow Asset Type");
		}
		if (StringUtils.isNotBlank(url)) {
			url += assetId;
			client.authenticate();
			response = client.get(url);
		}

		return response;
	}

	public RestClient getClient() {
		return client;
	}

	public void setClient(RestClient pClient) {
		client = pClient;
	}


}
