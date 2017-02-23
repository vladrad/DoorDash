package app.doordash.demo.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import app.doordash.demo.R;
import app.doordash.demo.adapter.RestaurantRecycler;
import app.doordash.demo.http.models.restaurants.Restaurant;
import app.doordash.demo.services.DoorDashService;

import static app.doordash.demo.services.DoorDashService.DoorDashServiceEvent.RESTAURANT_SEARCH_EVENT;

/**
 * Created by Vladi on 2/21/17.
 */

public class RestaurantListViewFragment extends BaseEventFragment {

    private RecyclerView recyclerView;
    private RestaurantRecycler restaurantRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rest_list);
        restaurantRecycler = new RestaurantRecycler();
        recyclerView.setAdapter(restaurantRecycler); // get list and set adapter
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        List<Restaurant> restaurants = DoorDashService.get().getRestaurants();
        if (restaurants != null)
            restaurantRecycler.setList(restaurants); //set any data that may have been there

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DoorDashService.DoorDashEvent doorDashEvent) {
        if (doorDashEvent.event.equals(RESTAURANT_SEARCH_EVENT)) {// wait for event
            List<Restaurant> restaurants = DoorDashService.get().getRestaurants();
            restaurantRecycler.setList(restaurants); //set new data
        }
    }


}
