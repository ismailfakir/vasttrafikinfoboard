package net.cloudcentrik.vasttrafik;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;

public interface VasttrifikTokenService {

    @POST("/token")
    Call<ResponseBody> getNewTokenPost();

    @GET("/bin/rest.exe/v2/departureBoard")
    Call<DepartureBoard> getDepartureBoard(@Header("Authorization") String authHeader,@QueryMap Map<String,String> qMap);


    @GET("/bin/rest.exe/v2/departureBoard")
    Call<ResponseBody> getData(@Header("Authorization") String authHeader,@QueryMap Map<String,String> qMap);

}
