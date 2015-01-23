package io.github.mths0x5f.guiaufu.ru;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.CardView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import io.github.mths0x5f.guiaufu.R;
import io.github.mths0x5f.guiaufu.SettingsActivity;
import io.github.mths0x5f.guiaufu.api.UFUInfoAPIClient;
import io.github.mths0x5f.guiaufu.ru.pojo.CardapioRU;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RUCardapioActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {

    private TextView textView;
    private CardapioRU cardapioRU;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rucardapio);

        // The mess begin below this line
        textView = (TextView) findViewById(R.id.info_text);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        // set a custom tint color for all system bars
        //tintManager.setTintColor(Color.parseColor("#99000FF"));
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);

        UFUInfoAPIClient.get().getCardapioRU("santa-monica", new Callback<CardapioRU>() {

            @Override
            public void success(CardapioRU cardapio, Response response) {
                cardapioRU = cardapio;
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(RUCardapioActivity.this);
                textView.setText(cardapio.getCardapios().get(0).getRefeicoes().getAlmoco().getPratoPrincipal());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(),
                        "deu ruim",
                        Toast.LENGTH_SHORT).show();
            }
        });




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
            Toast.makeText( getApplicationContext(),
                    item.getTitle(),
                    Toast.LENGTH_SHORT ).show();
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
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

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                swipeLayout.setRefreshing(false);
            }
        }, 5000);
    }

}
