package com.example.evaluacion_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;

import java.util.UUID;

import Clases.Cliente;


public class Firebase_act extends AppCompatActivity {
    private EditText edTxtNombre, edTxtDestino, edTxtPromo;
    private Button btnGuardar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_act);

        edTxtNombre = (EditText)findViewById(R.id.edTxtNombre);
        edTxtDestino = (EditText)findViewById(R.id.edTxtDestino);
        edTxtPromo = (EditText)findViewById(R.id.edTxtPromo);

        btnGuardar = (Button)findViewById(R.id.btnGuardar);

        InicializarBase();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edTxtNombre.getText().toString();
                String destino = edTxtDestino.getText().toString();
                String promo = edTxtPromo.getText().toString();

                if(!nombre.isEmpty() || !destino.isEmpty() || !promo.isEmpty()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(UUID.randomUUID().toString());
                    cliente.setNombre(nombre);
                    cliente.setDestino(destino);
                    cliente.setPromocion(promo);

                    databaseReference.child("Clientes").child(cliente.getId()).setValue(cliente);
                    Toast.makeText(getBaseContext(), "Cliente Guardado", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Debe de llenar todos los campos!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void InicializarBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void Listado(View view) {
        Intent intent = new Intent(this, Listado_act.class);
        startActivity(intent);
    }
}