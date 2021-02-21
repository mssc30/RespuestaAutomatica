package net.mssc.respuestaautomatica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtNumTelefono, txtMnsaje;
    Button btnAplicar;
    SharedPreferences misPreferencias;
    SharedPreferences.Editor miEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INICIALIZAR OBJETOS DE LA VISTA
        txtNumTelefono = findViewById(R.id.txtNumero);
        txtMnsaje = findViewById(R.id.txtMensaje);
        btnAplicar = findViewById(R.id.btnAplicar);

        //OBJETO PARA LEER Y ESCRIBIR EN EL OBJETO DE PREFERENCIAS
        misPreferencias = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        //OBJETO PARA EDITAR EL ARCHIVO PREFERENCIAS
        miEditor = misPreferencias.edit();

        //CUANDO SE LE DE CLIC AL BOTON
        btnAplicar.setOnClickListener(v->{
            //EN CASO DE QUE HAYA CONFIGURACION GUARDADA
            miEditor.remove("NUMERO");
            miEditor.remove("MENSAJE");

            //GUARDAR EL NUMERO Y EL MENSAJE AUTOMATICO
            miEditor.putString("NUMERO", txtNumTelefono.getText().toString());
            miEditor.putString("MENSAJE", txtMnsaje.getText().toString());

            //IMPORTANTE PARA GUARDAR CAMBIOS
            miEditor.commit();

            //INFORMAR AL USUARIO LOS CAMBIOS
            Toast.makeText(getApplicationContext(), "Cambios guardados", Toast.LENGTH_LONG).show();
        });

        //PONER LA CONFIGURACION EN LOS TEXT VIEW
        txtMnsaje.setText(misPreferencias.getString("MENSAJE", ""));
        txtNumTelefono.setText(misPreferencias.getString("NUMERO", ""));
    }
}