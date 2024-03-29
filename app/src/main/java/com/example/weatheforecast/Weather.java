package com.example.weatheforecast;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
    double temp,temp_min,temp_max,humidity,speed,deg,clouds;
    String description,icon,time;

    public Weather(double temp, double temp_min, double temp_max, double humidity, double speed, double deg, double clouds, String description, String icon, String time) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.humidity = humidity;
        this.speed = speed;
        this.deg = deg;
        this.clouds = clouds;
        this.description = description;
        this.icon = icon;
        this.time = time;
    }

    public Weather(String body) throws JSONException {

            JSONObject rootObject = new JSONObject(body);
            rootObject.getJSONObject("main");
            temp= rootObject.getJSONObject("main").getDouble("temp");

        temp_max= rootObject.getJSONObject("main").getDouble("temp_max");
        temp_min= rootObject.getJSONObject("main").getDouble("temp_min");
        humidity= rootObject.getJSONObject("main").getDouble("humidity");
        speed= rootObject.getJSONObject("wind").getDouble("speed");
        deg= rootObject.getJSONObject("wind").getDouble("deg");

        clouds = rootObject.getJSONObject("clouds").getDouble("all");
        time="n/a";
        description= "N/A";

        if(rootObject.getJSONArray("weather").length() >0){
            description=rootObject.getJSONArray("weather").getJSONObject(0).getString("description");
//            http://openweathermap.org/img/wn/10d@2x.png
            icon= "https://openweathermap.org/img/wn/"+
                    rootObject.getJSONArray("weather").getJSONObject(0).getString("icon")
            +"@2x.png";
         }

    }

    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", humidity=" + humidity +
                ", speed=" + speed +
                ", deg=" + deg +
                ", clouds=" + clouds +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

}
