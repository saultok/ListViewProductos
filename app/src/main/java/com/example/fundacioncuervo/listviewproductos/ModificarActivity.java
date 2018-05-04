package com.example.fundacioncuervo.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ModificarActivity extends AppCompatActivity {
    private EditText edtxt;
    private Spinner spinner;
    int p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);


        String categoria[] = {"Electronica","Dulceria","Papeleria","Moda","Perfumeria",
                "Hogar", "Videojuegos", "Bebes", "Deportes", "Computo"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categoria);

        edtxt = (EditText) findViewById(R.id.edtxt);
        spinner = (Spinner) findViewById(R.id.Spinner);
        spinner.setAdapter(adapter);

        Intent i = getIntent();
        String x = i.getStringExtra("Producto");
        String b = i.getStringExtra("Categoria");
        p = i.getIntExtra("posicion",-1);

        edtxt.setText(x);
        int f;
        Arrays.sort(categoria);
        f = Arrays.binarySearch(categoria,b);
        spinner.setSelection(f);



    }
    public  void  ModificarDatos(View w){
        Intent i = new Intent();
        i.putExtra("Producto",edtxt.getText().toString());
        i.putExtra("Categoria",spinner.getSelectedItem().toString());
        i.putExtra("posicion",p);
    setResult(RESULT_OK,i);
    finish();
}
    }

