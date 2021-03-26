package com.example.weatheforecast;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CurrentWeatherFragment extends Fragment {


    private static final String ARG_PARAM_CITY_COUNTRY = "ARG_PARAM_CITY_COUNTRY";
    private static final String TAG = "CurrentWeatherFragment" ;


    private String mCityCountry;


    public CurrentWeatherFragment() {
        // Required empty public constructor
    }


    public static CurrentWeatherFragment newInstance(String cityCountry) {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM_CITY_COUNTRY, cityCountry);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCityCountry = getArguments().getString(ARG_PARAM_CITY_COUNTRY);

        }
    }
    ImageView imageView;
    TextView temperature;
    TextView temperatureMax;
    TextView temperatureMin;
    TextView description;
    TextView humidity;
    TextView windSpeed;
    TextView windDegree;
    TextView cloudiness;
    TextView cityTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Current Weather");
        View view= inflater.inflate(R.layout.fragment_current_weather, container, false);
        cityTitle=view.findViewById(R.id.textViewCityCountryName);
        cityTitle.setText(mCityCountry);
        imageView=view.findViewById(R.id.imageView);
        temperature=view.findViewById(R.id.textViewTemperature);
        temperatureMax=view.findViewById(R.id.textViewTempMax);
        temperatureMin=view.findViewById(R.id.textViewTempMin);
        description=view.findViewById(R.id.textViewDescription);
        humidity=view.findViewById(R.id.textViewHumidity);
        windSpeed=view.findViewById(R.id.textViewWindSpeed);
        windDegree=view.findViewById(R.id.textViewWindDegree);
        cloudiness=view.findViewById(R.id.textViewCloudlness);

        Log.d(TAG, "Citys"+mCityCountry);
                getCurrentWeather();
        Log.d(TAG, "Done");


        view.findViewById(R.id.buttonCheckForecast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.currentWeatherToCheckWeather(mCityCountry);
            }
        });


        return view;
    }
    private final OkHttpClient client = new OkHttpClient();


    void getCurrentWeather(){
        Log.d(TAG,"inside GetCurrentWeather");

        HttpUrl url=  HttpUrl.parse("https://api.openweathermap.org/data/2.5/weather").newBuilder()
                .addQueryParameter("q",mCityCountry)
                .addQueryParameter("appid",getResources().getString(R.string.APIKEY))
                .addQueryParameter("mode","json")
                .addQueryParameter("units","imperial")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Log.d(TAG, "1");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "3");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body= response.body().string();
                Log.d(TAG, "2");

                if(response.isSuccessful()){



                    Log.d(TAG,"Successfull Response" + body);
                    try {
                        Weather weather= new Weather(body);
                        Log.d(TAG,"weather "+weather.toString());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Picasso.get().load(weather.icon).into(imageView);

                                temperature.setText(String.valueOf(weather.temp)+getResources().getString(R.string.StringF));
                                temperatureMax.setText(String.valueOf(weather.temp_max)+getResources().getString(R.string.StringF));
                                temperatureMin.setText(String.valueOf(weather.temp_min) +getResources().getString(R.string.StringF));
                                description.setText(String.valueOf(weather.description));
                                humidity.setText(String.valueOf(weather.humidity)+getResources().getString(R.string.StringPercentage));
                                windSpeed.setText(String.valueOf(weather.speed)+getResources().getString(R.string.StringSpeed));
                                windDegree.setText(String.valueOf(weather.deg)+getResources().getString(R.string.StringDegrees));
                                cloudiness.setText(String.valueOf(weather.clouds)+getResources().getString(R.string.StringPercentage));


                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                else{
                    Log.d(TAG,"Failure Response" + body);
                }
            }
        });

    }
    CurrentWeatherForecastListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof CurrentWeatherForecastListener){
            mListener=(CurrentWeatherForecastListener) context;
        } else{
            throw new RuntimeException(context.toString() + "must implement CurrentWeatherForecastListener");
        }
    }

    interface CurrentWeatherForecastListener {
        void currentWeatherToCheckWeather(String city);
    }

}