package app.doordash.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import app.doordash.demo.controllers.MainNavigationController;
import app.doordash.demo.services.FavoriteService;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private MainNavigationController mainNavigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainNavigationController = new MainNavigationController(this);
        mainNavigationController.showDefaultMapView();// show the default view
        FavoriteService.get().init(); //create the favorites list
    }

    @Override
    public void onResume() {
        super.onResume();
        mainNavigationController.registerForEvents();
    }

    @Override
    public void onPause() {
        super.onPause();
        mainNavigationController.unRegisterForEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_toggle, menu); // this handles our favorites list and toggle
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toggle_navigation) {
            mainNavigationController.toggleView(); // toggle between list and view
            return true;
        }

        if (id == R.id.favorites) {
            mainNavigationController.showFavoritesApp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



