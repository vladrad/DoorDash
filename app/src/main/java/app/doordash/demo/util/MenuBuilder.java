package app.doordash.demo.util;

import java.util.ArrayList;
import java.util.List;

import app.doordash.demo.http.models.menu.Items;
import app.doordash.demo.http.models.menu.Menu;
import app.doordash.demo.http.models.menu.MenuCategories;
import app.doordash.demo.views.MenuModel;

import static app.doordash.demo.views.MenuModel.ViewType.CATEGORY;
import static app.doordash.demo.views.MenuModel.ViewType.FOOD;

/**
 * Created by Vladi on 2/22/17.
 */

public class MenuBuilder {

    public static List<MenuModel> getBuiltMenu(List<Menu> menu){ // builds menu for adapter a head of time
        List<MenuModel> menuModels = new ArrayList<>();
        for(Menu singleMenu : menu){
            List<MenuCategories> menuCategories = singleMenu.getMenuCategoriesList();
            for(MenuCategories singleCategory : menuCategories){
                MenuModel menuModel = new MenuModel();
                menuModel.viewType = CATEGORY; //its a catagory type view
                menuModel.title = singleCategory.getTitle(); // just needs title
                menuModels.add(menuModel);
                for(Items item : singleCategory.getItems()){
                    MenuModel singleItem = new MenuModel();
                    singleItem.viewType = FOOD; // its a food view
                    singleItem.item = item; // set item
                    menuModels.add(singleItem);
                }
            }
        }
        return menuModels;
    }
}
