package micfil.nimor;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by michaelfilippo on 08/01/2018.
 */

public class Menu extends AppCompatActivity {

    RelativeLayout menuList;
    boolean isMenuGenerated = false;
    JSONObject fetchedJson;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        menuList = findViewById(R.id.menu_list);

        // Create Menu
        new FetchMenu().execute();

    }

    // Hide Menu
    public void hideMenu(View v){

        Intent intent = new Intent (this, Main.class);
        startActivity(intent);

    }

    private class FetchMenu extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            Log.d("test", "1");

            /*
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
            */
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {

                // Establish connection
                URL url = new URL("https://raw.githubusercontent.com/Mogget24/Nimor/html/config.json");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            //Log.d("result", result);

            super.onPostExecute(result);

            try {

                fetchedJson = new JSONObject(result);
                JSONArray albums = fetchedJson.getJSONArray("albums");


                for(int i = 0; i < albums.length(); i++){

                    Log.d("i", Integer.toString(i));

                    JSONObject album = albums.getJSONObject(i);
                    String albumName = album.getString("name");

                    Button element = new Button(getApplicationContext());
                    element.setText(albumName);
                    element.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    element.setTextColor(Color.WHITE);
                    element.setBackgroundColor(Color.BLACK);

                    Log.d("mah", albumName);

                    menuList.addView(element);

                }


            } catch (Throwable t) {
                // Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
            }

        }
    }

}
