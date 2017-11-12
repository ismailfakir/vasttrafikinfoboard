package net.cloudcentrik.vasttrafik;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class VasttrafikApiUtils {

    public static final String BASE_URL = "https://api.vasttrafik.se//bin/rest.exe/v2/";

    public static VasttrifikTokenService getVasttrifikService() {
        return VasttrafikRetrofitClient.getClient(BASE_URL).create(VasttrifikTokenService.class);
    }

    //get access token
    static String getAccessToken()throws Exception{

        String accessToken="";
        OAuthClientRequest request = OAuthClientRequest
                .tokenLocation("https://api.vasttrafik.se/token/")
                .setClientId("L3OkTVwZUdHCUVerTO7HSYqpBmoa")
                .setClientSecret("zRYffCS07dTaIc3L4c9T3Evfg2Qa")
                .setGrantType(GrantType.CLIENT_CREDENTIALS)
                .setScope("123458")
                .buildQueryMessage();
        //create OAuth client that uses custom http client under the hood
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        OAuthJSONAccessTokenResponse tokenResponseJson=oAuthClient.accessToken(request);
        accessToken=tokenResponseJson.getAccessToken();
        request=null;
        oAuthClient=null;

        System.out.println("Access Token : "+accessToken);
        return accessToken;
    }
}
