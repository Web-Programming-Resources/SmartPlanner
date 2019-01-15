package com.smartplanner;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements OnJsonReceived, OnUserCreated {
    private static String serverSignUpAddress;
    private static String serverLoginAddress;

    private EditText editTextAddress;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private String token;
    private TextView textViewSignUp;
    boolean received = false;
    public static String serverAddress;
    ProgressDialog progressDialog;
    AlertDialog signUpDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login();
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_signup, null);
        final EditText editTextEmail = view.findViewById(R.id.editText_dialog_email);
        final EditText editTextPassword = view.findViewById(R.id.editText_dialog_password);
        Button buttonSignup = view.findViewById(R.id.button_dialog_signup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if(!login.contains("@")) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.string_wrong_email), Toast.LENGTH_LONG).show();
                } else if(login.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.string_empty_email_or_password), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("email", login);
                        jsonObject.put("username", login);
                        jsonObject.put("password", password);
                        Log.i("json_sent_to_server", jsonObject.toString());
                        new SendDataToServer(LoginActivity.this).execute(serverSignUpAddress, jsonObject.toString());

                    } catch (JSONException ex){
                        ex.printStackTrace();
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.string_account_creation_failed), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.setView(view);
        signUpDialog = builder.create();
        signUpDialog.show();
    }

    private void login(){
        serverAddress = editTextAddress.getText().toString();
        Log.i("address_", serverAddress);
        serverSignUpAddress = serverAddress + "/api/users/signup";
        serverLoginAddress = serverAddress + "/api/token/generate";

        // TODO:: validate function
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_MaterialComponents_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", email);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("request_body", jsonBody.toString());
        new GetTokenFromServer(this, jsonBody.toString()).execute(serverLoginAddress);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(received)
                            progressDialog.dismiss();
                    }
                }, 3000);
    }

    private void onLoginSuccess(){
        progressDialog.dismiss();
        buttonLogin.setEnabled(true);
        Intent intent = new Intent(this, ShowPlanActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    private void onLoginFailed(){
        Toast.makeText(this, R.string.string_wrong_login, Toast.LENGTH_LONG).show();
        buttonLogin.setEnabled(true);
    }

    private void onServerConnectionError(){
        Toast.makeText(this, R.string.string_server_connection_error_message, Toast.LENGTH_LONG).show();
        buttonLogin.setEnabled(true);
    }

    @Override
    public void jsonReceived(String jsonString) {
        received = true;
        if (jsonString.toLowerCase().contains(getResources().getString(R.string.string_server_connection_error))){
            onServerConnectionError();
        }
        else {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                if(jsonObject.has("token")) {
                    token = jsonObject.getString("token");
                    onLoginSuccess();
                } else if(jsonObject.has("status")){
                    Log.i("jsonReceived_error", Integer.toString(jsonObject.getInt("status")));
                    onLoginFailed();

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void initViews(){
        editTextEmail = findViewById(R.id.editText_email);
        editTextPassword = findViewById(R.id.editText_password);
        buttonLogin = findViewById(R.id.button_login);
        textViewSignUp = findViewById(R.id.textView_signup);
        editTextAddress = findViewById(R.id.editText_address);
    }

    @Override
    public void onUserCreated(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            signUpDialog.dismiss();
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.string_account_created), Toast.LENGTH_LONG);

        } catch (JSONException ex){
            ex.printStackTrace();
            signUpDialog.dismiss();
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.string_account_creation_failed), Toast.LENGTH_LONG);
        }
    }
}
