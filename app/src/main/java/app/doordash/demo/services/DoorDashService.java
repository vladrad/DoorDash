package app.doordash.demo.services;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import app.doordash.demo.http.HttpService;
import app.doordash.demo.http.models.menu.Menu;
import app.doordash.demo.http.models.restaurants.Restaurant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vladi on 2/21/17.
 */

public class DoorDashService {
    private static DoorDashService doorDashService;
    private List<Restaurant> restaurants;
    private List<Menu> currentMenu;
    private Restaurant currentRestaurant;


    public enum DoorDashServiceEvent {RESTAURANT_SEARCH_EVENT, RESTAURANT_MENU_EVENT}

    public class DoorDashEvent {
        public String errorMsg; // will be used for event handling
        public DoorDashServiceEvent event;
    }

    public static DoorDashService get() { //
        if (doorDashService == null) { //getApi top event
            doorDashService = new DoorDashService();
            return doorDashService;
        } else {
            return doorDashService;
        }
    }

    public void searchRestaurant(double lat, double lng) {
        HttpService.getApi().getRestaurants(lat,lng).enqueue(new Callback<List<Restaurant>>() { // getApi the top games, this is the default http call
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                restaurants = response.body(); //set current locations
                DoorDashEvent doorDashEvent = new DoorDashEvent();
                doorDashEvent.event = DoorDashServiceEvent.RESTAURANT_SEARCH_EVENT;
                EventBus.getDefault().post(doorDashEvent); // send off event download was ok
            }
            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {

            }
        });
    }

    public void getMenu(String id) {
        HttpService.getApi().getMenu(id).enqueue(new Callback<List<Menu>>() { // getApi the top games, this is the default http call
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                currentMenu = response.body();
                DoorDashEvent doorDashEvent = new DoorDashEvent();
                doorDashEvent.event = DoorDashServiceEvent.RESTAURANT_MENU_EVENT;
                EventBus.getDefault().post(doorDashEvent); // send off event menu download was ok
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {

            }
        });
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }


    public Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void setCurrentRestaurant(Restaurant currentRestaurant) { //sets the currently selected restaurant
        this.currentRestaurant = currentRestaurant;
    }

    public List<Menu> getCurrentMenu() {
        return currentMenu;
    }
}
