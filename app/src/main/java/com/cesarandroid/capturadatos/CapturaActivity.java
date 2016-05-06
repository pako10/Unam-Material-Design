package com.cesarandroid.capturadatos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class CapturaActivity extends AppCompatActivity {

    private int año;
    private int mes;
    private int dia;
    EditText fehcaamostrar;
    Button dates;
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener SelectorFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);

        /** Se declara todos los editText que se estan utilanzo en la camputa de datos*/
        final EditText nom = (EditText) findViewById(R.id.Ednombre);
        fehcaamostrar = (EditText) findViewById(R.id.Efechamostrar);
        final EditText tel = (EditText) findViewById(R.id.Edtelefono);
        final EditText emaill = (EditText) findViewById(R.id.Edemal);
        final EditText des = (EditText) findViewById(R.id.Eddescrip);
        final Button sig = (Button) findViewById(R.id.Bsiguiente);
        dates = (Button) findViewById(R.id.Bfecha);

        sig.setOnClickListener(new Button.OnClickListener(){

            /** Metodo OnClik del Boton siguiente en el cual se estan pasando las cadenas que se camputen y pasarla en la
             siguiente actividad*/
        @Override
        public void onClick(View v){

                Intent intent = new Intent(CapturaActivity.this, EditarActivity.class);

                Bundle pasar = new Bundle();
                pasar.putString("Nombre", nom.getText().toString());
                pasar.putString("Fecha", fehcaamostrar.getText().toString());
                pasar.putString("Telefono", tel.getText().toString());
                pasar.putString("Email", emaill.getText().toString());
                pasar.putString("Descripcion", des.getText().toString());

            intent.putExtras(pasar);

                startActivity(intent);}
        });

        /**
        Bundle bundle = this.getIntent().getExtras();

        nom.setText(""   + bundle.getString("Nombre"));
        fehcaamostrar.setText("" +bundle.getString("Fecha"));
        tel.setText("" + bundle.getString("Telefono"));
        emaill.setText("" + bundle.getString("Email"));
        des.setText("" + bundle.getString("Descripcion"));
        */
        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH) + 1;
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mostrarfecha();
        SelectorFecha = new  DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                año = year;
                mes = monthOfYear;
                dia = dayOfMonth;
                mostrarfecha();
            }
        };

    }



    public void mostrarfecha(){

        fehcaamostrar.setText(año+"/"+ mes + "/" + dia);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return new DatePickerDialog(this, SelectorFecha, año, mes+1, dia);

        }

        return null;
    }

    public void mostrarCalendario(View control){
        showDialog(TIPO_DIALOGO);
    }
}
