package app.doordash.demo.http;

import java.util.List;

import app.doordash.demo.http.models.menu.Menu;
import app.doordash.demo.http.models.restaurants.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Vladi on 2/21/17.
 */

public interface Api {

    @GET("restaurant/") //get all places in lat lng area
    Call<List<Restaurant>> getRestaurants(@Query("lat") double lat, @Query("lng")  double lng);

    @GET("restaurant/{id}/menu") //get info on place
    Call<List<Menu>> getMenu(@Path("id") String id);

}
