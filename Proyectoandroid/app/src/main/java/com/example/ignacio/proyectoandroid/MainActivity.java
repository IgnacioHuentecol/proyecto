package com.example.ignacio.proyectoandroid;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    //Creamos objetos relacionados al layout
    EditText patente;
    EditText modelo;
    EditText año;
    EditText valorUF;
    Button calcular;
    Spinner marca;
    String marcaSeleccionada;

    ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asociamos los objetos con un elemento de la interfaz
        patente = (EditText) findViewById(R.id.edt_patente);
        marca = (Spinner) findViewById(R.id.spnmarca);
        modelo = (EditText) findViewById(R.id.edt_modelo);
        año = (EditText) findViewById(R.id.edt_año);
        valorUF = (EditText) findViewById(R.id.edt_uf);
        calcular = (Button) findViewById(R.id.btn_registro);

        // imagen = (ImageView) findViewById(R.id.imv_resultado);
        //Habilitamos la posibilidad de que el boton ejecute operaciones en el metodo OnClick
        calcular.setOnClickListener(this);
        //Creamos el adaptador del spinner a utiliza
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marca, android.R.layout.simple_spinner_item);

        // Especifique el diseño a utilizar cuando aparezca la lista de opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplique el adaptador a la hiladora
        marca.setAdapter(adapter);

        marca.setOnItemSelectedListener(this);

    }



    @Override
    public void onClick(View view) {
        if (!año.getText().toString().equals("")) {
            //Parseamos los datos de string a int
            int año_ingresado = Integer.parseInt(año.getText().toString());

            String patente_ingresada = patente.getText().toString();
            String modelo_ingresado = modelo.getText().toString();
            int uf_ingresada = Integer.parseInt(valorUF.getText().toString());


            //resultados de los metodos
            int anio_actual = Calendar.getInstance().get(Calendar.YEAR);
            int añopermitidos = 10;
            int antiguedad_recibida = calculoantiguedad(año_ingresado, anio_actual);
            int estado = determinar_Estado(añopermitidos, antiguedad_recibida);
            int precioS = calcularUf(antiguedad_recibida, uf_ingresada);

            Intent calcular = new Intent(MainActivity.this, ResultadoActivity.class);
            //traspasamos los datos q se enviaran al activity resultado
            calcular.putExtra("patente", patente_ingresada);
            calcular.putExtra("Añoingresado", año_ingresado);
            calcular.putExtra("modelo", modelo_ingresado);
            calcular.putExtra("uf", uf_ingresada);
            calcular.putExtra("antiguedad", antiguedad_recibida);
            calcular.putExtra("estado", estado);
            calcular.putExtra("precioS", precioS);
            calcular.putExtra("marca",marcaSeleccionada);

            startActivity(calcular);


        } else {
            Toast.makeText(getApplicationContext(), "Debe ingresar el año del vehiculo a consultar", Toast.LENGTH_SHORT).show();
        }

    }

    public int calculoantiguedad(int anioactual, int año_ingresado) {
        int antiguo;
        antiguo = año_ingresado-anioactual ;
        return antiguo;

    }

    public int determinar_Estado(int antiguedad, int añopermitidos) {
        int estado;
        if (añopermitidos >= antiguedad) {
            estado = 1;
        } else {
            estado = 0;
        }
        return estado;
    }

    public int calcularUf(int antiguedad_recibida, int uf_ingresada) {
        int seguro;

        if (antiguedad_recibida == 1) {
            seguro = antiguedad_recibida * uf_ingresada;
        } else {
            seguro = antiguedad_recibida * 0;
        }
        return seguro;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        marcaSeleccionada = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}










