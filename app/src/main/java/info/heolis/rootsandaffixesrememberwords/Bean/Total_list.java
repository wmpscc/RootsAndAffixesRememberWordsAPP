package info.heolis.rootsandaffixesrememberwords.Bean;

import java.util.ArrayList;
import java.util.List;

public class Total_list {
    private String root_introduce;
    private List<word> word = new ArrayList<>();

    public String getRoot_introduce() {
        return root_introduce;
    }

    public void setRoot_introduce(String root_introduce) {
        this.root_introduce = root_introduce;
    }

    public List<info.heolis.rootsandaffixesrememberwords.Bean.word> getWord() {
        return word;
    }

    public void setWord(List<info.heolis.rootsandaffixesrememberwords.Bean.word> word) {
        this.word = word;
    }
}
