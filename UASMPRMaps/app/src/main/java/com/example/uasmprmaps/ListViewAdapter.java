package com.example.uasmprmaps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView rank;
        TextView country;
        TextView population;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        rank = (TextView) itemView.findViewById(R.id.name);
        country = (TextView) itemView.findViewById(R.id.countrycode);
        population = (TextView) itemView.findViewById(R.id.capital);

        // Capture position and set results to the TextViews
        rank.setText(resultp.get(MainActivity.NAME));
        country.setText(resultp.get(MainActivity.CODE));
        population.setText(resultp.get(MainActivity.CAPITAL));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        // Capture ListView item click
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("name", resultp.get(MainActivity.NAME));
                // Pass all data country
                intent.putExtra("countrycode", resultp.get(MainActivity.CODE));
                // Pass all data population
                intent.putExtra("capital",resultp.get(MainActivity.CAPITAL));
                // Pass all data population
                intent.putExtra("timezone",resultp.get(MainActivity.TIMEZONE));
                // Pass all data population
                intent.putExtra("latlng",resultp.get(MainActivity.LATLNG));
                intent.putExtra("pos1",resultp.get(MainActivity.POS1));
                intent.putExtra("pos2",resultp.get(MainActivity.POS2));
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;
    }

}