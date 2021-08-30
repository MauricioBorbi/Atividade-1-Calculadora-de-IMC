package com.example.calculadordeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public EditText peso, altura;
    public Button botao;
    public TextView calculoResultado;
    public Float fltCalculo;
    public TextView salvarDado;
    private SharedPreferences save;
    private SharedPreferences.Editor    edit;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peso = (EditText) findViewById(R.id.editTextPeso);
        altura = (EditText) findViewById(R.id.editTextAltura);
        botao = (Button) findViewById(R.id.buttonCalcular);
        salvarDado = (TextView) findViewById(R.id.salvarDado);
        calculoResultado = (TextView) findViewById(R.id. calculoResultado);

        save = getSharedPreferences("baseDados", Context.MODE_PRIVATE);
        edit = save.edit();
        salvarDado.setText("peso: " + save.getString("peso", "não infomado") + " / " + "altura: " + save.getString("altura", "não informada"));
        calculoResultado.setText("IMC = " + save.getString("fltCalculo", "0"));
        mostrarTelaCalculo();

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.putString("peso", (peso.getText().toString()));
                edit.putString("altura", (altura.getText().toString()));
                edit.commit();
                fltCalculo = (Float.valueOf(save.getString("peso", "0")) / ( Float.valueOf(save.getString("altura", "0")) * Float.valueOf(save.getString("altura", "0"))));
                edit.putString("fltCalculo", String.valueOf(fltCalculo));
                edit.commit();
                mostrarTelaCalculo();
                salvarDado.setText("peso: " + save.getString("peso", "não infomado") + " / " + "altura: " + save.getString("altura", "não informada"));
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

        fltCalculo = (Float.valueOf(save.getString("peso", "0")) / ( Float.valueOf(save.getString("altura", "0")) * Float.valueOf(save.getString("altura", "0"))));

        String strCalculo;
        strCalculo = "IMC = " + fltCalculo.toString();

        if(fltCalculo < 17) {
            strCalculo = strCalculo + "\n" + "Peso pena, muito magro, como e que ta vivo?!!";
        }
        else if(fltCalculo < 18.49){
            strCalculo = strCalculo + "\n" + "Peso Frango, vai comer e treinar!!";
        }
        else if(fltCalculo < 24.99){
            strCalculo = strCalculo + "\n" + "Peso galo, tá na dieta e no treino certinho, parabéns";
        }
        else if(fltCalculo < 29.99){
            strCalculo = strCalculo + "\n" + "Saiu da dieta né, ta acima do peso";
        }
        else if(fltCalculo < 34.99){
            strCalculo = strCalculo + "\n" + "Obesidade I, você está gordo, fecha a boca";
        }
        else if(fltCalculo < 39.99){
            strCalculo = strCalculo + "\n" + "Gordo demais, Obesidade II (severa)";
        }
        else {
            strCalculo = strCalculo + "\n" + "Você está obeso, procurar um médico - Obesidade III (mórbida)";
        }

        calculoResultado.setText(strCalculo);
    }
}