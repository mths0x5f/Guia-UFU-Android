
package io.github.mths0x5f.guiaufu.ru.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Jantar {

    @Expose
    private String sobremesa;
    @Expose
    private String guarnicao;
    @Expose
    private String suco;
    @SerializedName("prato-vegetariano")
    @Expose
    private String pratoVegetariano;
    @Expose
    private String feijao;
    @SerializedName("arroz-vegetariano")
    @Expose
    private String arrozVegetariano;
    @SerializedName("prato-principal")
    @Expose
    private String pratoPrincipal;
    @Expose
    private String arroz;
    @Expose
    private String salada;

    /**
     * 
     * @return
     *     The sobremesa
     */
    public String getSobremesa() {
        return sobremesa;
    }

    /**
     * 
     * @param sobremesa
     *     The sobremesa
     */
    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa;
    }

    /**
     * 
     * @return
     *     The guarnicao
     */
    public String getGuarnicao() {
        return guarnicao;
    }

    /**
     * 
     * @param guarnicao
     *     The guarnicao
     */
    public void setGuarnicao(String guarnicao) {
        this.guarnicao = guarnicao;
    }

    /**
     * 
     * @return
     *     The suco
     */
    public String getSuco() {
        return suco;
    }

    /**
     * 
     * @param suco
     *     The suco
     */
    public void setSuco(String suco) {
        this.suco = suco;
    }

    /**
     * 
     * @return
     *     The pratoVegetariano
     */
    public String getPratoVegetariano() {
        return pratoVegetariano;
    }

    /**
     * 
     * @param pratoVegetariano
     *     The prato-vegetariano
     */
    public void setPratoVegetariano(String pratoVegetariano) {
        this.pratoVegetariano = pratoVegetariano;
    }

    /**
     * 
     * @return
     *     The feijao
     */
    public String getFeijao() {
        return feijao;
    }

    /**
     * 
     * @param feijao
     *     The feijao
     */
    public void setFeijao(String feijao) {
        this.feijao = feijao;
    }

    /**
     * 
     * @return
     *     The arrozVegetariano
     */
    public String getArrozVegetariano() {
        return arrozVegetariano;
    }

    /**
     * 
     * @param arrozVegetariano
     *     The arroz-vegetariano
     */
    public void setArrozVegetariano(String arrozVegetariano) {
        this.arrozVegetariano = arrozVegetariano;
    }

    /**
     * 
     * @return
     *     The pratoPrincipal
     */
    public String getPratoPrincipal() {
        return pratoPrincipal;
    }

    /**
     * 
     * @param pratoPrincipal
     *     The prato-principal
     */
    public void setPratoPrincipal(String pratoPrincipal) {
        this.pratoPrincipal = pratoPrincipal;
    }

    /**
     * 
     * @return
     *     The arroz
     */
    public String getArroz() {
        return arroz;
    }

    /**
     * 
     * @param arroz
     *     The arroz
     */
    public void setArroz(String arroz) {
        this.arroz = arroz;
    }

    /**
     * 
     * @return
     *     The salada
     */
    public String getSalada() {
        return salada;
    }

    /**
     * 
     * @param salada
     *     The salada
     */
    public void setSalada(String salada) {
        this.salada = salada;
    }

}
