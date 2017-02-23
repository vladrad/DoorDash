package app.doordash.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import app.doordash.demo.adapter.MenuRecycler;
import app.doordash.demo.fragments.SingleMapViewFragment;
import app.doordash.demo.services.DoorDashService;
import app.doordash.demo.util.FragmentLoader;
import app.doordash.demo.util.MenuBuilder;
import app.doordash.demo.views.MenuModel;

import static app.doordash.demo.services.DoorDashService.DoorDashServiceEvent.RESTAURANT_MENU_EVENT;


public class RestaurantActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MenuRecycler menuRecycler;
    private SingleMapViewFragment singleMapViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        menuRecycler = new MenuRecycler();
        singleMapViewFragment = new SingleMapViewFragment();
        singleMapViewFragment.setRestaurant(DoorDashService.get().getCurrentRestaurant());
        singleMapViewFragment.setUpMap(); //create and set up map
        FragmentLoader.loadFragment(singleMapViewFragment, this, R.id.map_fragment); //load map fragment
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.menu_list);
        recyclerView.setAdapter(menuRecycler); //set cycler
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        setTitle(DoorDashService.get().getCurrentRestaurant().getName());
        DoorDashService.get().getMenu(DoorDashService.get().getCurrentRestaurant().getId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DoorDashService.DoorDashEvent doorDashEvent) {
        if (doorDashEvent.event.equals(RESTAURANT_MENU_EVENT)) {// wait for menu event and then add it to list
            List<MenuModel> menuModelList = MenuBuilder.getBuiltMenu(DoorDashService.get().getCurrentMenu());
            menuRecycler.setList(menuModelList); // got food http call now set and display
        }
    }
}
