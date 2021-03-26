package com.example.weatheforecast;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CheckWeatherRecylerViewAdapter extends  RecyclerView.Adapter<CheckWeatherRecylerViewAdapter.CheckWeatherHolder>{

    ArrayList<Weather> foreCastArrayList;
    public CheckWeatherRecylerViewAdapter(ArrayList<Weather> fCast) {
        this.foreCastArrayList=fCast;
    }

    @NonNull
    @Override
    public CheckWeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_each_fore_cast, parent, false);
        CheckWeatherHolder checkWeatherHolder = new CheckWeatherHolder(view);

        return checkWeatherHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckWeatherHolder holder, int position) {

        Weather weather=foreCastArrayList.get(position);

        Picasso.get().load(weather.icon).into(holder.imageView);

        holder.temperature.setText(String.valueOf(weather.temp)+"F" );
        holder.temperatureMax.setText("Max"+String.valueOf(weather.temp_max)+"F");
        holder.temperatureMin.setText("Min"+String.valueOf(weather.temp_min)+"F");
        holder.description.setText(String.valueOf(weather.description));
        holder.humidity.setText("Humidity: "+String.valueOf(weather.humidity)+"%");
        Log.d("time","time"+weather.time);
        holder.time.setText(String.valueOf(weather.time));


    }

    @Override
    public int getItemCount() {
        return foreCastArrayList.size();
    }

    public static class CheckWeatherHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView temperature;
        TextView temperatureMax;
        TextView temperatureMin;
        TextView description;
        TextView humidity;
        TextView time;


        public CheckWeatherHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageViewIcon);
            temperature=itemView.findViewById(R.id.textViewWFTemp);
            temperatureMax=itemView.findViewById(R.id.textViewWFTempMax);
            temperatureMin=itemView.findViewById(R.id.textViewWFTempMin);
            description=itemView.findViewById(R.id.textViewWFDescription);
            humidity=itemView.findViewById(R.id.textViewWTHumidity);
            time=itemView.findViewById(R.id.textViewDateTime);




        }
    }
}
