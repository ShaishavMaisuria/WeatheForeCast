package com.example.weatheforecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForeCast {


    ArrayList<Weather> foreCastArrayList;
    public ForeCast(String body) throws JSONException {
        double temp,temp_min,temp_max,humidity,speed,deg,clouds;
        String description,icon;

        JSONObject rootObject = new JSONObject(body);
        foreCastArrayList=new ArrayList<>();
        JSONArray foreCastJson = rootObject.getJSONArray("list");

        for(int i=0;i< foreCastJson.length();i++){

            JSONObject foreCastObject = foreCastJson.getJSONObject(i);

            temp= foreCastObject.getJSONObject("main").getDouble("temp");

            temp_max= foreCastObject.getJSONObject("main").getDouble("temp_max");
            temp_min=foreCastObject.getJSONObject("main").getDouble("temp_min");
            humidity= foreCastObject.getJSONObject("main").getDouble("humidity");
            speed= foreCastObject.getJSONObject("wind").getDouble("speed");
            deg= foreCastObject.getJSONObject("wind").getDouble("deg");

            clouds = foreCastObject.getJSONObject("clouds").getDouble("all");


            description= "N/A";
            icon="";
            if(foreCastObject.getJSONArray("weather").length() >0){
                description=foreCastObject.getJSONArray("weather").getJSONObject(0).getString("description");
//            http://openweathermap.org/img/wn/10d@2x.png
                icon= "https://openweathermap.org/img/wn/"+
                        foreCastObject.getJSONArray("weather").getJSONObject(0).getString("icon")
                        +"@2x.png";
            }

            long dateUnixFormat=foreCastObject.getLong("dt");
            Date date= new Date(dateUnixFormat*1000L);
            SimpleDateFormat simpedate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=simpedate.format(date);
            Weather weatherObject= new Weather(temp,temp_min,temp_max,humidity,speed,deg,clouds,description,icon,time);

            foreCastArrayList.add(weatherObject);

        }




    }
}
