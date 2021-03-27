package com.krishna.retrofit_insertdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText name,username,password;
    String url = "http://10.0.2.2/android_php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.eName);
        username = findViewById(R.id.eUsername);
        password = findViewById(R.id.ePassword);

        submit = findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

    }

    public void insertData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        myapi api = retrofit.create(myapi.class);

        Call<Model> call = api.addData(name.getText().toString(), username.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                name.setText("");
                username.setText("");
                password.setText("");
                Toast.makeText(getApplicationContext(),"Data inserted Successfully...",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
                Log.d("TAG",t.toString());
            }
        });



    }
}