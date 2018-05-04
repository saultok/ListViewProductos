package com.example.fundacioncuervo.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private ListView lista;

    /*private String productos[] = {"Computadora", "Mouse", "Dulces", "Hojas",
            "Lapices", "Lentes","Reloj", "Cuchara", "Celular", "Mesa", "Refrigerador",
            "Horno", "Audifonos"};

    private String categoria[] = {"Electronica","Electronica","Dulceria","Papeleria","Papeleria","Moda","Perfumeria",
            "Hogar", "Electronicos", "Hogar", "Electrodomesticos", "Electrodomesticos", "Electronica"};*/

    private List<String>lproductos = new ArrayList<>();
    private List<String>lcategoria = new ArrayList<>();

    private void actualizarTabla(){
        String productos[] = new String [lproductos.size()];
        lproductos.toArray(productos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, productos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long
                    id) {
                txt.setText("Categoria elegido: " + lcategoria.get(position));
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            txt = (TextView) findViewById(R.id.textView);
            lista = (ListView) findViewById(R.id.lista);
              actualizarTabla();

        registerForContextMenu(lista);

    }



    public void llamaVentana(View w){
        Log.e("Verifica Click","Click");
        Intent i = new Intent(this,AgregarActivity.class);
        startActivityForResult(i,1234);
    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* String cad = data.getStringExtra("Producto");
        Toast.makeText(this,cad,Toast.LENGTH_LONG).show();
        String c = data.getStringExtra("Categoria");
        Toast.makeText(this,c,Toast.LENGTH_LONG).show();*/
       if(requestCode == 1234) {
           lproductos.add(data.getStringExtra("Producto"));
           lcategoria.add(data.getStringExtra("Categoria"));
           actualizarTabla();
       }
        if (requestCode ==123) {
           int posicion = data.getIntExtra("posicion",-1);
            lproductos.set(posicion,data.getStringExtra("Producto"));
            lcategoria.set(posicion,data.getStringExtra("Categoria"));
            actualizarTabla();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.op_salir:
               finish();
                break;
                case  R.id.op_info:
                    Toast.makeText(this,"Informacion",Toast.LENGTH_SHORT).show();
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
///////////Menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(lista.getAdapter().getItem(info.position).toString());
        getMenuInflater().inflate(R.menu.menu_emergente,menu);
    }

    ////// Modificar y Eliminar

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){

            case  R.id.emergente_1:
                String nombre = lproductos. get(info.position);

                Intent i = new Intent(this,ModificarActivity.class);
                i.putExtra("Producto",lproductos.get(info.position).toString());
                i.putExtra("Categoria",lcategoria.get(info.position).toString());
                i.putExtra("posicion",info.position);
                startActivityForResult(i,123);


                break;

            case  R.id.emergente_2:
                String nombre2 = lproductos. get(info.position);
                lproductos.remove(info.position);
                lcategoria.remove(info.position);
                actualizarTabla();
                break;

        }

        return true;
    }
}

