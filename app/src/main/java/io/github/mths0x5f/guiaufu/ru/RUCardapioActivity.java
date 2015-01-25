package io.github.mths0x5f.guiaufu.ru;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    // Create a new Fragment to be placed in the activity layout
    private List<CardapioFragment> cardapioFragmentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rucardapio);

        // The mess begin below this line


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

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (savedInstanceState != null) {

                for(int i = 0; i< 10; i++)
                    cardapioFragmentArrayList.add((CardapioFragment)getFragmentManager().
                            findFragmentByTag("fragment_cardapio_"+i));
                Log.i("aaa", "" + cardapioFragmentArrayList.size());
                return;
            }





            for(int i = 0; i < 10; i++) {
                cardapioFragmentArrayList.add(new CardapioFragment());
                transaction.add(R.id.fragment_container,
                        cardapioFragmentArrayList.get(i),
                        "fragment_cardapio_"+i);
            }



            transaction.commit();
            Log.i("aaa","OnCreate");

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
                ((TextView) cardapioFragmentArrayList.get(0).getView().findViewById(R.id.textViewMealName)).setText("dfghkl");
                Log.i("aaa", "" + cardapioFragmentArrayList.size());
            }
        }, 5000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("aaa", "onStart");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state0


        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

    }

}
