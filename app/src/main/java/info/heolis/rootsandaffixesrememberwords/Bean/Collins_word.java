package info.heolis.rootsandaffixesrememberwords.Bean;

import java.util.List;

public class Collins_word {
    private String posp;
    private String tran;
    private String def;
    private String gramarinfo;
    private List<Collins_word_example> example;

    public String getTran() {
        return tran;
    }

    public void setTran(String tran) {
        this.tran = tran;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getGramarinfo() {
        return gramarinfo;
    }

    public void setGramarinfo(String gramarinfo) {
        this.gramarinfo = gramarinfo;
    }

    public List<Collins_word_example> getExample() {
        return example;
    }

    public void setExample(List<Collins_word_example> example) {
        this.example = example;
    }

    public String getPosp() {

        return posp;
    }

    public void setPosp(String posp) {
        this.posp = posp;
    }
}
