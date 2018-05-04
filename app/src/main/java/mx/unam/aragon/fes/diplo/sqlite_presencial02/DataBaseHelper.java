package mx.unam.aragon.fes.diplo.sqlite_presencial02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, Constantes.BD, null, Constantes.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(Constantes.BD, Constantes.CREATE_TABLE_ALU);
        db.execSQL(Constantes.CREATE_TABLE_ALU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_ALU);
        onCreate(db);
    }
}
