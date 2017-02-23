package app.doordash.demo.controllers;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.doordash.demo.FavoritesActivity;
import app.doordash.demo.MainActivity;
import app.doordash.demo.R;
import app.doordash.demo.RestaurantActivity;
import app.doordash.demo.fragments.MapSearchFragment;
import app.doordash.demo.fragments.RestaurantListViewFragment;
import app.doordash.demo.http.models.restaurants.Restaurant;
import app.doordash.demo.services.DoorDashService;
import app.doordash.demo.util.FragmentLoader;

import static app.doordash.demo.controllers.MainNavigationController.NavEvent.RESTAURANT_INFO;

/**
 * Created by Vladi on 2/21/17.
 */

public class MainNavigationController {
    private MainActivity activity;
    private MapSearchFragment mapSearchFragment;
    private RestaurantListViewFragment restaurantListViewFragment;

    public static enum NavEvent {RESTAURANT_INFO}

    public static class NavigationEvent { //event sent to controller to change app
        public NavEvent event;
        public Restaurant restaurant;
    }

    public MainNavigationController(MainActivity activity) {
        this.activity = activity;
    }


    public void registerForEvents() {
        EventBus.getDefault().register(this);
    } // register for events

    public void unRegisterForEvents() { // unregister on resume
        EventBus.getDefault().unregister(this);
    }

    public void showDefaultMapView() { //shows map on default
        mapSearchFragment = new MapSearchFragment();
        mapSearchFragment.setRetainInstance(true);
        restaurantListViewFragment = new RestaurantListViewFragment();
        restaurantListViewFragment.setRetainInstance(true);

        FragmentLoader.addFragment(restaurantListViewFragment,activity,R.id.fragment_holder);
        FragmentLoader.addFragment(mapSearchFragment,activity,R.id.fragment_holder); //add both then hide list
        FragmentLoader.hideFragment(restaurantListViewFragment,activity);
    }

    public void toggleView() {
        if (mapSearchFragment.isVisible()) { //toggle by hiding and showing view
            FragmentLoader.hideFragment(mapSearchFragment,activity);
            FragmentLoader.showFragment(restaurantListViewFragment,activity);
        } else {
            FragmentLoader.hideFragment(restaurantListViewFragment,activity);
            FragmentLoader.showFragment(mapSearchFragment,activity);
        }
    }

    public void showFavoritesApp() {
        Intent favoritesActivity = new Intent(activity.getApplicationContext(), FavoritesActivity.class); //launch favorite activity
        activity.startActivity(favoritesActivity);
    }


    public void showRestaurantActivity() {
        Intent favoritesActivity = new Intent(activity.getApplicationContext(), RestaurantActivity.class); //launch favorite RestaurantApp
        activity.startActivity(favoritesActivity);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NavigationEvent navigationEvent) { //wait for navigation event
        if (navigationEvent.event.equals(RESTAURANT_INFO)) {// wait for event
            DoorDashService.get().setCurrentRestaurant(navigationEvent.restaurant);
            showRestaurantActivity();
        }
    }
}
