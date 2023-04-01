package com.example.utimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    static EditText etUsername;
    EditText password;
    EditText testpas;
    Button btn1, btn2, btn3;
    DBHelper DB;
    AlertDialog ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        etUsername=findViewById(R.id.name);
        password=findViewById(R.id.password);
        testpas=findViewById(R.id.testpas);
        DB = new DBHelper(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass= password.getText().toString();
                String repass=testpas.getText().toString();
                String user = etUsername.getText().toString();

                if (TextUtils.isEmpty(etUsername.getText().toString()) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(RegisterActivity.this,"All fields Required",Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if (insert==true){
                                Toast.makeText(RegisterActivity.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                //intent.putExtra("username", etUsername.getText().toString());
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,"Registered Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"User already Exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"Password are not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);*/

                AlertDialog.Builder dbuilder = new AlertDialog.Builder(RegisterActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.activity_login,null);
                dbuilder.setView(dialogView);
                ad = dbuilder.create();
                ad.show();
                etUsername=dialogView.findViewById(R.id.username);
                password=dialogView.findViewById(R.id.logpass);

                btn3 = dialogView.findViewById(R.id.btn3);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String user = etUsername.getText().toString();
                        String testpas= password.getText().toString();

                        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(testpas))
                            Toast.makeText(RegisterActivity.this, "all fields Required", Toast.LENGTH_SHORT).show();
                        else{
                            Boolean checkuserpass= DB.checkusernamepassword(user,testpas);
                            if (checkuserpass==true){
                                Toast.makeText(RegisterActivity.this, "Login Successful",Toast.LENGTH_SHORT).show();
                                //RegisterActivity.setUsername(user);
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                //intent.putExtra("username", user);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }

        });


    }

    public static String getUsername()
    {
        return etUsername.getText().toString();
    }
    public static void setUsername(String username)
    {
        etUsername.setText(username);
    }
}