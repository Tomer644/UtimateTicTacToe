package com.example.utimatetictactoe;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    static EditText etUsername;
    EditText password;
    EditText testpas;
    Button btn1, btn2, btn3;
    DBHelper DB;
    AlertDialog ad;
    private FirebaseFirestore firestore;
    public List<Skin>skins;
    Map<String, Object>data;
    //Map<String, Boolean> skins;
    //ArrayList<Skin> skins;

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

        firestore = FirebaseFirestore.getInstance();
        skins = new ArrayList<>();
        makeSkinsList();
        data = new HashMap<String, Object>();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass= password.getText().toString();
                String repass=testpas.getText().toString();
                String user = etUsername.getText().toString();

                //map.put("username", user);
                //map.put("skins", skins);

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(RegisterActivity.this,"All fields Required",Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if (insert==true){
                                data.put(user, skins);
                                firestore.collection("users").document(etUsername.getText().toString()).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){

                                            Toast.makeText(RegisterActivity.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            //intent.putExtra("username", etUsername.getText().toString());
                                            startActivity(intent);

                                        }
                                        else//remove from sql
                                        {
                                            DB.deleteUser(user);
                                            Toast.makeText(RegisterActivity.this,"firebase bad, deleted from sql", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
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

    public void makeSkinsList(){
        skins.add(new Skin("Xs0",  true, SkinType.common));
        skins.add(new Skin("Os0", true, SkinType.common));
        for (int i = 1; i < 6; i++) {
            //List<Skin>skins = new ArrayList<>();
            SkinType skinType;
            if(i<3) skinType = SkinType.rare;
            else if (i<4) skinType = SkinType.epic;
            else skinType = SkinType.legendary;
            // 0  c
            //1,2 r
            //3 e
            //4, 5 l
            Skin skinX = new Skin("Xs"+i,  false, skinType);
            skins.add(skinX);
            Skin skinO = new Skin("Os"+i, false, skinType);
            skins.add(skinO);
        }
    }

//    @NonNull
//    public static List<Skin> getDefaultSkinsList(){
//        List<Skin>list = new ArrayList<>();
//        list.add(new Skin("X0",  true, SkinType.common));
//        list.add(new Skin("O0", true, SkinType.common));
//        return list;
//    }

    @NonNull
    public static String getUsername()
    {
        return etUsername.getText().toString();
    }
    /*private static void setUsername(String username)
    {
        etUsername.setText(username);
    }*/
}