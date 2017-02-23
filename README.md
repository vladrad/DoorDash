# DoorDash
This is a sample app that will search restaurants within in a certain area. It will let you favorite your restaurants and list their menu. Please see attached app.apk.

![Alt text](http://i.imgur.com/3yZB30C.png “”)
![Alt text](http://i.imgur.com/l9LPU3F.png “”)
![Alt text](http://i.imgur.com/tKGvo3U.png “”)
![Alt text](http://i.imgur.com/EYgQ2Dy.png “”)


# Whats here?
­View all restaurants near a given location. You can assume a fixed location for now (required) <br />­Add restaurants to a favorite list (required) <br />
­Be able to choose where on a map you’d like to search restaurants (optional) <br />
# Whats missing?­Login with your consumer credentials (optional) <br />­Query restaurants based on their name (optional) <br />­Add items of a restaurant to an order cart (optional) <br />­Check out the order cart (optional) <br />

# About
On default the map will center over the DoorDash HQ building in SF. A download will kick off to determine which restaurants are in your area. You may move around and search other cities for restaurants. <br />
In the top right corner you have two action bar buttons. The heart will take you to your favorites list. The arrow will toggle a list view of what the current map search as returned for that area. Within the list view you can add restaurants to your favorites by tapping on the heart (you can view all your favorites by pressing on the heart in action bar). Tapping on a restaurant in the list view will open up a activity that displays a map and menu information.<br />

# Library used
Retrofit - used for http requests <br />
Picasso - used for loading images <br />
EventBus - used for communicating events between classes <br />
Android support libraries - used for various ui elements <br />

# What needs work?
The UI I made could use a slide out navigation menu <br />
MenuRecycler and RestaurantRecycler could both use a Base class that holds List<T> and overwrite any of the view functions <br />
MainNavigationController has some functions which can be condensed. <br />
HttpService you can probably move the server name into a cradle build config file so you can determine where the app points at build time <br />
HeaderInterceptor is ready for auth token but never got to it <br />
DoorDashService HAS NO ERROR handling when doing http requests, failed requests are just let through without notifying the user. I would set a error message and shoot off the error to he appropriate class. <br />
The Entire app is in portrait mode. It needs code to track changes when activity gets destroyed and recreated in landscape mode. The life cycle and navigation needs to be kept track of. <br />
FavoriteService is a simple add remove class that could probably be consolidated into <T> base class. Inheriting from it makes it easier as you can have the list be any class of your choosing. Also the entire FavoriteService is only in memory, ideally you want to keep this info in a local db and load it up in the init() call(SugarORM is pretty good library for this). <br />
FavoritesActivity has no way of showing that your list is currently empty, if the user has no favorites then the list is empty. <br />
MainActivity is light as much as I could keep it <br />
I think some layouts can be condensed and split up. I am loading straight layouts but some classes such as the list cells should be their own views (extending from view). This would clean up the recycler adapters since all logic of setting and finding layouts would be inside there. <br />
Github repo needs a ignore file that won’t check everything in. <br />

#Whats next?
I started creating the shopping cart by listing a menu in the Restaurant screen. I would probably use a floating button for the cart. As you add food items the cart notification badge will increase allowing the user to see how many items there are. <br />
I would add a pull out menu on the left side to allow the user to sign in. The auth token would be stored locally and would be used in the http interceptor. <br />
I would probably add a search view in the action bar in the main map. Searching there would populate the map and list with results. <br />


#Other
I included my debug.keystore key for you and app.apk in the base folder. The key has no password but is set up to use google maps api.






