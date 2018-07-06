package com.example.jerry.conntest3;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonArray;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiService apiService;
    TextView tv;
    EditText ed1,ed2,ed3,ed4,ed5;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);

        tv = (TextView) findViewById(R.id.michin);
        ed1 = (EditText) findViewById(R.id.mysqlId);
        ed2 = (EditText) findViewById(R.id.mysqlPw);
        ed3 = (EditText) findViewById(R.id.mysqlEmail);
        ed4 = (EditText) findViewById(R.id.mysqlName);
        ed5 = (EditText) findViewById(R.id.mysqlIntroduce);
        btn1 = (Button) findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ed1.getText().toString();
                String pw = ed2.getText().toString();
                String email = ed3.getText().toString();
                String name = ed4.getText().toString();
                String introduce = ed5.getText().toString();
                Log.d("id",id);

                Call<ResponseBody> post = apiService.sendPost(id,pw,email,name,introduce);
                post.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody rb = response.body();
                        Log.d("Test",rb+"success");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("Test","post fail");
                    }
                });
            }
        });


        //Get
        Call<ResponseBody> comment = apiService.getComment();
        comment.enqueue(new Callback<ResponseBody>() {
            //데이터가 받아지면 호출됨
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                     //Log.v("Test",response.body().string());
                    //String MyResult = response.body().string();
                    //System.out.println("postJSONRequest response.body : "+MyResult);
                    //tv.setText(response.body().toString());
                    tv.setText(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //데이터 받기 실패하면 호출
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Test","fail");
            }
        });

        //post
       /* Call<ResponseBody> post = apiService.addId(ed1.getText().toString());
        post.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    Log.d("Test","post fail");
                }catch(Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Test","post fail");
            }
        });*/

    }
}