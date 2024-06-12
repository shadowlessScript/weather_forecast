package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.net.URI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Objects;

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
                BufferedReader  in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String InputLine;

                StringBuilder response = new StringBuilder();

                while((InputLine = in.readLine()) != null){
                    response.append(InputLine);
                }
                in.close();

//                System.out.println("Api Response: " + response);
                JSONParser res = new JSONParser();
                JSONObject jo = (JSONObject) res.parse(response.toString());
                int le = jo.size();
                String[] keyName = new String[le];

                int counter = 0;
                for(Object key: jo.keySet()){
                    keyName[counter] = key.toString();
                    counter++;
                }
            }else{
                System.out.printf("Api response failed, response code [ %s ]", Integer.toString(apiResponseCode));
                System.out.println();
            }

        }catch (Exception URISyntaxException){
            System.out.println("violates RFC 2396");
        }

    }
}
