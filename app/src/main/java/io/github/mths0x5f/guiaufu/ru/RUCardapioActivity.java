package io.github.mths0x5f.guiaufu.ru;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import io.github.mths0x5f.guiaufu.R;
import io.github.mths0x5f.guiaufu.api.UFUInfoAPIClient;
import io.github.mths0x5f.guiaufu.ru.pojo.CardapioRU;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RUCardapioActivity extends ActionBarActivity {

    private TextView textView;
    private CardapioRU cardapioRU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rucardapio);

        // The mess begin below this line
        textView = (TextView) findViewById(R.id.textviewwwww);

        if(isNetworkConnected()) {

            UFUInfoAPIClient.get().getCardapioRU("santa-monica", new Callback<CardapioRU>() {

                @Override
                public void success(CardapioRU cardaepioRU, Response response) {
                    cardapioRU = cardaepioRU;
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(),
                            "deu ruim",
                            Toast.LENGTH_SHORT).show();
                }
            });

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rucardapio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* And here we go... Custom methods go below */


    /* Reinventing the wheel, part I
     * Something to check if device is connected to a network
     */
    public boolean isNetworkConnected() {

        ConnectivityManager connMgr = (ConnectivityManager)
                            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()) {

            return true;
        }
        else {
            // There's no connection, so tell the user
            Toast.makeText( getApplicationContext(),
                            R.string.toast_no_network,
                            Toast.LENGTH_SHORT ).show();
            return false;
        }

    }



}
