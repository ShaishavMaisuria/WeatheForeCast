package com.example.weatheforecast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CheckWeatherRecylerViewAdapter extends  RecyclerView.Adapter<CheckWeatherRecylerViewAdapter.CheckWeatherHolder>{
ForeCast foreCast;
    public CheckWeatherRecylerViewAdapter(ForeCast fCast) {
        this.foreCast=fCast;
    }

    @NonNull
    @Override
    public CheckWeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_app_inside_list_view, parent, false);
        CheckWeatherHolder checkWeatherHolder = new CheckWeatherHolder(view);

        return checkWeatherHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckWeatherHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class CheckWeatherHolder extends RecyclerView.ViewHolder{

        public CheckWeatherHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
