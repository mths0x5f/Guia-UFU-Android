
package io.github.mths0x5f.guiaufu.ru.pojo;

import java.util.ArrayList;
import java.util.List;
// import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

// @Generated("org.jsonschema2pojo")
public class CardapioRU {

    @Expose
    private String campus;
    @Expose
    private List<Cardapio> cardapios = new ArrayList<Cardapio>();

    /**
     * 
     * @return
     *     The campus
     */
    public String getCampus() {
        return campus;
    }

    /**
     * 
     * @param campus
     *     The campus
     */
    public void setCampus(String campus) {
        this.campus = campus;
    }

    /**
     * 
     * @return
     *     The cardapios
     */
    public List<Cardapio> getCardapios() {
        return cardapios;
    }

    /**
     * 
     * @param cardapios
     *     The cardapios
     */
    public void setCardapios(List<Cardapio> cardapios) {
        this.cardapios = cardapios;
    }

}
