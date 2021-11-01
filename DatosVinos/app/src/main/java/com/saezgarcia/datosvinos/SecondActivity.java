package com.saezgarcia.datosvinos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.saezgarcia.datosvinos.csv.Csv;
import com.saezgarcia.datosvinos.vinos.Vino;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName() + "zyx";

    //Declaracion de las variables que se usarán en diferentes métodos de la clase.
    EditText etId, etNombre, etBodega, etColor, etOrigen, etGraduacion, etFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    /* Método para declarar y/o instanciar los componentes necesarios de la interfaz que no van a tener que ser necesitados
     * por varios métodos a parte de este.
     * */
    private void initialize() {
       etId = findViewById(R.id.etId);
       etNombre = findViewById(R.id.etNombre);
       etBodega = findViewById(R.id.etBodega);
       etColor = findViewById(R.id.etColor);
       etOrigen = findViewById(R.id.etOrigen);
       etGraduacion = findViewById(R.id.etGraduacion);
       etFecha = findViewById(R.id.etFecha);

        TextView tvId = findViewById(R.id.tvId);
        TextView tvNombre = findViewById(R.id.tvNombre);
        TextView tvBodega = findViewById(R.id.tvBodega);
        TextView tvColor = findViewById(R.id.tvColor);
        TextView tvOrigen = findViewById(R.id.tvOrigen);
        TextView tvGraduacion = findViewById(R.id.tvGraduacion);
        TextView tvFecha = findViewById(R.id.tvFecha);

        Button btAdd = findViewById(R.id.btAdd);
        Button btCancel = findViewById(R.id.btCancelAdd);

        btAdd.setOnClickListener((View v)->{
            añadeVino();
        });

        btCancel.setOnClickListener((View v)->{
            finish();
        });

    }

    /* Método boolean que comprueba si la inserción del nuevo Vino se hizo de forma correcta
    *  para poder indicar al usuario si se ha hecho correctamente o no
    */
    private boolean añadeVinoResult(boolean valor){
        boolean result = false;
        if(valor){
            Toast.makeText(this, "Vino añadido", Toast.LENGTH_SHORT).show();
            result = true;
        } else {
            Toast.makeText(this, "Hubo un error, puede que ese ID ya exista", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    /* Método que obtiene los datos introducidos por el usuario para poder crear un objeto
    *  Vino y poder escribirlo. Si la inserción se hace correctamente, finaliza la actividad y vuelve a
    *  la pantalla principal.
    */
    private void añadeVino() {
        Vino v = new Vino(Integer.parseInt(etId.getText().toString()),
                etNombre.getText().toString(), etBodega.getText().toString(),
                etColor.getText().toString(), etOrigen.getText().toString(),
                Double.parseDouble(etGraduacion.getText().toString()), Integer.parseInt(etFecha.getText().toString()));

        if(añadeVinoResult(Csv.escribeArchivo(getFilesDir(), v, getString(R.string.csv_file)))){
            finish();
        }
    }



}