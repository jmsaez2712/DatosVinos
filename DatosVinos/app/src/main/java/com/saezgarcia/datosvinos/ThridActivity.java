package com.saezgarcia.datosvinos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saezgarcia.datosvinos.csv.Csv;
import com.saezgarcia.datosvinos.vinos.Vino;

public class ThridActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName() + "zyx";

    //Declaramos las variables que si van a necesitar ser accedidas habitualmente por diferentes métodos de la clase.
    private Vino vin;
    private EditText etId, etNombre, etBodega, etColor, etOrigen, etGraduacion, etFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        initialize();
    }

    /*Método para declarar y/o instanciar los componentes necesarios de la interfaz que no van a tener que se necesitados
    * por varios métodos a parte de este
    * */
    private void initialize() {

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("idVino");

        String arr[] = Csv.leeArchivoConId(getFilesDir(), id, getString(R.string.csv_file));

        vin = new Vino(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], Double.parseDouble(arr[5]), Integer.parseInt(arr[6].trim()));

        Button btEdit = findViewById(R.id.btEditarMod);
        Button btDelete = findViewById(R.id.btDeleteMod);

        etId = findViewById(R.id.etIdMod);
        etId.setEnabled(false);
        etId.setText(String.valueOf(vin.getId()));

        etNombre = findViewById(R.id.etNombreMod);
        etNombre.setText(vin.getNombre());

        etBodega = findViewById(R.id.etBodegaMod);
        etBodega.setText(vin.getBodega());

        etColor = findViewById(R.id.etColorMod);
        etColor.setText(vin.getColor());

        etOrigen = findViewById(R.id.etOrigenMod);
        etOrigen.setText(vin.getOrigen());

        etGraduacion = findViewById(R.id.etGraduacionMod);
        etGraduacion.setText(String.valueOf(vin.getGraduación()));

        etFecha = findViewById(R.id.etFechaMod);
        etFecha.setText(String.valueOf(vin.getFecha()));

        Button btCancel = findViewById(R.id.btCancelMod);

        btCancel.setOnClickListener((View v) ->{
            finish();
        });

        btEdit.setOnClickListener((View v) ->{
            editVino(vin);
        });

        btDelete.setOnClickListener((View v) ->{
            deleteVino();
        });
    }

    //Método que permite el borrado de un vino.
    private void deleteVino() {
        String id = etId.getText().toString();
        Csv.borraFila(getFilesDir(), id, getString(R.string.csv_file));
    }

    //Método que permite la edición y su guardado en el archivo vinos.csv
    private void editVino(Vino vin) {

        String id = etId.getText().toString();
        String nombre = etNombre.getText().toString();
        String bodega = etBodega.getText().toString();
        String color = etColor.getText().toString();
        String origen = etOrigen.getText().toString();
        String graduacion = etGraduacion.getText().toString();
        String fecha = etFecha.getText().toString();


        vin.setId(Integer.parseInt(id));
        vin.setNombre(nombre);
        vin.setBodega(bodega);
        vin.setColor(color);
        vin.setOrigen(origen);
        vin.setGraduación(Double.parseDouble(graduacion));
        vin.setFecha(Integer.parseInt(fecha));

        Csv.borraFila(getFilesDir(), id, getString(R.string.csv_file));
        Csv.escribeArchivo(getFilesDir(), vin, getString(R.string.csv_file));

        Toast.makeText(this, "Vino editado", Toast.LENGTH_SHORT).show();
        
        finish();
        
    }
}