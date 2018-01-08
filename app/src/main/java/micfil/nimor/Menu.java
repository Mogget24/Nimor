package micfil.nimor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by michaelfilippo on 08/01/2018.
 */

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

    }

    // Hide Menu
    public void hideMenu(View v){

        Intent intent = new Intent (this, Main.class);
        startActivity(intent);

    }

}
