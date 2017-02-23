package app.doordash.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import app.doordash.demo.adapter.RestaurantRecycler;
import app.doordash.demo.http.models.restaurants.Restaurant;
import app.doordash.demo.services.DoorDashService;
import app.doordash.demo.services.FavoriteService;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestaurantRecycler restaurantRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.favorites));
        recyclerView = (RecyclerView) findViewById(R.id.rest_list);
        restaurantRecycler = new RestaurantRecycler();
        recyclerView.setAdapter(restaurantRecycler);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        restaurantRecycler.setHideFavorite(true);

        List<Restaurant> restaurants = FavoriteService.get().getFavorites();
        if (restaurants != null) {
            restaurantRecycler.setList(restaurants); //set favorites
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
