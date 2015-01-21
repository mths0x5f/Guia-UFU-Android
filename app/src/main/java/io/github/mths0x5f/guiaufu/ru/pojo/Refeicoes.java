
package io.github.mths0x5f.guiaufu.ru.pojo;

// import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

// @Generated("org.jsonschema2pojo")
public class Refeicoes {

    @Expose
    private Almoco almoco;
    @Expose
    private Jantar jantar;

    /**
     * 
     * @return
     *     The almoco
     */
    public Almoco getAlmoco() {
        return almoco;
    }

    /**
     * 
     * @param almoco
     *     The almoco
     */
    public void setAlmoco(Almoco almoco) {
        this.almoco = almoco;
    }

    /**
     * 
     * @return
     *     The jantar
     */
    public Jantar getJantar() {
        return jantar;
    }

    /**
     * 
     * @param jantar
     *     The jantar
     */
    public void setJantar(Jantar jantar) {
        this.jantar = jantar;
    }

}
