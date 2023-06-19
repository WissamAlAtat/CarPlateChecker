package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
     private Button AddBtn,Check;
     private EditText Plate;
     ListView ls;
     String CheckURL="https://mobilecsci410wissamalatat.000webhostapp.com/Project/getCarInfo.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddBtn=findViewById(R.id.btnAddMain);
        Check=findViewById(R.id.CheckBtn);
        Plate=findViewById(R.id.txtplatecheck);
        ls=(ListView) findViewById(R.id.lsCarPlates);
        ArrayList<CarInfo> CarsInfo=new ArrayList<>();
        ArrayAdapter<CarInfo>CI=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,CarsInfo);
        RequestQueue queue= Volley.newRequestQueue(this);

        JsonArrayRequest request=new JsonArrayRequest(CheckURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0;i<response.length();i++){
                        JSONObject row=response.getJSONObject(i);
                        int carplate=row.getInt("plateNum");
                        String name=row.getString("Owner");
                        String brand=row.getString("Brand");
                        String Model=row.getString("Model");
                        int year=row.getInt("Year");
                        CarsInfo.add(new CarInfo(carplate,name,brand,Model,year));
                    }
                    CI.notifyDataSetChanged();
                    ls.setAdapter(CI);
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);




        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AddCar.class);
                startActivity(i);
            }
        });
        Check.setOnClickListener(v -> {
            StringRequest plateRequest=checkCar(
                    Plate.getText().toString());
                    queue.add(plateRequest);

        });

    }
    public StringRequest checkCar(String platnum){
        StringRequest request=new StringRequest(1, CheckURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Can't Display Info", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("platecarnum",platnum);
                return params;
            }
        };
        return request;
    }
}