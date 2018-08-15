package info.heolis.rootsandaffixesrememberwords.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import info.heolis.rootsandaffixesrememberwords.Bean.RootList;
import info.heolis.rootsandaffixesrememberwords.Bean.RootName;
import info.heolis.rootsandaffixesrememberwords.Bean.Total_list;
import info.heolis.rootsandaffixesrememberwords.Bean.word;

public class JsonTools {
    public static Total_list jsonData(Context context, String fileName) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(fileName), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        inputStreamReader.close();
        Total_list total_list = JSON.parseObject(stringBuilder.toString(), Total_list.class);
        return total_list;

    }

    public static RootList jsonRootList(Context context, String fileName) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(fileName), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        inputStreamReader.close();
        RootList rootList = JSON.parseObject(stringBuilder.toString(), RootList.class);
        return rootList;
    }
}
