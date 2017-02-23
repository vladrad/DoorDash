package app.doordash.demo.http.models.menu;

import java.util.List;

/**
 * Created by Vladi on 2/22/17.
 */

public class MenuCategories {
    private String title;
    private List<Items> items; // all the food items

    public String getTitle() {
        return title;
    }

    public List<Items> getItems() {
        return items;
    }
}
