package com.example.evaluacion_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Clases.Cliente;


public class Listado_act extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Cliente clienteSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_act);

        listView = (ListView)findViewById(R.id.listView);

        InicializarBase();

        databaseReference.child("Clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Cliente cliente = dataSnapshot.getValue(Cliente.class);
                    listaClientes.add(cliente);

                    ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_expandable_list_item_1, listaClientes);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clienteSeleccionado = (Cliente)adapterView.getItemAtPosition(i);
            }
        });
    }

    public void EliminarCliente(View view) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteSeleccionado.getId());
        databaseReference.child("Clientes").child(cliente.getId()).removeValue();

        Toast.makeText(this, "Se ha eliminado el cliente", Toast.LENGTH_LONG).show();
    }

    public void InicializarBase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}