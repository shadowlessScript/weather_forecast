package org.example;

import java.net.URI;
import java.net.HttpURLConnection;
import java.util.Arrays;

import org.example.ApiResponse;
import org.json.simple.JSONObject;

public class Main {

    public static void main(String[] args){


        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=-1.296276&longitude=36.832591&hourly=temperature_2m,weather_code&timezone=auto";
        System.out.println("\n"+apiUrl);
        try{

            URI api = new URI(apiUrl);
            System.out.println(api.getRawQuery());
            HttpURLConnection conn = (HttpURLConnection)api.toURL().openConnection();

            conn.setRequestMethod("GET");

            int apiResponseCode = conn.getResponseCode();

            if(apiResponseCode == HttpURLConnection.HTTP_OK){
                ApiResponse response = new ApiResponse(conn);

//                System.out.println(response.getJo());
                System.out.println(Arrays.toString(response.GetResponseKeys()));
//                System.out.println(response.extractInfo("timezone"));

                String[] weatherCode = new String[]{response.extractInfo(
                        "hourly").toString()};
                System.out.println(Arrays.toString(weatherCode));
            }else{
                System.out.printf("Api response failed, response code [ %s ]", Integer.toString(apiResponseCode));
                System.out.println();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
