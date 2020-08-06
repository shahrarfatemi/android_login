package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText,ageEditText,emailEditText,passwordEditText,classEditText;
    private Button signupButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = (EditText) findViewById(R.id.nameText);
        ageEditText = (EditText) findViewById(R.id.nameText);
        emailEditText = (EditText) findViewById(R.id.nameText);
        passwordEditText = (EditText) findViewById(R.id.nameText);
        classEditText = (EditText) findViewById(R.id.nameText);


        databaseHelper = new DatabaseHelper(this);
        signupButton = (Button) findViewById(R.id.signUpButtonId2);
        signupButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String name = nameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String clss = classEditText.getText().toString();
        if(v.getId() == R.id.singupButtonId){
            if(!(name.equals("") || password.equals("") || age.equals("") || email.equals("") || clss.equals(""))){
                int _age,_class;
                _age = Integer.parseInt(age);
                _class = Integer.parseInt(clss);
                long rowId = databaseHelper.insertToDatabase(name,email,password,_age,_class);
                if(rowId == -1){
                    Toast.makeText(SignUpActivity.this, "not inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SignUpActivity.this, "signed up", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
