package app.doordash.demo.fragments;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import app.doordash.demo.http.models.restaurants.Restaurant;
import app.doordash.demo.services.DoorDashService;

import static app.doordash.demo.services.DoorDashService.DoorDashServiceEvent.RESTAURANT_SEARCH_EVENT;

/**
 * Created by Vladi on 2/21/17.
 */

public class MapViewFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;

    public void setUpMap() {
        getMapAsync(this);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        // Lets set the Default to Door Dash HQ
        LatLng sf = new LatLng(37.787072, -122.400451); // just set the main hq pin
        googleMap.addMarker(new MarkerOptions().position(sf).title("DoorDash HQ"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sf, 14f));
        DoorDashService.get().searchRestaurant(sf.latitude, sf.longitude); // start the service, service will shoot off event to map fragment and list fragment
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DoorDashService.DoorDashEvent doorDashEvent) {
        if (doorDashEvent.event.equals(RESTAURANT_SEARCH_EVENT)) {// wait for event
            List<Restaurant> restaurants = DoorDashService.get().getRestaurants();
            googleMap.clear();
            for (Restaurant restaurant : restaurants) {
                LatLng sf = new LatLng(restaurant.getAddress().getLat(), restaurant.getAddress().getLng()); // create pin on map
                googleMap.addMarker(new MarkerOptions().position(sf).title(restaurant.getName()));// create marker on map
            }
        }
    }


}
