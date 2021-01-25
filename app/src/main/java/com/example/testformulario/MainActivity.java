package com.example.testformulario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText nombre;
    EditText email;
    EditText asunto;
    EditText contenido;
    Button datos;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        nombre = findViewById(R.id.editTextNombre);
        email = findViewById(R.id.editTextEmail);
        asunto = findViewById(R.id.editTextAsunto);
        contenido = findViewById(R.id.editTextContenido);
        datos = findViewById(R.id.btnDatos);

        datos.setOnClickListener(v -> CreateData());
    }

    private void CreateData() {
        String vNombre = nombre.getText().toString();
        String vEmail = email.getText().toString();
        String vAsunto = asunto.getText().toString();
        String vContenido = contenido.getText().toString();


        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", vNombre);
        map.put("Email", vEmail);
        map.put("Asunto", vAsunto);
        map.put("Contenido", vContenido);
        map.put("Fecha", new Date().getTime());
        db.collection("form").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this, "Datos enviados", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Los datos no se pueden enviar", Toast.LENGTH_SHORT).show();
            }
        });

    }
}