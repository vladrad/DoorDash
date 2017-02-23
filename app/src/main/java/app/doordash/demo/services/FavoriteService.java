package app.doordash.demo.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import app.doordash.demo.http.models.restaurants.Restaurant;

/**
 * Created by Vladi on 2/21/17.
 */

public class FavoriteService {

    private static FavoriteService favoriteService;
    private HashMap<String, Restaurant> favorites;

    public void init() {
        favorites = new HashMap<>();
    }

    public static FavoriteService get() {
        if (favoriteService == null) {
            favoriteService = new FavoriteService();
        }
        return favoriteService;
    }

    public void addToFavorites(Restaurant restaurant) {
        favorites.put(restaurant.getId(), restaurant); //add to favorites
    }

    public void removeFromFavorites(String id) {
        favorites.remove(id);
    } //remove from map

    public boolean isFavourited(String id) {
        return favorites.containsKey(id); // check if it is in favorites
    }

    public List<Restaurant> getFavorites() {
        return Arrays.asList(favorites.values().toArray(new Restaurant[favorites.size()]));
    }

}
