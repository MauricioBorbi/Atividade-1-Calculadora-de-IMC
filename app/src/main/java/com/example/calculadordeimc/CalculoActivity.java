package com.example.calculadordeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CalculoActivity extends AppCompatActivity {
    TextView calculo;
    Float fltPeso, fltAltura, fltCalculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        calculo = (TextView) findViewById(R.id.textViewCalculo);

        Intent intent = getIntent();
        fltAltura = Float.parseFloat(intent.getStringExtra("altura"));
        fltPeso = Float.parseFloat(intent.getStringExtra("peso"));
        fltCalculo = fltPeso / (fltAltura * fltAltura);

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

        calculo.setText(strCalculo);
    }
}