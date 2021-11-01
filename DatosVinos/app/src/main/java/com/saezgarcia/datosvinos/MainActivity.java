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

public class MainActivity extends AppCompatActivity {

    //Declaramos las variables que si van a necesitar ser accedidas habitualmente por diferentes métodos de la clase.
    private EditText etId;
    private TextView tvVinos;
    private String CABECERA = "ID \tNombre \tBodega \tColor \tOrigen \tGraduacion \tFecha \n\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    /*Método para declarar y/o instanciar los componentes necesarios de la interfaz que no van a tener que se necesitados
     * por varios métodos a parte de este
     * */
    private void initialize() {
        etId = findViewById(R.id.etIdMain);

        Button btAdd = findViewById(R.id.btAddMain);
        btAdd.setOnClickListener((View v)->{
            goAddActivity();
        });

        Button btModify = findViewById(R.id.btModifyMain);
        btModify.setOnClickListener((View v) ->{
            goEditActivity();
        });

        tvVinos = findViewById(R.id.tvMain);
        tvVinos.setText(CABECERA + Csv.leeArchivo(getFilesDir(), getString(R.string.csv_file)).replace(";", " \t"));
    }

    //Método donde se hace un intent para ir hacia la activity de añadir un vino
    public void goAddActivity(){
        Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }

    //Método que se hace para hacer un intent hacia la activity de edit o eliminar un vino
    public void goEditActivity(){
        Intent i = new Intent(this, ThridActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("idVino", etId.getText().toString());
        i.putExtras(bundle);
        startActivity(i);
    }

    /*Sobreescribimos el método onResume() para poder actulizar en el TextView los vinos por cada modificacion
    * que se haga en archivo vinos.csv, ya sea añadir, modificar o eliminar.
    */
    @Override
    protected void onResume() {
        super.onResume();
        tvVinos.setText(CABECERA + Csv.leeArchivo(getFilesDir(), getString(R.string.csv_file)).replace(";", " \t"));
    }
}