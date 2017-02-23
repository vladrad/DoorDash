package app.doordash.demo.views;

import java.util.Enumeration;

import app.doordash.demo.http.models.menu.Items;


/**
 * Created by Vladi on 2/22/17.
 */

public class MenuModel {

    public enum ViewType {CATEGORY,FOOD}
    public ViewType viewType;
    public Items item;
    public String title;

}
