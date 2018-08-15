package info.heolis.rootsandaffixesrememberwords;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.heolis.rootsandaffixesrememberwords.Bean.Total_list;
import info.heolis.rootsandaffixesrememberwords.Bean.word;
import info.heolis.rootsandaffixesrememberwords.utils.JsonTools;

public class Main2Activity extends AppCompatActivity {
    private Total_list mTotal_list = new Total_list();
    private List<word> mWords;
    private ViewPager mViewPager;
    private FragmentAdapter adapter;
    private List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mViewPager = findViewById(R.id.viewpager);
        getMesg();

        LeftFragment leftFragment = new LeftFragment();
        leftFragment.setTotalList(mTotal_list);
        mFragments.add(leftFragment);

        MiddleFragment middleFragment = new MiddleFragment();
        middleFragment.setWords(mTotal_list);
        mFragments.add(middleFragment);
        mFragments.add(new RightFragment());
        adapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(1);
    }

    private void getMesg() {
        Intent intent = getIntent();
        String temp = intent.getStringExtra("fileName");
        int file_index = Integer.parseInt(temp);
        try {
            mTotal_list = JsonTools.jsonData(this, "root_word" + file_index + ".txt");
            mWords = mTotal_list.getWord();
            Log.e("root", mTotal_list.getWord().size() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
