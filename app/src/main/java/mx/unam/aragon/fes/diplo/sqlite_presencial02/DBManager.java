package mx.unam.aragon.fes.diplo.sqlite_presencial02;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DataBaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager (Context c){
        context=c;
    }

    public  DBManager open() throws SQLException{
        dbHelper=new DataBaseHelper(context);
        database= dbHelper.getWritableDatabase();
        return this;
    }

    public  void close(){
        dbHelper.close();
    }

    public void  insert(String nombre, String carrera, String curp){
        ContentValues cv = new ContentValues();
        cv.put(Constantes.NOMBRE, nombre);
        cv.put(Constantes.CARRERA, carrera);
        cv.put(Constantes.CURP, curp);
        database.insert(Constantes.TABLA_ALU,null,cv);
    }

    public int update(long id, String nombre, String carrera, String curp){
        ContentValues cv = new ContentValues();
        cv.put(Constantes.NOMBRE, nombre);
        cv.put(Constantes.CARRERA, carrera);
        cv.put(Constantes.CURP, curp);
        int i= database.update(Constantes.TABLA_ALU, cv, Constantes.ID+ "=" +id, null);
        return i;
    }

    public void delete(long id){
        database.delete(Constantes.TABLA_ALU, Constantes.ID + " = "+ id, null);
    }

    public Cursor select(){
        String[] colums= new String[]{
                Constantes.ID, Constantes.NOMBRE,Constantes.CARRERA, Constantes.CURP
        };
        Cursor cursor=database.query(Constantes.TABLA_ALU,colums,null,null,null,null,null);
        if (cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor select(String id){
        String[] colums= new String[]{
                Constantes.ID, Constantes.NOMBRE,Constantes.CARRERA, Constantes.CURP
        };
        String whereClause= Constantes.ID + " = ?";
        String[] whereArgs=new String[]{id};

        Cursor cursor=database.query(Constantes.TABLA_ALU,colums,null,null,null,null,null);
        if (cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public String[][] getAlumnos(){
        Cursor rs=this.select();
        String[][] alumnos= new String[rs.getCount()][rs.getColumnCount()];
        int i=0;
        if (rs.moveToFirst()){
            do{
                alumnos[i][0]=rs.getString(0);
                alumnos[i][1]=rs.getString(1);
                alumnos[i][2]=rs.getString(2);
                alumnos[i][3]=rs.getString(3);
                i++;
            }while (rs.moveToNext());
        }
        return alumnos;
    }
}
