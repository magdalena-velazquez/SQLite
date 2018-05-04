package mx.unam.aragon.fes.diplo.sqlite_presencial02;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BDActivity extends AppCompatActivity {

    SQLiteDatabase db;

    EditText us,pass;
    Button button;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);

         db = openOrCreateDatabase(Constantes.BD, MODE_PRIVATE, null);

         db.execSQL("CREATE TABLE IF NOT EXISTS "+Constantes.TABLA_US +" " +
                 "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+" usuario VARCHAR NOT NULL, password VARCHAR NOT NULL);");

         us=(EditText) findViewById(R.id.editTextUS);
         pass= (EditText) findViewById(R.id.editTextPss);
         tv=(TextView)findViewById(R.id.textView);
         button=(Button)findViewById(R.id.buttonIn);

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 insertar();
                 listar();
             }
         });

        listar();
    }

    private void insertar(){
        String sql="INSERT IN TO " + Constantes.TABLA_US + "VALUES " + "(NULL, '"+ us.getText().toString()+"'"
        + ", '" + pass.getText().toString()+"');";
        Log.i(Constantes.TABLA_US, sql);
        db.execSQL(sql);
        us.setText("");
        pass.setText("");
    }
    private void listar(){
        String sql= "SELECT * FROM "+Constantes.TABLA_US;
        Cursor rs =db.rawQuery(sql, null);
        tv.setText("");
        if (rs.moveToFirst()){
            do{
                tv.append("ID "+ rs.getInt(0)+" us: " + rs.getString(1)+
                " pass: " + rs.getString(2)+ "\n");
            }while (rs.moveToNext());
        }
    }
}
