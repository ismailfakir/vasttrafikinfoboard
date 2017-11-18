package net.cloudcentrik.vasttrafik;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.*;

public class VasttrafikApiUtils{

    public interface CallbackInterface {
        void onSuccess(List<Departure> departureList);
        void onFailed(Throwable error);
    }

    public static final String BASE_URL = "https://api.vasttrafik.se//bin/rest.exe/v2/";

    public static VasttrifikTokenService getVasttrifikService() {
        return VasttrafikRetrofitClient.getClient(BASE_URL).create(VasttrifikTokenService.class);
    }

    //get access token
    private static String getAccessToken()throws Exception{

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

    public static void getDepartures(String stopName,CallbackInterface callBack) throws Exception{

        String token=VasttrafikApiUtils.getAccessToken();

        Map<String, String> field=new HashMap<>();

        String today = new SimpleDateFormat("yy-MM-dd").format(new Date().getTime()+60*60000);
        String now = new SimpleDateFormat("HH:mm").format(new Date());
        field.put("id","vårväderstorget");
        //field.put("id",stopName);
        field.put("date",today);
        field.put("time",now);
        field.put("format","json");

        VasttrifikTokenService service=getVasttrifikService();

        Call<DepartureBoard> call1 = service.getDepartureBoard("Bearer "+token,field);

        //System.out.println(call1.request().toString());

        call1.enqueue(new Callback<DepartureBoard>() {
            @Override
            public void onResponse(Call<DepartureBoard> call, Response<DepartureBoard> response) {
                try {

                    List<Departure> departureList=response.body().getDepartureBoard().getDeparture();
                    //System.out.println(departureList.size());
                    callBack.onSuccess(departureList);
                    //printResponse(response);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DepartureBoard> call, Throwable t) {

                System.out.println(t.getMessage());
                callBack.onFailed(t);
            }
        });

    }

    private static void printResponse(Response<DepartureBoard> response){
        List<Departure> db=response.body().getDepartureBoard().getDeparture();

        for(Departure d:db){
            System.out.println(d.toString());
        }
    }

}
