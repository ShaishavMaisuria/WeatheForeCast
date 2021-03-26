package com.example.weatheforecast;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherForecastFragment extends Fragment {

    private static final String ARG_PARAM_CITY_COUNTRY_CHECK = "ARG_PARAM_CITY_COUNTRY_CHECK";
    private static final String TAG = "WeatherForecastFragment" ;


    private String mCityCountry;


    public WeatherForecastFragment() {
        // Required empty public constructor
    }


    public static WeatherForecastFragment newInstance(String cityCountry) {
        WeatherForecastFragment fragment = new WeatherForecastFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM_CITY_COUNTRY_CHECK, cityCountry);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCityCountry = getArguments().getString(ARG_PARAM_CITY_COUNTRY_CHECK);

        }
    }
    TextView title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_forecast, container, false);
        Log.d(TAG,"Succesful in weatherForecast"+mCityCountry);
        title=view.findViewById(R.id.textViewcityCountryTitle);
        recyclerView= view.findViewById(R.id.recylerViewForecast);
        recyclerView.setHasFixedSize(true);
        title.setText(mCityCountry);
        getCheckedCity();
        return view;
    }

    CheckWeatherRecylerViewAdapter adapter;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    private final OkHttpClient client = new OkHttpClient();
    void getCheckedCity(){

        Log.d(TAG,"inside GetCurrentWeather");

        HttpUrl url=  HttpUrl.parse("https://api.openweathermap.org/data/2.5/forecast").newBuilder()
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

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body= response.body().string();
                Log.d(TAG, "2");

                if(response.isSuccessful()) {


                    Log.d(TAG, "Successfull Response" + body);
                    try {
                        ForeCast foreCast = new ForeCast(body);
                        layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new CheckWeatherRecylerViewAdapter(foreCast);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.d(TAG, "Fail Response" + body);
                }

            }
        });
    }
}