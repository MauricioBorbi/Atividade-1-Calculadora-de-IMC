package com.example.calculadordeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText peso,altura;
    Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peso = (EditText) findViewById(R.id.editTextPeso);
        altura = (EditText) findViewById(R.id.editTextAltura);
        botao = (Button) findViewById(R.id.buttonCalcular);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTelaCalculo();
            }
        });
    }
    public void mostrarTelaCalculo(){
        if(TextUtils.isEmpty(peso.getText().toString())){
            peso.setError("Campo obrigatório!");
            return;
        }

        if(TextUtils.isEmpty(altura.getText().toString())){
            altura.setError("Campo obrigatório!");
            return;
        }

        Intent intent = new Intent(this, CalculoActivity.class);
        intent.putExtra("altura",altura.getText().toString());
        intent.putExtra("peso",peso.getText().toString());
        startActivity(intent);
    }
}