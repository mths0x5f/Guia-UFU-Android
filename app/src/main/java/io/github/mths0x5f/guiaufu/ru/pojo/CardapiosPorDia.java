package io.github.mths0x5f.guiaufu.ru.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;


public class CardapiosPorDia {

    @Expose
    private List<Refeicao> refeicoes = new ArrayList<Refeicao>();
    @Expose
    private String data;


    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
