package app.doordash.demo.http.models.menu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vladi on 2/22/17.
 */

public class Menu {
    @SerializedName("menu_categories")
    private List<MenuCategories> menuCategoriesList; // holds all menu catergories

    public List<MenuCategories> getMenuCategoriesList() {
        return menuCategoriesList;
    }

}
