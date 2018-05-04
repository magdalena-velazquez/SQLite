package mx.unam.aragon.fes.diplo.sqlite_presencial02;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DBManager dbManager;
    ListView lista;
    SimpleCursorAdapter adapter;
    final String[]from=new String[]{Constantes.ID, Constantes.NOMBRE, Constantes.CARRERA, Constantes.CURP};

    final int[]to=new int[] {R.id.nombre,R.id.carrera,R.id.curp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbManager= new DBManager(this);
        dbManager.open();
        Cursor cursor=dbManager.select();

        lista= (ListView) findViewById(R.id.listView);
        adapter= new SimpleCursorAdapter(this, R.layout.registro_layout,cursor,
                from, to,0);
        adapter.notifyDataSetChanged();

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView idTV=(TextView) view.findViewById(R.id.id);
                Intent i=new Intent(getApplication(),AlumnoActivity.class);
                i.putExtra("id", idTV.getText().toString());
                i.putExtra(Constantes.ACCION, Constantes.EDITAR);
                startActivity(i);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i=new Intent(getApplicationContext(),AlumnoActivity.class);
                i.putExtra(Constantes.ACCION,Constantes.INSERTAR);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_db) {
            Intent i = new Intent(this, BDActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_dbtable) {
            Intent i = new Intent(this, TableDBActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
