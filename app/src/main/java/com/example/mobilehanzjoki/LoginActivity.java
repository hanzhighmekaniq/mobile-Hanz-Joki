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
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText txt_email, txt_password;

    private Button btn_login;

    private TextView txt_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        txt_reg = findViewById(R.id.txt_reg);
        btn_login = findViewById(R.id.btn_login);

        txt_reg.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });

        btn_login.setOnClickListener(v-> {
            String email = txt_email.getText().toString().trim();
            String password = txt_password.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Email / Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            } else {
//                processLogin(email, password);

                String url = new Configurasi().baseUrl()+"api-login.php";

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);

                            int statusCode = object.getInt("status_code");
                            String message = object.getString("message");

                            if (statusCode == 200 && message.equals("Success")) {
                                Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                ){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("email", email);
                        map.put("pw", password);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });
    }

//    private void processLogin(String email, String password) {
//
//        String url = new Configurasi().baseUrl()+"api-login.php";
//
//        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
//            try {
//                JSONObject object = new JSONObject(response);
//
//                int statusCode = object.getInt("status_code");
//                String message = object.getString("message");
//
//                if (statusCode == 200 && message.equals("Success")) {
//                    Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                } else {
//                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }, error -> {
//            error.printStackTrace();
//
//            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
//        }) {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("email", email);
//                map.put("pw", password);
//                return map;
//            }
//        };


//    }
}