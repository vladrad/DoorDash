package app.doordash.demo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import app.doordash.demo.R;
import app.doordash.demo.services.DoorDashService;
import app.doordash.demo.util.FragmentLoader;

/**
 * Created by Vladi on 2/21/17.
 */

public class MapSearchFragment extends Fragment {

    private MapViewFragment mapViewFragment;
    private Button searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.map_fragment,container,false);
        searchButton = (Button) view.findViewById(R.id.search_button);
        mapViewFragment = new MapViewFragment(); //create map view fragment
        mapViewFragment.setUpMap(); // set up map
        FragmentLoader.loadFragment(mapViewFragment,getActivity(),R.id.map); //load fragment
        setUpSearchButton();
        return view;
    }


    public void setUpSearchButton(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapViewFragment.getGoogleMap() != null){
                    GoogleMap googleMap = mapViewFragment.getGoogleMap();
                    double lat = googleMap.getCameraPosition().target.latitude;
                    double lng  = googleMap.getCameraPosition().target.longitude;
                    DoorDashService.get().searchRestaurant(lat,lng); // start the service, service will shoot off event to map fragment and list fragment
                }
            }
        });
    }

}
