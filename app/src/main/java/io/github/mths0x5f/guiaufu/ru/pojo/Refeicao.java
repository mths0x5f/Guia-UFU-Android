package io.github.mths0x5f.guiaufu.ru.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Refeicao {

    @Expose
    private String sobremesa;
    @SerializedName("prato-vegetariano")
    @Expose
    private String pratoVegetariano;
    @Expose
    private String arroz;
    @Expose
    private String salada;
    @SerializedName("arroz-vegetariano")
    @Expose
    private String arrozVegetariano;
    @Expose
    private String guarnicao;
    @SerializedName("prato-principal")
    @Expose
    private String pratoPrincipal;
    @Expose
    private String suco;
    @Expose
    private String feijao;


    public String getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa;
    }

    public String getPratoVegetariano() {
        return pratoVegetariano;
    }

    public void setPratoVegetariano(String pratoVegetariano) {
        this.pratoVegetariano = pratoVegetariano;
    }

    public String getArroz() {
        return arroz;
    }

    public void setArroz(String arroz) {
        this.arroz = arroz;
    }

    public String getSalada() {
        return salada;
    }

    public void setSalada(String salada) {
        this.salada = salada;
    }

    public String getArrozVegetariano() {
        return arrozVegetariano;
    }

    public void setArrozVegetariano(String arrozVegetariano) {
        this.arrozVegetariano = arrozVegetariano;
    }

    public String getGuarnicao() {
        return guarnicao;
    }

    public void setGuarnicao(String guarnicao) {
        this.guarnicao = guarnicao;
    }

    public String getPratoPrincipal() {
        return pratoPrincipal;
    }

    public void setPratoPrincipal(String pratoPrincipal) {
        this.pratoPrincipal = pratoPrincipal;
    }

    public String getSuco() {
        return suco;
    }

    public void setSuco(String suco) {
        this.suco = suco;
    }

    public String getFeijao() {
        return feijao;
    }

    public void setFeijao(String feijao) {
        this.feijao = feijao;
    }

}
