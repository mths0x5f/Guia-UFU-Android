
package io.github.mths0x5f.guiaufu.ru.pojo;

// import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

// @Generated("org.jsonschema2pojo")
public class Cardapio {

    @Expose
    private Refeicoes refeicoes;
    @Expose
    private String data;

    /**
     * 
     * @return
     *     The refeicoes
     */
    public Refeicoes getRefeicoes() {
        return refeicoes;
    }

    /**
     * 
     * @param refeicoes
     *     The refeicoes
     */
    public void setRefeicoes(Refeicoes refeicoes) {
        this.refeicoes = refeicoes;
    }

    /**
     * 
     * @return
     *     The data
     */
    public String getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(String data) {
        this.data = data;
    }

}
