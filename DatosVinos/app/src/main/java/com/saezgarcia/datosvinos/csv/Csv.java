package com.saezgarcia.datosvinos.csv;



import static android.provider.Settings.Global.getString;

import android.util.Log;
import android.widget.Toast;

import com.saezgarcia.datosvinos.MainActivity;
import com.saezgarcia.datosvinos.R;
import com.saezgarcia.datosvinos.vinos.Vino;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Csv {

    public static final String TAG = MainActivity.class.getName() + "zyx";

    //Método con el que escribimos un Vino en el archivo vinos.csv de forma interna.
    public static boolean escribeArchivo(File file, Vino v, String archivo){

        boolean ok = true;
        boolean exist = true;
        File f = new File(file, archivo);
        FileWriter fw = null;
        String str = v.getId() +"; " + v.getNombre() + "; " + v.getBodega() + "; "
                + v.getColor() + "; " + v.getOrigen() + "; " + v.getGraduación() + "; "
                + v.getFecha() + "; ";
        try{
            if(v.equals(leeArchivoId(file, String.valueOf(v.getId()), archivo))){
                fw = new FileWriter(f,true);
                fw.write(str);
                fw.write("\n");
                fw.flush();
                fw.close();
            } else { exist = false;
               return exist;
            }

        }catch(Exception e){
            ok = false;
            Log.v(TAG,e.toString());
        }

        return ok;
    }

    //Método que obtiene del archivo vinos.csv un vino a partir del id que tiene asignado
    public static Vino leeArchivoId(File file, String id, String archivo){

        String[] str;
        File f = new File(file, archivo);

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while((linea = br.readLine()) != null){
              str = linea.trim().split(";");
                if(id.equals(str[0])){
                    Vino v = new Vino(Integer.parseInt(str[0]), str[1], str[2], str[3], str[4],
                            Double.parseDouble(str[5]), Integer.parseInt(str[6]));
                    return v;
                }
            }
        } catch (Exception e){
            Log.v(TAG,e.toString());
        }
        return null;
    }

    /*Método que lee todo el archivo vinos.csv devolviendo una String con los datos almacenados
    * dentro de este
    */
    public static String leeArchivo(File file, String archivo){
        String str = "";
        File f = new File(file, archivo);

        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while((linea = br.readLine()) != null){
                str += linea+"\n";
            }
        } catch (Exception e) {
            Log.v(TAG,e.toString());
        }

        return str;
    }

    /* Método que devuelve un Array de String con los datos correspodientes de un vino segun el id pasado,
    * siempre y cuando este exista en el archivo vinos.csv
    */
    public static String[] leeArchivoConId(File file, String id, String archivo){

        String[] str;
        File f = new File(file, archivo);

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while((linea = br.readLine()) != null){
                str = linea.split(";");
                if(id.equals(str[0])){
                    return str;
                }
            }
        } catch (Exception e){
            Log.v(TAG,e.toString());
        }
        return null;
    }

    /* Método que a partir de un id de un vino, almacena todos los demas vinos en un archivo temporal,
    *  lo que nos permite aislar el vino seleccionado por id. Posteriormente se elimina el archivo principal con el vino
    * seleccionado y se renombra el archivo temporal para convertirlo en el archivo principal, pero sin el vino seleccionado
    */
    public static void borraFila(File file, String id, String archivo){
        File f = new File(file, archivo);
        File f2 = new File(file, "temp.tmp");
        String str[];
        String tmp;
        try {
            FileWriter fw = new FileWriter(f2);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while((linea = br.readLine()) != null){
                str = linea.split(";");
                if(!id.equals(str[0])){
                   tmp = linea;
                   fw.write(tmp);
                   fw.write("\n");
                   fw.flush();
                }
            }
            fw.close();
            br.close();

            f.delete();
            f2.renameTo(f);
        } catch (Exception e){
            Log.v(TAG,e.toString());
        }

    }
}
