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

public class Mainregister extends AppCompatActivity {
    private EditText namedit, passedit,gmaieit;
    private Button  register;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registes);
        auth = FirebaseAuth.getInstance();
        gmaieit = findViewById(R.id.GMAIL);
        passedit = findViewById(R.id.MATKHAU);
        namedit=findViewById(R.id.TENNGUOIDUNG);
        register = findViewById(R.id.TAOTAIKHOANG);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }


        });
    }
    private void register() {
        String name, pass,gmail;
        name = namedit.getText().toString();
        pass = passedit.getText().toString();
        gmail=gmaieit.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Nhập lại tên người dùng",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Nhập lại mật khẩu",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(gmail)) {
            Toast.makeText(this, "Nhập lại gmail", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(gmail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Tạo thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent( Mainregister.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Tạo không thành công",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
