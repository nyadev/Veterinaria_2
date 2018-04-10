package com.example.axelcuevas.veterinaria_2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText id, name;
    private Button alta, consulta, baja, cambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.id);
        name = findViewById(R.id.nombre);

        alta = findViewById(R.id.alta);
        consulta = findViewById(R.id.consulta);
    }

    public void agregar(View v){
        BDatos objBD = new BDatos(this, "Mascotas", null, 1);
        SQLiteDatabase bd = objBD.getWritableDatabase();
        String clave = id.getText().toString();
        String nombre = name.getText().toString();

        if(!nombre.isEmpty() && !clave.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("clave",clave);
            registro.put("nombre", nombre);
            bd.insert("animales", null, registro);
            bd.close();
            id.setText("");
            name.setText("");
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void consulta(View v){
        BDatos objBD = new BDatos(this, "Mascotas", null, 1);
        SQLiteDatabase bd = objBD.getReadableDatabase();
        String clave = id.getText().toString();
        if(!clave.isEmpty()){
            Cursor fila = bd.rawQuery("select nombre from animales where clave =" + clave, null);
            if(fila.moveToFirst()){
                name.setText(fila.getString(0));
                bd.close();
            }
            else{
                Toast.makeText(this, "No exite la mascota", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Debes escribir la clave de la mascota", Toast.LENGTH_SHORT).show();
        }
    }

/*    public void baja(){
        BDatos objBD = new BDatos(this, "Mascotas", null, 1);
        SQLiteDatabase bd = objBD.getWritableDatabase();
        String clave = id.getText().toString();
        if(!clave.isEmpty()){
            bd.delete("animales", "clave = ?", new String[]{clave});
            Toast.makeText(this, "Borrado Exitoso", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Debes escribir la clave de la mascota", Toast.LENGTH_SHORT).show();
        }
    }

    public void cambio(){
        BDatos objBD = new BDatos(this, "Mascotas", null, 1);
        SQLiteDatabase bd = objBD.getWritableDatabase();
        String clave = id.getText().toString();
        String nombre = name.getText().toString();

        if(!nombre.isEmpty() && !clave.isEmpty()){
            ContentValues cambio = new ContentValues();
            cambio.put("nombre", nombre);
            bd.update("animales", cambio, "clave="+clave, null);
            bd.close();
            name.setText("");
            Toast.makeText(this, "Cambio Exitoso", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }*/
}
