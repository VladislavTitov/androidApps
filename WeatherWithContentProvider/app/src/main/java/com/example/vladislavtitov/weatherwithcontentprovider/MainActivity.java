package com.example.vladislavtitov.weatherwithcontentprovider;

import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vladislavtitov.weatherwithcontentprovider.network.models.CityForecast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<CityForecast>>,
        SwipeRefreshLayout.OnRefreshListener, NewCityDialogCallback{

    public static final int WEATHER_ASYNC_LOADER_ID = 0;
    private Loader<List<CityForecast>> weatherAsyncLoader;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;

    private AlertDialog newCityDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.cities);
        cityAdapter = new CityAdapter();
        recyclerView.setAdapter(cityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CitiesNamesProvider.getInstance().queryCitiesNames(this);

        weatherAsyncLoader = getSupportLoaderManager().initLoader(WEATHER_ASYNC_LOADER_ID, null, this);

    }

    @Override
    public Loader<List<CityForecast>> onCreateLoader(int id, Bundle args) {
        Loader<List<CityForecast>> loader = null;
        if (id == WEATHER_ASYNC_LOADER_ID){
            loader = new WeatherAsyncLoader(MainActivity.this, CitiesNamesProvider.getInstance().provideCitiesNames());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<CityForecast>> loader, List<CityForecast> data) {
        swipeRefreshLayout.setRefreshing(false);
        cityAdapter.updateCityForecasts(data);
    }

    @Override
    public void onLoaderReset(Loader<List<CityForecast>> loader) {

    }

    @Override
    public void onRefresh() {
        weatherAsyncLoader.onContentChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_item){
            newCityDialog = NewCityDialogCreator.createNewCityDialog(MainActivity.this);
            newCityDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addNewCity() {
        TextInputEditText newCityNameInput = (TextInputEditText) newCityDialog.findViewById(R.id.new_city_name);
        CitiesNamesProvider.getInstance().addCityName(this, newCityNameInput.getText().toString());
        weatherAsyncLoader.onContentChanged();
    }

    private class CityAdapter extends RecyclerView.Adapter<CityHolder>{

        private List<CityForecast> cityForecasts;

        public CityAdapter() {
            cityForecasts = new ArrayList<>();
        }

        public void updateCityForecasts(List<CityForecast> cityForecasts){
            this.cityForecasts = cityForecasts;
            notifyDataSetChanged();
        }

        @Override
        public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
            return new CityHolder(view);
        }

        @Override
        public void onBindViewHolder(CityHolder holder, int position) {
            holder.bind(cityForecasts.get(position));
        }

        @Override
        public int getItemCount() {
            return cityForecasts.size();
        }
    }

    private class CityHolder extends RecyclerView.ViewHolder{

        private TextView cityName;
        private RecyclerView forecasts;
        private ForecastAdapter forecastAdapter;
        private View itemView;

        public CityHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.cityName = (TextView) itemView.findViewById(R.id.city_name);
            this.forecasts = (RecyclerView) itemView.findViewById(R.id.forecast);
        }

        public void bind(CityForecast forecast){

            cityName.setText(forecast.getCity().getName() + ", " + forecast.getCity().getCountry());

            forecastAdapter = new ForecastAdapter(forecast.getList());
            forecasts.setAdapter(forecastAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            forecasts.setLayoutManager(layoutManager);
        }

    }

}
