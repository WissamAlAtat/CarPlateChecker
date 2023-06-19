package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddCar extends AppCompatActivity {
    private final static String SAVEURL = "https://mobilecsci410wissamalatat.000webhostapp.com/Project/savePlates.php";
    private final static String passbykey="pAsSbYkEy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        Button add=findViewById(R.id.btnAdd);
        Button reset=findViewById(R.id.btnReset);

        EditText CarNum=findViewById(R.id.txtPlateNumber);
        EditText Owner=findViewById(R.id.txtOwnerName);
        EditText Brand=findViewById(R.id.txtCarBranc);
        EditText Model=findViewById(R.id.txtModel);
        EditText Year=findViewById(R.id.txtDate);

        Button Reset=findViewById(R.id.btnReset);
        Button Add=findViewById(R.id.btnAdd);


        RequestQueue queue=Volley.newRequestQueue(this);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarNum.setText("");
                Owner.setText("");
                Brand.setText("");
                Model.setText("");
                Year.setText("");
            }
        });
        Add.setOnClickListener(v -> {
            StringRequest carRequest=saveCar(
                    CarNum.getText().toString(),
                    Owner.getText().toString(),
                    Brand.getText().toString(),
                    Model.getText().toString(),
                    Year.getText().toString());
                    queue.add(carRequest);
        });





    }
    public StringRequest saveCar(String platecarnum,String ownername,String brand,String model,String year){
        StringRequest request=new StringRequest(1, SAVEURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddCar.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCar.this, "Can't Add this Car,please fill all Fields", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("platecarnum",platecarnum);
                params.put("ownername",ownername);
                params.put("brand",brand);
                params.put("model",model);
                params.put("year",year);
                params.put("key ",passbykey);
                return params;
            }
        };
        return request;
    }
}