package io.github.mths0x5f.guiaufu.ru;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import io.github.mths0x5f.guiaufu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardapioFragment extends Fragment {


    public CardapioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("aaa","onCreateView");
        return inflater.inflate(R.layout.fragment_cardapio, container, false);
    }


}
