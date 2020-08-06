package com.example.myapplication;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper databaseHelper;
    private EditText nameEditText,passwordEditText;
    private Button loginButton,signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        nameEditText = (EditText) findViewById(R.id.nameText);
        passwordEditText = (EditText) findViewById(R.id.passwordText);

        signupButton = (Button) findViewById(R.id.singupButtonId);
        signupButton.setOnClickListener(this);
        loginButton = (Button) findViewById(R.id.loginButtonId);
    }

    @Override
    public void onClick(View v) {

        String name = nameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(v.getId() == R.id.loginButtonId) {
            boolean userFound = databaseHelper.findUser(name,password);
            if(userFound){
                CurrentUser.userName = name;
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this, "wrong credentials", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId() == R.id.singupButtonId){
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        }

    }

    public void showData(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.show();
    }
}
