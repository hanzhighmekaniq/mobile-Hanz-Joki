package com.example.mobilehanzjoki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText txt_email, txt_first_name, txt_end_name, txt_username, txt_password;
    private Button btn_reg;
    private TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_email = findViewById(R.id.txt_email);
        txt_first_name = findViewById(R.id.txt_first_name);
        txt_end_name = findViewById(R.id.txt_end_name);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        btn_reg = findViewById(R.id.btn_reg);
        txt_login = findViewById(R.id.txt_login);


        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txt_email.getText().toString().length() == 0){
                    txt_email.setError("email tidak boleh kosong");
                }
                else if(txt_first_name.getText().toString().length() == 0){
                    txt_first_name.setError("nama depan tidak boleh kosong");
                }
                else if(txt_end_name.getText().toString().length() == 0){
                    txt_end_name.setError("nama belakang tidak boleh kosong");
                }
                else if(txt_username.getText().toString().length() == 0){
                    txt_username.setError("username tidak boleh kosong");
                }
                else if(txt_password.getText().toString().length() == 0){
                    txt_password.setError("password tidak boleh kosong");
                }
                else{

                    String url = new Configurasi().baseUrl()+"api_register.php";

                    StringRequest stringRequest = new StringRequest(
                            Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                        }
                    }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<String, String>();

                            params.put("email", txt_email.getText().toString());
                            params.put("nama_depan", txt_first_name.getText().toString());
                            params.put("nama_belakang", txt_end_name.getText().toString());
                            params.put("username", txt_username.getText().toString());
                            params.put("pw", txt_password.getText().toString());

                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });

    }
}