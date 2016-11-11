package com.example.vladislav.cityweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mCitiesList;
    CitiesAdapter adapter;
    Button newCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CitiesProvider.getInstance().restoreCity(this);

        mCitiesList = (RecyclerView) findViewById(R.id.cities);
        newCity = (Button) findViewById(R.id.newcity);

        adapter = new CitiesAdapter();
        updateCities();
        mCitiesList.setAdapter(adapter);
        mCitiesList.setLayoutManager(new LinearLayoutManager(this));

        newCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewCityActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateCities();
    }

    public void updateCities(){
        adapter.setCities();
    }

    public class CitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        List<String> cities;

        public void setCities() {
            this.cities = CitiesProvider.getInstance().getCities();
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_item, parent, false);
            return new CityHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof CityHolder){
                ((CityHolder) holder).getCityName().setText(cities.get(position));
                ((CityHolder) holder).getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                        intent.putExtra("city", cities.get(position));
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return cities.size();
        }
    }

    public class CityHolder extends RecyclerView.ViewHolder{

        TextView cityName;
        View itemView;

        public CityHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            cityName = (TextView) itemView.findViewById(R.id.city_name);
        }

        public View getItemView() {
            return itemView;
        }

        public TextView getCityName() {
            return cityName;
        }
    }
}
