package com.example.evaluacion_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Clases.Pizza;

public class Promociones_act extends AppCompatActivity {
    private Spinner spnClientes;
    private EditText edTxtPromocion, edTxtValorEnvio;
    private TextView txtNombre, txtValorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones_act);

        spnClientes = (Spinner)findViewById(R.id.spnClientes);
        String[] listaClientes = {"Rosa", "Ramiro", "Robert"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listaClientes);
        spnClientes.setAdapter(adapter);

        edTxtPromocion = (EditText)findViewById(R.id.edTxtPromocion);
        edTxtValorEnvio = (EditText)findViewById(R.id.edTxtValorEnvio);

        txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtValorTotal = (TextView)findViewById(R.id.txtValorTotal);
    }

    public void Calcular(View view){
        String cliente = spnClientes.getSelectedItem().toString();
        String promocion = edTxtPromocion.getText().toString();

        Pizza pizza = new Pizza();

        try {
            int valorEnvio = Integer.parseInt(edTxtValorEnvio.getText().toString());

            if(promocion.equalsIgnoreCase("promo") || promocion.equalsIgnoreCase("pizza promo")) {
                txtNombre.setText("Estimado " + cliente + " el final según promoción y envio es:");
                txtValorTotal.setText("$" + (pizza.getPromoPizza() + valorEnvio));
            }
            else if(promocion.equalsIgnoreCase("master") || promocion.equalsIgnoreCase("master pizza")) {
                txtNombre.setText("Estimado " + cliente + " el final según promoción y envio es:");
                txtValorTotal.setText("$" + (pizza.getPizzaMaster() + valorEnvio));
            }
            else if(promocion.equalsIgnoreCase("max") || promocion.equalsIgnoreCase("pizza max")) {
                txtNombre.setText("Estimado " + cliente + " el final según promoción y envio es:");
                txtValorTotal.setText("$" + (pizza.getMaxPizza() + valorEnvio));
            }
            else {
                Toast.makeText(this, "Ingrese una promoción válida: Promo, Master o Max", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ingrese monto valido!", Toast.LENGTH_LONG).show();
        }



    }
}