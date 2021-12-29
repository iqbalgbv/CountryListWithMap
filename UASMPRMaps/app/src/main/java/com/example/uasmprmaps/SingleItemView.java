package com.example.uasmprmaps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SingleItemView extends Activity {

    // Declare Variables
    String nama;
    String kode;
    String ibukota;
    String waktu;
    String latlng;
    String pos1;
    String pos2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);

        Intent i = getIntent();
        // Get the result of rank
        nama = i.getStringExtra("name");
        // Get the result of country
        kode = i.getStringExtra("countrycode");
        // Get the result of population
        ibukota = i.getStringExtra("capital");
        // Get the result of timezone
        waktu = i.getStringExtra("timezone");
        // Get the result of population
        latlng = i.getStringExtra("latlng");
        pos1 = i.getStringExtra("pos1");
        pos2 = i.getStringExtra("pos2");

        // Locate the TextViews in singleitemview.xml
        TextView txtrank = (TextView) findViewById(R.id.name);
        TextView txtcountry = (TextView) findViewById(R.id.countrycode);
        TextView txtpopulation = (TextView) findViewById(R.id.capital);
        TextView txttimezone = (TextView) findViewById(R.id.timezone);
        TextView txtlatlng = (TextView) findViewById(R.id.latlng);

        // Set results to the TextViews
        txtrank.setText(nama);
        txtcountry.setText(kode);
        txtpopulation.setText(ibukota);
        txttimezone.setText(waktu);
        txtlatlng.setText(pos1+" "+pos2);

    }

    public void readData(View v){
        Intent temp = getIntent();
        pos1 = temp.getStringExtra("pos1");
        pos2 = temp.getStringExtra("pos2");
        nama = temp.getStringExtra("name");
        Uri intentUri = Uri.parse("http://maps.google.com/maps?q="+ pos1  +"," + pos2 +"("+ nama + ")&iwloc=A&hl=es");
        Intent intent = new Intent(Intent.ACTION_VIEW,intentUri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

}
