package com.example.zooall;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Mainlogin extends AppCompatActivity {
    private EditText gmaildit, passedit;
    private Button login, register;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        gmaildit = findViewById(R.id.GMAIL);
        passedit = findViewById(R.id.MATKHAU);
        login = findViewById(R.id.DANGNHAP);
        register = findViewById(R.id.DANGKY);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void login() {
        String name, pass;
        name = gmaildit.getText().toString();
        pass = passedit.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Nhập lại tên người dùng",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Nhập lại mật khẩu",Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(name,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent( Mainlogin.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Đăng nhập không thành công",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private void register() {
        Intent i = new Intent(Mainlogin.this, Mainregister.class);
        startActivity(i);
    }

}
