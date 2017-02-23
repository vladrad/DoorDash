package app.doordash.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import app.doordash.demo.R;
import app.doordash.demo.controllers.MainNavigationController;
import app.doordash.demo.http.models.restaurants.Restaurant;
import app.doordash.demo.services.FavoriteService;

import static app.doordash.demo.controllers.MainNavigationController.NavEvent.RESTAURANT_INFO;

/**
 * Created by Vladi on 2/21/17.
 */

public class RestaurantRecycler extends RecyclerView.Adapter<RestaurantRecycler.ViewHolder> {
    private List<Restaurant> list;
    private Context context;
    private boolean hideFavorite;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView logo; //my view holder //holds image
        public ImageView favorite;
        public TextView title;//all sub info is here
        public TextView tags;
        public TextView address;
        public RelativeLayout infoHolder;

        public ViewHolder(View v) {
            super(v);
        }

        public void setUpOnClick() {
            infoHolder.setOnClickListener(this); // this listens for the entire
        }

        @Override
        public void onClick(View view) {
            MainNavigationController.NavigationEvent navigationEvent = new MainNavigationController.NavigationEvent();
            navigationEvent.restaurant = RestaurantRecycler.this.list.get(getPosition());
            navigationEvent.event = RESTAURANT_INFO;
            EventBus.getDefault().post(navigationEvent);//send event to launch activity for to view menu
        }
    }

    @Override
    public RestaurantRecycler.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rest_info, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        vh.logo = (ImageView) v.findViewById(R.id.rest_logo);
        vh.favorite = (ImageView) v.findViewById(R.id.favorites);
        vh.title = (TextView) v.findViewById(R.id.rest_title);
        vh.tags = (TextView) v.findViewById(R.id.tags);
        vh.address = (TextView) v.findViewById(R.id.address);
        vh.infoHolder = (RelativeLayout) v.findViewById(R.id.info_holder);
        vh.setUpOnClick(); //set up onclick after getting all infor for view
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Restaurant restaurant = list.get(position);
        holder.title.setText(restaurant.getName());
        holder.tags.setText(toCSV(restaurant.getTags()));
        holder.address.setText(restaurant.getAddress().getPrintableAddress());
        Picasso.with(context).load(restaurant.getCoverImgUrl()).into(holder.logo);
        if (hideFavorite) { //hide favorite view
            holder.favorite.setVisibility(View.GONE);
        } else {
            if (FavoriteService.get().isFavourited(restaurant.getId())) { //check if item is in favorites list
                holder.favorite.setSelected(true);
            } else {
                holder.favorite.setSelected(false);
            }
            holder.favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { // set onclick listener for favorites list
                    if (FavoriteService.get().isFavourited(restaurant.getId())) {
                        holder.favorite.setSelected(false);
                        FavoriteService.get().removeFromFavorites(restaurant.getId());
                    } else {
                        FavoriteService.get().addToFavorites(restaurant);
                        holder.favorite.setSelected(true);
                    }
                }
            });
        }
    }

    public void setList(List<Restaurant> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static String toCSV(String[] array) { //found online, takes all tags turns them into nice comma seperated
        String result = "";
        if (array.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : array) {
                sb.append(s).append(",");
            }
            result = sb.deleteCharAt(sb.length() - 1).toString();
        }
        return result;
    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void setHideFavorite(boolean hideFavorite) {//hides favoirtes button
        this.hideFavorite = hideFavorite;
    }
}


