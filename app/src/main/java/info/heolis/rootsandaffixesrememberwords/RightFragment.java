package info.heolis.rootsandaffixesrememberwords;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.heolis.rootsandaffixesrememberwords.Bean.Collins_message;
import info.heolis.rootsandaffixesrememberwords.Bean.Collins_total;
import info.heolis.rootsandaffixesrememberwords.Bean.Collins_word;
import info.heolis.rootsandaffixesrememberwords.utils.CollinsAdapter;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public class RightFragment extends Fragment {
    private View root;
    private RecyclerView mRecyclerView;
    private MultiTypeAdapter adapter;
    private Items mItems;

    public RightFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_right, container, false);
        getMore("animal");
        return root;
    }

    private void initRecyclerView(Collins_message collins_message) {
        mRecyclerView = root.findViewById(R.id.rv_collins);
        adapter = new MultiTypeAdapter();
        adapter.register(Collins_T_item.class, new Collins_T_itemViewBinder());
        mRecyclerView.setAdapter(adapter);
        mItems = new Items();
        List<Collins_word> collinsWords = collins_message.getCollins().getEntry();
        for (int i = 0; i < collinsWords.size(); i++) {


        }

    }

    private void getMore(String name) {
        OkGo.<String>post("http://dict-mobile.iciba.com/interface/index.php?c=word&list=300&client=1&timestamp=1532777619&sign=9bec278772cf7a4d")
                .tag(root.getContext())
                .isMultipart(true)
                .params("word", name)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Collins_total collinsTotal = JSON.parseObject(response.body(), Collins_total.class);
                        Collins_message collinsMessage = collinsTotal.getMessage();
                        initRecyclerView(collinsMessage);
                    }
                });
    }


}
