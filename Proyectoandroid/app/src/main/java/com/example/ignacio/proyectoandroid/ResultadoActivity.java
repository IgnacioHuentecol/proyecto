package com.example.ignacio.proyectoandroid;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ResultadoActivity extends AppCompatActivity {
    TextView anio_recibida;
    TextView patente_recibida;
    TextView marca_recibida;
    TextView modelo_recibida;
    TextView valoruf_recibida;
    TextView antiguedad_muestra;
    TextView asegurado_muestra;
    TextView valorseguro_muestra;
    ImageView muestraimagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        //declaramos recibir los parametros

        patente_recibida = (TextView) findViewById(R.id.txv_patente);
        marca_recibida = (TextView) findViewById(R.id.txv_marca);
        modelo_recibida = (TextView) findViewById(R.id.txv_modelo);
        anio_recibida = (TextView) findViewById(R.id.txv_anio);
        valoruf_recibida = (TextView) findViewById(R.id.txv_uf);
        antiguedad_muestra = (TextView) findViewById(R.id.txv_antiguedad);
        asegurado_muestra = (TextView) findViewById(R.id.txv_asegurado);
        valorseguro_muestra = (TextView) findViewById(R.id.txv_valor_uf_pagar);
        muestraimagen = (ImageView) findViewById(R.id.imv_estado);
        //creamos variables para recibir datos del activity anterior

        Bundle recibir = this.getIntent().getExtras();
        String patente_Recibe = recibir.getString("patente");
        String marca_Recibe = recibir.getString("marca");
        String modelo_Recibe = recibir.getString("modelo");
        int anio_Recibe = recibir.getInt("Añoingresado");
        int uf_Recibe = recibir.getInt("uf");
        int antiguedad = recibir.getInt("antiguedad");
        int estado = recibir.getInt("estado");
        int precio = recibir.getInt("precioS");
        //variables reciben y muestran datos en pantalla
        patente_recibida.setText(patente_Recibe);
        marca_recibida.setText(marca_Recibe);
        modelo_recibida.setText(modelo_Recibe);
        anio_recibida.setText(String.valueOf(anio_Recibe));
        valoruf_recibida.setText(String.valueOf(uf_Recibe));
        antiguedad_muestra.setText(String.valueOf(antiguedad));
        valorseguro_muestra.setText(String.valueOf(precio));

        //Resultado de los metodos aplicados
        determinarSeguro(estado);
        mostrarImagen(estado);
    }

    public void determinarSeguro(int estado) {
        if (estado == 0) {
            asegurado_muestra.setText("SI");
        } else {
            asegurado_muestra.setText("NO");
        }
    }

    public void mostrarImagen(int estado) {

        if (estado == 0) {
            muestraimagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.seguro));
            Toast.makeText(getApplicationContext(), "Es seguro", Toast.LENGTH_SHORT).show();
        } else {
            muestraimagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.noseguro));
            Toast.makeText(getApplicationContext(), "No es asegurable", Toast.LENGTH_SHORT).show();
        }
    }


}


