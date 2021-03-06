package com.example.mpip.freeride;

import android.content.Intent;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RenterRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.constrainLayout){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }

    EditText et1, et2, et3, et4;
    FloatingActionButton fab;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_register);

        et1 = (EditText) findViewById(R.id.reg_ime);
        et2 = (EditText) findViewById(R.id.reg_surname);
        et3 = (EditText) findViewById(R.id.reg_tel);
        et4 = (EditText) findViewById(R.id.store_name);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constrainLayout);
        constraintLayout.setOnClickListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et1.getText().toString();
                String surname = et2.getText().toString();
                String tel = et3.getText().toString();
                String storeName = et4.getText().toString();

                if(name.equals("") || surname.equals("") || tel.equals("") || storeName.equals(""))
                {
                    Toast.makeText(v.getContext(), "Please fill out all the fields.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!validateName() || !validateSurname() || !validatePhoneNo()){}
                    else {
                        Intent fromIntent = getIntent();
                        String email = fromIntent.getStringExtra("email");
                        String pass = fromIntent.getStringExtra("pass");
                        Toast.makeText(getApplicationContext(), "\"Let's pick your shop address...", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), RenterMapsActivity.class);
                        i.putExtra("email", email);
                        i.putExtra("pass", pass);
                        i.putExtra("name", name);
                        i.putExtra("surn", surname);
                        i.putExtra("tel", tel);
                        i.putExtra("storeName", storeName);
                        startActivity(i);
                    }
                }
            }
        });
    }

    private Boolean validateName() {
        String val = et1.getText().toString();
        String namePattern="^[A-Z][a-z]{2,19}";
        if (!val.matches(namePattern)) {
            et1.setError("Enter a valid name!");
            return false;
        } else {
            et1.setError(null);
            return true;
        }
    }

    private Boolean validateSurname() {
        String val = et2.getText().toString();
        String phonePattern="^[A-Z][a-z]{2,19}";
        if (!val.matches(phonePattern)) {
            et2.setError("Enter a valid surname!");
            return false;
        } else {
            et2.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = et3.getText().toString();
        String phonePattern="^07[0-9]{7}";
        if (!val.matches(phonePattern)) {
            et3.setError("Incorrect phone number!");
            return false;
        } else {
            et3.setError(null);
            return true;
        }
    }
}
