package net.cloudcentrik.vasttrafik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.api.scripting.JSObject;
import okhttp3.*;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

import static net.cloudcentrik.vasttrafik.DisplayBoardFrame.createAndShowGUI;

/**
 * Hello world!
 *
 */
public class App
{
    private static VasttrifikTokenService vasttrafikService;
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Starting vasttrafik api" );

        vasttrafikService=VasttrafikApiUtils.getVasttrifikService();
        displayBoard();
    }

    private static void displayBoard() throws Exception{

        String token=VasttrafikApiUtils.getAccessToken();

        Map<String, String> field=new HashMap<>();
        field.put("id","vårväderstorget");



        String today = new SimpleDateFormat("yy-MM-dd").format(new Date().getTime()+60*60000);
        String now = new SimpleDateFormat("HH:mm").format(new Date());

        System.out.println("Request date "+today);
        System.out.println("Request time "+now);


        field.put("date",today);
        field.put("time",now);
        field.put("format","json");

        Call<DepartureBoard> call1 = vasttrafikService.getDepartureBoard("Bearer "+token,field);

        System.out.println(call1.request().toString());

        call1.enqueue(new Callback<DepartureBoard>() {
            @Override
            public void onResponse(Call<DepartureBoard> call, Response<DepartureBoard> response) {
                try {

                    //System.out.println(response.body().getDepartureBoard().getServerdate());

                    List<Departure> departures=response.body().getDepartureBoard().getDeparture();
                    printResponse(response);

                    for (Departure departure : departures){
                        //System.out.println(departure.toString());

                        //System.out.println(departure.getName()+" "+departure.getTime()+" "+departure.getDirection());
                    }

                    //Schedule a job for the event-dispatching thread:
                    //creating and showing this application's GUI.
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            createAndShowGUI(departures);
                        }
                    });



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DepartureBoard> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    private void printDeparture(Departure departure){

        System.out.println(departure.toString());

    }

    private static void printResponse(Response<DepartureBoard> response){
        List<Departure> db=response.body().getDepartureBoard().getDeparture();

        for(Departure d:db){
            /*System.out.println(d.getTime());
            System.out.println(d.getRtTime());
            System.out.println(d.getBooking());
            System.out.println(d.getAccessibility());
            System.out.println(d.getRtDate());
            System.out.println(d.getType());*/
            System.out.println(d.toString());

        }



    }
}
