package com.example.kacent.mytaksdemo2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String url = "http://www.imooc.com/api/teacher?type=4&num=30";
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        MainTask task = new MainTask();
        task.execute(url);
        Log.i("dasda", "test@@@@@@@@@@@@@@@@@@@@2");
    }

    class MainTask extends AsyncTask<String, Void, List<StuData>> {
        @Override
        protected List<StuData> doInBackground(String... params) {
            return getListData(params[0]);
        }

        @Override
        protected void onPostExecute(List<StuData> stuDatas) {
            super.onPostExecute(stuDatas);
            MyAdapter adapter = new MyAdapter(stuDatas, MainActivity.this);
            listView.setAdapter(adapter);
        }
    }

    public String getJsonString(String url) {
        try {
            InputStream is = new URL(url).openStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String JsonString = "";
            String lines = "";
            while ((lines = br.readLine()) != null) {
                JsonString += lines;
            }
            return JsonString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StuData> getListData(String url) {
        String JsonString = getJsonString(url);
        List<StuData> list = new ArrayList<StuData>();
        StuData stuData;
        Log.i("info", JsonString);
        try {
            JSONObject jsonObject = new JSONObject(JsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                stuData = new StuData();
                stuData.imgIcon = jsonObject.getString("picSmall");
                stuData.title = jsonObject.getString("name");
                stuData.context = jsonObject.getString("description");
                list.add(stuData);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
