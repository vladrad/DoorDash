package app.doordash.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import app.doordash.demo.R;
import app.doordash.demo.views.MenuModel;

/**
 * Created by Vladi on 2/21/17.
 */

public class MenuRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MenuModel> list;


    public class HeaderView extends RecyclerView.ViewHolder {
        public TextView title; //my view holder

        public HeaderView(View v) {
            super(v);
        }
    }

    public class SingleFoodView extends RecyclerView.ViewHolder {
        public TextView itemName; //my view holder
        public TextView itemDescription;

        public SingleFoodView(View v) {
            super(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).item == null) { //check for view type
            return 0; //header
        } else {
            return 1; //food item
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == 0) { // get view type and inflate
            //Do header
            v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_layout, parent, false);

            HeaderView headerView = new HeaderView(v);
            headerView.title = (TextView) v.findViewById(R.id.header);
            return headerView;
        } else {
            //Food type
            v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_layout, parent, false);
            SingleFoodView singleFoodView = new SingleFoodView(v);
            singleFoodView.itemName = (TextView) v.findViewById(R.id.item_title);
            singleFoodView.itemDescription = (TextView) v.findViewById(R.id.item_description);
            return singleFoodView;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            HeaderView headerView = (HeaderView) holder; //just the header view
            headerView.title.setText(list.get(position).title);
        } else {
            SingleFoodView singleFoodView = (SingleFoodView) holder; //just the food view
            singleFoodView.itemName.setText(list.get(position).item.getName());
            singleFoodView.itemDescription.setText(list.get(position).item.getDescription());
        }
    }

    public void setList(List<MenuModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

}


