package io.github.mths0x5f.guiaufu.ru.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Cardapio {

    @SerializedName("cardapios-por-dia")
    @Expose
    private List<CardapiosPorDia> cardapiosPorDia = new ArrayList<CardapiosPorDia>();


    public List<CardapiosPorDia> getCardapiosPorDia() {
        return cardapiosPorDia;
    }

    public void setCardapiosPorDia(List<CardapiosPorDia> cardapiosPorDia) {
        this.cardapiosPorDia = cardapiosPorDia;
    }

}
