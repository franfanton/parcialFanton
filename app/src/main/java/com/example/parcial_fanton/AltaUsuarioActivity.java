package com.example.parcial_fanton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AltaUsuarioActivity extends AppCompatActivity {

    private TextView textView;
    private SeekBar seekBar;
    private EditText nombre,contrasenia1,contrasenia2,correo;
    private Spinner mesNacimiento,anioNacimiento;


    @SuppressLint("SetTextI18n")
    //se agregaron las annotation para que deje de mostrar warnings
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_usuario);
        Toolbar toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);

        final Button boton = findViewById(R.id.botonregistrarme);

        nombre = (EditText) findViewById(R.id.nombre);
        contrasenia1 = (EditText) findViewById(R.id.contrasenia1);
        contrasenia2 = (EditText) findViewById(R.id.contrasenia2);
        correo = (EditText) findViewById(R.id.correo);
        mesNacimiento = (Spinner) findViewById(R.id.mesNacimiento);
        anioNacimiento = (Spinner) findViewById(R.id.anioNacimiento);
        CheckBox terminosycondiciones = (CheckBox) findViewById(R.id.terminosycondiciones);
        textView = (TextView) findViewById(R.id.textoCargaInicial);
        seekBar = (SeekBar) findViewById(R.id.seekBarCargaInicial);
        textView.setText("Credito Inicial: " + seekBar.getProgress() + "/" + seekBar.getMax());
        mesNacimiento.setEnabled(false);
        anioNacimiento.setEnabled(false);


        terminosycondiciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    boton.setEnabled(true);
                } else {
                    boton.setEnabled(false);
                }

            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailPattern = getString(R.string.mailCorrecto);
                //ver ejemplo email@utn.frsf.edu.ar, se cambió el texto de lugar a values/strings.xml
                Calendar calendar = Calendar.getInstance();
                int mesEnero = 1;
                int mesAhora = calendar.get(Calendar.MONTH)+1;
                int anioAhora = calendar.get(Calendar.YEAR);
                int mesVencTarj = 0;
                int anioVencTarj = 0;
                int value = seekBar.getProgress();

                if(nombre.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "El campo nombre esta vacio.", Toast.LENGTH_SHORT).show();
                }
                else if(contrasenia1.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "El campo contraseña esta vacio.", Toast.LENGTH_SHORT).show();
                }
                else if((!(contrasenia1.getText().toString().equals(contrasenia2.getText().toString()))) ||
                        contrasenia2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                }
                else if(correo.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "El campo correo esta vacio.", Toast.LENGTH_SHORT).show();
                }
                else if (!(correo.getText().toString().trim().matches(emailPattern))) {
                    Toast.makeText(getApplicationContext(),"Ingrese un correo valido",Toast.LENGTH_SHORT).show();
                }
                else {

                        Toast.makeText(getApplicationContext(), "Registro Exitoso!", Toast.LENGTH_SHORT).show();
                        String nombreUsuario = nombre.getText().toString();
                        String contraseniaUsuario = contrasenia1.getText().toString();
                        String correoUsuario = correo.getText().toString();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menuinicial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch(item.getItemId()){
            case R.id.itemRegistrar:
                Toast.makeText(this, "Selecciono Registrarse", Toast.LENGTH_SHORT).show();
                i = new Intent(AltaUsuarioActivity.this, AltaUsuarioActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }
}