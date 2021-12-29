package com.example.uasmprmaps;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    static String NAME = "nama";
    static String CODE = "kode";
    static String CAPITAL = "ibukota";
    static String TIMEZONE = "timezone";
    static String LATLNG = "posmap";
    static String POS1 = "pos1";
    static String POS2 = "pos2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arraylist = new ArrayList<>();
        listview = findViewById(R.id.listview);
        // Execute DownloadJSON AsyncTask
        new DownloadJSON().execute();
    }

    public class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            progressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            progressDialog.setTitle("Android JSON Parse Tutorial");
            // Set progressdialog message
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            // Show progressdialog
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler httpHandler = new HttpHandler();

            // JSON data url
            String jsonurl = "https://gist.githubusercontent.com/erdem/8c7d26765831d0f9a8c62f02782ae00d/raw/248037cd701af0a4957cce340dabb0fd04e38f4c/countries.json";
            String jsonString = httpHandler.makeServiceCall(jsonurl);
            Log.e(TAG, "Response from url: " + jsonString);
            if (jsonString != null) {
                try {
                    JSONArray country = new JSONArray(jsonString);
                    // Getting JSON Array node

                    for (int i = 0; i < country.length(); i++) {
                        JSONObject c = country.getJSONObject(i);
                        String nama = c.getString("name");
                        String kode = c.getString("country_code");
                        String ibukota = c.getString("capital");
                        String waktu = c.getString("timezones");
                        JSONArray posisi = c.getJSONArray("latlng");

                        HashMap<String, String> colorx = new HashMap<>();

                        colorx.put("nama", nama);
                        colorx.put("kode", kode);
                        colorx.put("ibukota", ibukota);
                        colorx.put("timezone", waktu);
                        for (int j = 0; j < posisi.length(); j++) {
                            String posisi1 = timezone.getString(0);
                            String posisi2 = timezone.getString(1);


                            colorx.put("pos1", posisi1);
                            colorx.put("pos2", posisi2);


                        }
                        arraylist.add(colorx);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Could not get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Could not get json from server.",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the listview in listview_main.xml
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(MainActivity.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            progressDialog.dismiss();
        }
    }
}
