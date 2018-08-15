package info.heolis.rootsandaffixesrememberwords;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.heolis.rootsandaffixesrememberwords.Bean.RootList;
import info.heolis.rootsandaffixesrememberwords.Bean.RootName;
import info.heolis.rootsandaffixesrememberwords.Bean.Total_list;
import info.heolis.rootsandaffixesrememberwords.Bean.word;
import info.heolis.rootsandaffixesrememberwords.utils.JsonTools;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity {
    private RootList mRootList;
    private List<RootName> mRootNames;
    private RecyclerView mRecyclerView;
    private RootListAdapter adapter;
    private final int REQUEST_WRITE_EXTERNAL_STORAGE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        try {
            mRootList = JsonTools.jsonRootList(this, "root_list.txt");
            mRootNames = mRootList.getRootList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initRecycleItem();
    }

    private void initRecycleItem() {
        mRecyclerView = findViewById(R.id.rv_root_list);
        adapter = new RootListAdapter(mRootNames);
        mRecyclerView.setAdapter(adapter);

    }
    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
