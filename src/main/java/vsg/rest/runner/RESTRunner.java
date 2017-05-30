package vsg.rest.runner;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Blob;
import com.sun.jersey.api.client.ClientResponse;
import vsg.rest.client.RestClient;
import vsg.rest.exception.SSOCustomException;

import java.util.List;

import static vsg.rest.runner.RESTConstants.*;


/**
 * Created by Denis Orlov.
 */
public class RESTRunner {
	public static void main(String[] args) throws SSOCustomException {


		RestClient client = new RestClient(WCS_BASE_URI, WCS_USER, WCS_PASSWORD);
		ClientResponse responseOneAsset = client.get("/sites/FurnitureMedic/types/FMImage/assets/1460377889783");
		if (responseOneAsset.getStatus() == HTTP_STATUS_200) {
			AssetBean article = responseOneAsset.getEntity(AssetBean.class);
			if (null != article) {

				List<Attribute> articleAttributes = article.getAttributes();
				AssetBean newArticle = new AssetBean();
				newArticle.setName("REST API PUSHKA");

				newArticle.getAttributes().addAll(articleAttributes);
				Attribute blobAttribute = new Attribute();
				Attribute.Data blobData = new Attribute.Data();
				Blob blob = new Blob();
				blob.setHref("");

				ClientResponse responsePut = client.post(newArticle,"/sites/AHS/types/AHSArticle/assets/");
				System.out.println(responsePut.getStatus());

/*				for(int iterator=0; iterator<articleAttributes.size(); iterator++) {

					if(articleAttributes.get(iterator).getName().equals("Title")){
						System.out.println("article with id=" + article.getId() + " ,Title = " + articleAttributes.get(iterator).getData().getStringValue());
						Attribute.Data data = articleAttributes.get(iterator).getData();
						data.setStringValue("TEST META TITLE REST API");
						Attribute newAttr = new Attribute();
						newAttr.setName(articleAttributes.get(iterator).getName());
						newAttr.setData(data);

						article.getAttributes().set(iterator, newAttr);
						RestClient newClient = new RestClient(WCS_BASE_URI, WCS_USER, WCS_PASSWORD);

						ClientResponse response = newClient.put(article, "/sites/AHS/types/AHSArticle/assets/1460376624353");
						if(response.getStatus() == HTTP_STATUS_200) {
							System.out.println("meta title was successfully changed");
						} else {
							System.out.println(response.getStatus());
						}
					}
				}*/
			}
		}

		/*ClientResponse listOfAssetsBySite = client.get("/sites/AHS/types/AHSArticle/search");
		if (responseOneAsset.getStatus() == HTTP_STATUS_200) {
			String output = listOfAssetsBySite.getEntity(String.class);
			System.out.println("List of assets by site = " + output);
		}*/


/*		ClientResponse parentAsset = client.get("/sites/AHS/types/AHSArticle/search");
		if (parentAsset.getStatus() == HTTP_STATUS_200) {
			AssetsBean assetList = parentAsset.getEntity(AssetsBean.class);

			for (AssetInfo asset : assetList.getAssetinfos()) {


				System.out.println("Asset href = " + asset.getHref());

				ClientResponse assetResponse = client.get(asset.getHref());
				if (assetResponse.getStatus() == 200) {
					AssetBean assetBean = assetResponse.getEntity(AssetBean.class);
					List<Attribute> attributeList = assetBean.getAttributes();

					for (Attribute attribute : attributeList) {
						attribute.getData();
						*//*Attribute data = attribute.getData();
						System.out.println("Attribute name = " + attribute.getName() + ", " + "data= " + data.getDateValue()== null? true : false);*//*
					}
				} else {
					ErrorBean errorBean = assetResponse.getEntity(ErrorBean.class);
					System.out.println(errorBean.getMessage());
				}

			}
		}*/

	}
}
