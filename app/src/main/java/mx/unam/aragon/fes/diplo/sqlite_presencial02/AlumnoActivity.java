package mx.unam.aragon.fes.diplo.sqlite_presencial02;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlumnoActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etNombre,etCarrera,etCurp;
    private Button btnUpdate, btnDelete, btnInsert;
    private long id;

    private  DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        etNombre=(EditText) findViewById(R.id.editTextNombre);
        etCarrera=(EditText) findViewById(R.id.editTextCarrera);
        etCurp= (EditText) findViewById(R.id.editTextCurp);

        btnUpdate=(Button) findViewById(R.id.buttonUp);
        btnDelete= (Button) findViewById(R.id.buttonDel);
        btnInsert=(Button) findViewById(R.id.buttonIn);

        dbManager=new DBManager(this);
        dbManager.open();

        Intent i=getIntent();
        setTitle(i.getStringExtra(Constantes.ACCION)+" Alumno");
        try{
            id=Long.parseLong(i.getStringExtra("id"));
        }catch (Exception e){
            e.printStackTrace();
        }
        if (i.getStringExtra(Constantes.ACCION).equals(Constantes.EDITAR)){
            Cursor c=dbManager.select(i.getStringExtra("id"));
            etNombre.setText(c.getString(c.getColumnIndex(Constantes.NOMBRE)));
            etCarrera.setText(c.getString(c.getColumnIndex(Constantes.CARRERA)));
            etCurp.setText(c.getString(c.getColumnIndex(Constantes.CURP)));
        }

        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        if(i.getStringExtra(Constantes.ACCION).equals(Constantes.INSERTAR)){
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            btnInsert.setEnabled(true);
        }else if (i.getStringExtra(Constantes.ACCION).equals(Constantes.EDITAR)){
            btnUpdate.setEnabled(true);
            btnDelete.setEnabled(true);
            btnInsert.setEnabled(false);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonIn:
                dbManager.insert(etNombre.getText().toString(),etCarrera.getText().toString(),etCurp.getText().toString());
                returnHome();
                break;
            case R.id.buttonUp:
                dbManager.update(id, etNombre.getText().toString(),etCarrera.getText().toString(),etCurp.getText().toString());
                returnHome();
                break;
            case R.id.buttonDel:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("Seguro de eliminar Alumno \n"+ "Nombre "+etNombre.getText().toString()
                        +" ?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dbManager.delete(id);
                        Toast.makeText(getApplicationContext(),"Registro eliminado exitosamente",Toast.LENGTH_SHORT).show();
                        returnHome();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });
                break;
        }
    }

    public void returnHome(){
        Intent i=new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
