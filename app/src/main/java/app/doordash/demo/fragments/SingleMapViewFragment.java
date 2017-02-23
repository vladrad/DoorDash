package app.doordash.demo.fragments;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;

import app.doordash.demo.http.models.restaurants.Restaurant;
import app.doordash.demo.services.DoorDashService;

/**
 * Created by Vladi on 2/21/17.
 */

public class SingleMapViewFragment extends MapViewFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;

    private Restaurant restaurant;

    public void setUpMap() {
        EventBus.getDefault().unregister(this);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng location = new LatLng(restaurant.getAddress().getLat(), restaurant.getAddress().getLng()); // just set the main hq pin
        googleMap.addMarker(new MarkerOptions().position(location).title(restaurant.getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16f));
        DoorDashService.get().searchRestaurant(location.latitude, location.longitude); // start the service, service will shoot off event to map fragment and list fragment
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
