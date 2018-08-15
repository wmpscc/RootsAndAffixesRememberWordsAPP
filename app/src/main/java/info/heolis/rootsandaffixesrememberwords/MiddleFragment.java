package info.heolis.rootsandaffixesrememberwords;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.heolis.rootsandaffixesrememberwords.Bean.Total_list;
import info.heolis.rootsandaffixesrememberwords.Bean.word;
import info.heolis.rootsandaffixesrememberwords.utils.LMediaPlayerManger;


public class MiddleFragment extends Fragment implements View.OnClickListener {
    private View root;
    private Total_list mTotal_list;
    public static int current_num = 0;
    private List<word> mWords;
    private TextView tvName, tvMean, tvCount;
    private Button btRemember, btNotRemember;
    private int flage = 1;
    private LMediaPlayerManger mPlayerManger;
    public MiddleFragment() {
        // Required empty public constructor
    }

    public void setWords(Total_list total_list) {
        this.mWords = total_list.getWord();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_middle, container, false);
        initView();
        refresh(current_num);
        return root;
    }

    private void initView() {
        tvName = root.findViewById(R.id.tv_name);
        tvMean = root.findViewById(R.id.tv_mean);
        tvCount = root.findViewById(R.id.tvCount);
        btRemember = root.findViewById(R.id.bt_remember);
        btNotRemember = root.findViewById(R.id.bt_not_remember);
        mPlayerManger = LMediaPlayerManger.getInstance();


        btRemember.setOnClickListener(this);
        btNotRemember.setOnClickListener(this);
        tvMean.setOnClickListener(this);
        tvName.setOnClickListener(this);
        Log.e("real", mWords.size() + "");
    }

    private void refresh(int index) {
        tvName.setText(mWords.get(index).getName());
        tvMean.setText(mWords.get(index).getIntroduce());
        tvCount.setText(String.format("%d / %d", index + 1, mWords.size()));
        playMusic(mWords.get(index).getName());
    }
    private void playMusic(String name){
        String filepath = "http://audio.beingfine.cn/speeches/UK/UK-speech/" + name.replace(" ", "") + ".mp3";
        mPlayerManger.playMusic(0, filepath, new LMediaPlayerManger.PlayMusicCompleteListener() {
            @Override
            public void playMusicComplete(int position) {
                Log.e("play_end", "playMusicComplete:" + position);
            }
        });
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_remember:
                if (current_num + 1 >= mWords.size()) {
                    Toast.makeText(root.getContext(), "已经是最后一条了", Toast.LENGTH_SHORT).show();
                    break;
                }
                current_num++;
                refresh(current_num);
                break;
            case R.id.bt_not_remember:
                if (current_num - 1 < 0) {
                    Toast.makeText(root.getContext(), "已经是第一条了", Toast.LENGTH_SHORT).show();
                    break;
                }
                current_num--;
                refresh(current_num);
                break;
            case R.id.tv_mean:
                if (flage == 0) {
                    tvMean.setText(mWords.get(current_num).getIntroduce());
                    flage++;
                } else {
                    tvMean.setText(mWords.get(current_num).getMean());
                    flage--;
                }
                break;
            case R.id.tv_name:
                playMusic(mWords.get(current_num).getName());
                break;
        }
    }

}
