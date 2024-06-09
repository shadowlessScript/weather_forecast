package org.weather;

import java.net.URI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Main {

    public static void main(String[] args){
        
        String APIKEY = "7e0eca37de06f0ac287fc2764d27f029";
        String AppId = "javaWeatherMan";
        
        String apiUrl = String.format("https://api.open-meteo.com/v1/forecast?latitude=-1.296276&longitude=36.832591&hourly=temperature_2m,weather_code&timezone=auto");
        System.out.println("\n"+apiUrl);
        try{
            
            URI api = new URI(apiUrl);
            System.out.println(api.getRawQuery());
            HttpURLConnection conn = (HttpURLConnection)api.toURL().openConnection();

            conn.setRequestMethod("GET");

            int apiResponseCode = conn.getResponseCode();

            if(apiResponseCode == HttpURLConnection.HTTP_OK){
               BufferedReader  in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
               String InputLine;

               StringBuilder response = new StringBuilder();

               while((InputLine = in.readLine()) != null){
                    response.append(InputLine);
               }
               in.close();

               System.out.println("Api Response: " + response.toString());
            }else{
                System.out.printf("Api response failed, response code [ %s ]", Integer.toString(apiResponseCode));
                System.out.println();
            }

        }catch (Exception URISyntaxException){
            System.out.println("violates RFC 2396");
        }

    }
}
