package com.example.weatheforecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ForeCast {

    double temp,temp_min,temp_max,humidity,speed,deg,clouds;
    String description,icon;
    ArrayList<ForeCast> foreCastArrayList;
    public ForeCast(String body) throws JSONException {
        JSONObject rootObject = new JSONObject(body);
        foreCastArrayList=new ArrayList<>();
        JSONArray foreCastJson = rootObject.getJSONArray("list");

        for(int i=0;i< foreCastJson.length();i++){

            JSONObject foreCastObject = foreCastJson.getJSONObject(i);

            temp= foreCastObject.getDouble("temp");

            temp_max= foreCastObject.getDouble("temp_max");
            temp_min=foreCastObject.getDouble("temp_min");
            humidity= foreCastObject.getDouble("humidity");
            speed= foreCastObject.getDouble("speed");
            deg= foreCastObject.getDouble("deg");

            clouds = foreCastObject.getDouble("all");


            description= "N/A";

            if(rootObject.getJSONArray("weather").length() >0){
                description=foreCastObject.getJSONArray("weather").getJSONObject(0).getString("description");
//            http://openweathermap.org/img/wn/10d@2x.png
                icon= "https://openweathermap.org/img/wn/"+
                        foreCastObject.getJSONArray("weather").getJSONObject(0).getString("icon")
                        +"@2x.png";
            }



        }




    }
}
