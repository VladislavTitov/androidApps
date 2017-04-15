package com.example.vladislavtitov.weatherwithcontentprovider;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vladislavtitov.weatherwithcontentprovider.network.models.List;


public class ForecastAdapter extends  RecyclerView.Adapter<ForecastAdapter.ForecastHolder>{

    private java.util.List<List> forecasts;

    public ForecastAdapter(java.util.List<List> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public ForecastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ForecastHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastHolder holder, int position) {
        holder.bind(forecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public class ForecastHolder extends RecyclerView.ViewHolder{

        private TextView temp;
        private TextView pressure;
        private TextView humidity;
        private TextView description;
        private TextView date;

        public ForecastHolder(View itemView) {
            super(itemView);
            temp = (TextView) itemView.findViewById(R.id.temp);
            pressure = (TextView) itemView.findViewById(R.id.pressure);
            humidity = (TextView) itemView.findViewById(R.id.humidity);
            description = (TextView) itemView.findViewById(R.id.descr);
            date = (TextView) itemView.findViewById(R.id.date);
        }

        public void bind(List list){
            temp.setText(String.valueOf(Math.round(list.getMain().getTemp() - 273.15)) + "°C");
            pressure.setText(String.valueOf(list.getMain().getPressure()) + " hPa");
            humidity.setText(String.valueOf(list.getMain().getHumidity()) + "%");
            description.setText(String.valueOf(list.getWeather().get(0).getDescription()));
            date.setText(list.getDtTxt());
        }

    }

}
