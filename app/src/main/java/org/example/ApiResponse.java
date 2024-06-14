package org.example;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

public class ApiResponse{
    private HttpURLConnection conn;
    private JSONObject jo;

    public ApiResponse(HttpURLConnection conn){
        this.conn = conn;
        JsonResponse();
    }

    public void JsonResponse(){
       /*
        * Gets the JSON response from the API.*/ 
       try{ 
           BufferedReader  in = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
           String InputLine;

           StringBuilder response = new StringBuilder();

           while((InputLine = in.readLine()) != null){
               response.append(InputLine);
           }
           in.close();

           //                System.out.println("Api Response: " + response);
           JSONParser res = new JSONParser();
           this.jo = (JSONObject) res.parse(response.toString());
       }catch(Exception e){
           displayErr(e);
       }
    }

    public String[] GetResponseKeys(){
        /*
         * returns the keys of the jsonobject 
         * @returns {keyNames}*/

        int le = this.jo.size();
        String[] keyNames = new String[le];

        int counter = 0;
        for(Object key: this.jo.keySet()){
            keyNames[counter] = key.toString();
            counter++;
        }

        return keyNames;
    }
    
    public Object extractInfo(String key){
        return this.jo.get(key);
    }


    public JSONObject getJo(){
        /*returns the json response*/
        return this.jo;
    }
    
    private String displayErr(Exception e){
        // will be used to display errors. 
        return e.getMessage();

    }
}
