package io.github.mths0x5f.guiaufu.ru;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.mths0x5f.guiaufu.ru.pojo.Almoco;
import io.github.mths0x5f.guiaufu.ru.pojo.Cardapio;
import io.github.mths0x5f.guiaufu.ru.pojo.Jantar;
import io.github.mths0x5f.guiaufu.util.DataFragment;
import io.github.mths0x5f.guiaufu.R;
import io.github.mths0x5f.guiaufu.SettingsActivity;
import io.github.mths0x5f.guiaufu.api.UFUInfoAPIClient;
import io.github.mths0x5f.guiaufu.ru.pojo.CardapioRU;
import io.github.mths0x5f.guiaufu.util.MultipleFragments;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RUCardapioActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static String LOGCAT_TAG = "CardapioRU";

    private SwipeRefreshLayout swipeRefreshLayout;

    private DataFragment<CardapioRU> cardapioData = new DataFragment<>();
    private DataFragment<List<List<CardapioFragment>>> allCardapioCardsPersistence = new DataFragment<>();


    private List<CardapioFragment> cardapioCardsPerDay;
    private List<List<CardapioFragment>> allCardapioCards = new ArrayList<>();

    private FragmentManager fm = getFragmentManager();

    @Override
    @SuppressWarnings("uncheck")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rucardapio);

        // The mess begin below this line
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        if (savedInstanceState != null) { // Recreated Activity

            // Restore saved data in fragments
            cardapioData = (DataFragment) fm.findFragmentByTag("cardapio_data");
            allCardapioCardsPersistence = (DataFragment) fm.findFragmentByTag("all_cardapio_fragments");

        } else { // Fresh created Activity

            // Create fragments to save data
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(cardapioData, "cardapio_data");
            transaction.add(allCardapioCardsPersistence, "all_cardapio_fragments");
            transaction.commit();

            // Execute the pending transactions *now*, because we need it
            // on a method that may execute before otherwise
            fm.executePendingTransactions();

            // It's time to start an async HTTP request
            UFUInfoAPIClient.get().getCardapioRU("santa-monica", new Callback<CardapioRU>() { // TODO Get shared preferences

                @Override
                public void success(CardapioRU cardapio, Response response) {

                    // The first step is to save the result of request,
                    // so we don't need to do this every time.
                    cardapioData.setData(cardapio);
                    create();

                }

                @Override
                public void failure(RetrofitError error) {

                    Toast.makeText(getApplicationContext(),
                            error.toString(),
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
                swipeRefreshLayout.setRefreshing(false);
                ((TextView) cardapioCardsPerDay.get(0).getView().findViewById(R.id.textViewMealName)).setText("dfghkl");
                Log.i("aaa", "" + cardapioCardsPerDay.size());
            }
        }, 5000);
    }

    @Override
    public void onStart() {
        super.onStart();

        // At this point, the needed views and DataFragments were already created.


        //Log.i(LOGCAT_TAG, cardapioData.getData().getCardapios().size()+"CUGE");


    }

    private void create() {



        for (Cardapio c: cardapioData.getData().getCardapios()) {

            FragmentTransaction ft = fm.beginTransaction();

            cardapioCardsPerDay = MultipleFragments.create(CardapioFragment.class, 2);
            MultipleFragments.addToTransaction(cardapioCardsPerDay, R.id.fragment_container,
                                               "cardapio_"+c.getData(), ft);
            allCardapioCards.add(cardapioCardsPerDay);

            ft.commit();
            fm.executePendingTransactions();

            View almoco_view = cardapioCardsPerDay.get(0).getView();
            View jantar_view = cardapioCardsPerDay.get(1).getView();

            List<Object> l = new ArrayList<>();
            Almoco almoco = c.getRefeicoes().getAlmoco();
            Jantar jantar = c.getRefeicoes().getJantar();
            l.add(almoco);
            l.add(jantar);


            ((TextView)almoco_view.findViewById(R.id.textViewMealName)).setText("Almoço");
            ((TextView)almoco_view.findViewById(R.id.textViewMainCourse)).setText(almoco.getPratoPrincipal());
            ((TextView)almoco_view.findViewById(R.id.textViewVegetarianCourse)).setText(almoco.getPratoVegetariano());
            ((TextView)almoco_view.findViewById(R.id.textViewBeans)).setText(almoco.getFeijao());
            ((TextView)almoco_view.findViewById(R.id.textViewGarnish)).setText(almoco.getGuarnicao());
            ((TextView)almoco_view.findViewById(R.id.textViewSalad)).setText(almoco.getSalada());
            ((TextView)almoco_view.findViewById(R.id.textViewDessert)).setText(almoco.getSobremesa());
            ((TextView)almoco_view.findViewById(R.id.textViewJuice)).setText(almoco.getSuco());




            ((TextView)jantar_view.findViewById(R.id.textViewMealName)).setText("Jantar");
            ((TextView)jantar_view.findViewById(R.id.textViewMainCourse)).setText(jantar.getPratoPrincipal());
            ((TextView)jantar_view.findViewById(R.id.textViewVegetarianCourse)).setText(jantar.getPratoVegetariano());
            ((TextView)jantar_view.findViewById(R.id.textViewBeans)).setText(jantar.getFeijao());
            ((TextView)jantar_view.findViewById(R.id.textViewGarnish)).setText(jantar.getGuarnicao());
            ((TextView)jantar_view.findViewById(R.id.textViewSalad)).setText(jantar.getSalada());
            ((TextView)jantar_view.findViewById(R.id.textViewDessert)).setText(jantar.getSobremesa());
            ((TextView)jantar_view.findViewById(R.id.textViewJuice)).setText(jantar.getSuco());

        }



        allCardapioCardsPersistence.setData(allCardapioCards);

    }


}
