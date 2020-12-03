package com.example.evaluacion_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edTxtUsuario, edTxtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTxtUsuario = (EditText)findViewById(R.id.edTxtUsuario);
        edTxtPass = (EditText)findViewById(R.id.edTxtPass);
    }

    public void Menu(View view) {
        String usuario = edTxtUsuario.getText().toString();
        String pass = edTxtPass.getText().toString();

        if(usuario.equalsIgnoreCase("android") && pass.equalsIgnoreCase("123")){
            Intent intent = new Intent(this, Menu_act.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Usuario y/p Constraseña Incorrecta(s)", Toast.LENGTH_LONG).show();
        }
    }
}