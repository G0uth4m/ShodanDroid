package com.g0uth4m.shodanapp;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListItemAdapter extends ArrayAdapter<ListItem> {

    public ListItemAdapter(Activity context, ArrayList<ListItem> listItems){
        super(context, 0, listItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ListItem currentItem = getItem(position);

        TextView ip = (TextView) listItemView.findViewById(R.id.ip_view);
        ip.setText(currentItem.getIP());

        TextView port = (TextView) listItemView.findViewById(R.id.port_view);
        port.setText("" + currentItem.getPort());

        TextView city = (TextView) listItemView.findViewById(R.id.city_view);
        city.setText(currentItem.getCity());

        TextView country = (TextView) listItemView.findViewById(R.id.country_view);
        country.setText(currentItem.getCountry());

        TextView isp = (TextView) listItemView.findViewById(R.id.isp_view);
        isp.setText(currentItem.getISP());

        TextView org = (TextView) listItemView.findViewById(R.id.org_view);
        org.setText(currentItem.getOrg());

        TextView data = (TextView) listItemView.findViewById(R.id.data_view);
        data.setText(currentItem.getData());

        return listItemView;
    }
}
