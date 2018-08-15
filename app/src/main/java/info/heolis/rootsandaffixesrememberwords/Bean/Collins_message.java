package info.heolis.rootsandaffixesrememberwords.Bean;

public class Collins_message {
    private Collins_entry collins;
    private Collins_baesInfo baesInfo;
    private String word_name;
    private String result_from;

    public Collins_entry getCollins() {
        return collins;
    }

    public void setCollins(Collins_entry collins) {
        this.collins = collins;
    }

    public Collins_baesInfo getBaesInfo() {
        return baesInfo;
    }

    public void setBaesInfo(Collins_baesInfo baesInfo) {
        this.baesInfo = baesInfo;
    }

    public String getWord_name() {
        return word_name;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public String getResult_from() {
        return result_from;
    }

    public void setResult_from(String result_from) {
        this.result_from = result_from;
    }
}
