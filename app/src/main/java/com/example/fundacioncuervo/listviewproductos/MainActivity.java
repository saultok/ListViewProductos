package com.example.fundacioncuervo.listviewproductos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private ListView lista;

    private String productos[] = {"Computadora", "Mouse", "Dulces", "Hojas",
            "Lapices", "Lentes","Reloj", "Cuchara", "Celular", "Mesa", "Refrigerador",
            "Horno", "Audifonos"};

    private String categoria[] = {"Electronica","Electronica","Dulceria","Papeleria","Papeleria","Moda","Perfumeria",
            "Hogar", "Electronicos", "Hogar", "Electrodomesticos", "Electrodomesticos", "Electronica"};
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            txt = (TextView) findViewById(R.id.textView);
            lista = (ListView) findViewById(R.id.lista);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,productos);
            lista.setAdapter(adapter);


            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long
                        id) {
                    txt.setText("Categoria elegido: " + categoria[position]);
                }
            });
    }}

