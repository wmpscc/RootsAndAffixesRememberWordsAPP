package info.heolis.rootsandaffixesrememberwords;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.heolis.rootsandaffixesrememberwords.Bean.Total_list;


public class LeftFragment extends Fragment {
    private Total_list mTotalList;
    private View root;
    private TextView tvIntroduce;
    private TextView tvTitle;

    public LeftFragment() {
        // Required empty public constructor
    }

    public void setTotalList(Total_list totalList) {
        this.mTotalList = totalList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_left, container, false);
        initView();
        return root;
    }

    public void initView() {
        tvTitle = root.findViewById(R.id.tv_root_title);
        tvIntroduce = root.findViewById(R.id.tv_root_introduce);
        String title = mTotalList.getRoot_introduce().split("\n")[0];
        String content = mTotalList.getRoot_introduce().split("\n")[1];
        tvIntroduce.setText(content);
        tvTitle.setText(title.split("\\. ")[1]);
        Log.e("title", title);
    }


}
