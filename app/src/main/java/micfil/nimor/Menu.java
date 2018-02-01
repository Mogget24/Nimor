package micfil.nimor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by michaelfilippo on 08/01/2018.
 */

public class Menu extends AppCompatActivity {

    RelativeLayout menuList, menuContainer, album1, album2;
    boolean isMenuGenerated = false;
    JSONObject fetchedJson;
    Typeface MetalMacabre;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // Setup font
        MetalMacabre = Typeface.createFromAsset(getAssets(),  "fonts/MetalMacabre.ttf");

        menuList = findViewById(R.id.menu_list);
        menuContainer = findViewById(R.id.controls_wrapper);
        album1 = findViewById(R.id.album1);

        /*
        album1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandSection(view);
            }
        });
        */

        // Set the font for all views
        for (int i = 0; i < menuContainer.getChildCount(); i++){
            if(menuContainer.getChildAt(i) instanceof TextView){
                ( (TextView) menuContainer.getChildAt(i)).setTypeface(MetalMacabre);;
            }
        }

        // Make each "album" clickable
        for (int i = 0; i < menuList.getChildCount(); i++){
            if(getStringId(menuList.getChildAt(i)).contains("album")){

                final int currentIndex = i;

                menuList.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        expandSection(menuList.getChildAt(currentIndex));
                    }
                });
            }

        }

        // Create Menu
        AsyncTask task = new FetchMenu().execute();

    }

    // Get the string ID of a view
    public static String getStringId(View view) {
        if (view.getId() == -1) return "no-id";
        else return view.getResources().getResourceName(view.getId());
    }

    // Hide Menu and re-enable main
    public void hideMenu(View v){

        Intent intent = new Intent (this, Main.class);
        startActivity(intent);

    }

    // Expand section in the menu
    public void expandSection(View v){ //<- try this

        int current = menuList.indexOfChild(v);

        // Clicked view is a RelativeLayout for sure
        RelativeLayout currentView = (RelativeLayout) v;

        ImageView currentImage = null;

        // Find the children to change
        for (int i = 0; i < currentView.getChildCount(); i++){
            if(currentView.getChildAt(i) instanceof ImageView){
                currentImage = (ImageView) currentView.getChildAt(i);
            }
        }

        View next = menuList.getChildAt(current + 1);

        // TEMPORARY PICCONE
        album2 = findViewById(R.id.album2);

        // Expand
        if(next.getVisibility() == View.GONE){

            // Change icon
            currentImage.setImageResource(R.drawable.collapse);

            // Show children
            next.setVisibility(View.VISIBLE);

            /*album2 // CHANGE PICCONE -> LAYOUT BELOW VALUE CHANGES */

        } else {

            // Change icon
            currentImage.setImageResource(R.drawable.expand);

            // Hide children
            next.setVisibility(View.GONE);

        }


    }

    private class FetchMenu extends AsyncTask<String, String, String> {

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

            super.onPostExecute(result);

            try {

                fetchedJson = new JSONObject(result);
                JSONArray albums = fetchedJson.getJSONArray("albums");

                for(int i = 0; i < albums.length(); i++){

                    JSONObject album = albums.getJSONObject(i);
                    String albumName = album.getString("name");

                    Button element = new Button(getApplicationContext());
                    element.setText(albumName);
                    element.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    element.setTextColor(Color.WHITE);
                    element.setBackgroundColor(Color.BLACK);
                    element.setTypeface(MetalMacabre);

                    // Has to become expander/collapser + loop on songs
                    element.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        Intent intent = new Intent (getBaseContext(), Main.class);
                        intent.putExtra("switchSong", "myMethod");
                        startActivity(intent);

                        }
                    });

                    //menuList.addView(element); <- TEMPORARY: ENABLE THIS TO MAKE IT WORK

                }


            } catch (Throwable t) {
                // Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
            }

        }
    }

}
