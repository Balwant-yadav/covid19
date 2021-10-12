package com.example.covid19;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView date,ac,d,cn,rec;
    RecyclerView rv;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);
        update();
        date=findViewById(R.id.date);
        ac=findViewById(R.id.active);
        d=findViewById(R.id.death);
        cn=findViewById(R.id.cnfrm);
        rec=findViewById(R.id.recoverd);


    }
    public void update(){
        try {
            makecall("https://api.covid19india.org/data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void makecall(String url) throws IOException {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String res=response.body().string();
                String data = null,total = null,cnf = null,deats = null,re=null;
                Gson gson=new Gson();
                try {
                    JSONObject jsonObject=new JSONObject(res);
                    JSONArray jsonArray=jsonObject.getJSONArray("statewise");
                    JSONObject object=jsonArray.getJSONObject(0);
                     data=object.getString("lastupdatedtime");
                      total=object.getString("active");
                     cnf=object.getString("confirmed");
                    deats=object.getString("deaths");
                    re=object.getString("recovered");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                api appi=gson.fromJson(res,api.class);

                final  adapter gadapter=new adapter(appi.getStatewise(),getBaseContext());
                String finalData = data;

                String finalTotal = total;
                String finalCnf = cnf;
                String finalDeats = deats;
                String finalRe = re;
                String finalData1 = data;
                MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    rv.setAdapter(gadapter);
                    rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));


                    date.setText(finalData);
                    ac.setText(finalTotal);
                    cn.setText(finalCnf);
                    d.setText(finalDeats);
                    rec.setText(finalRe);




                }
            });
            }
        });
    }
}