package mx.unam.aragon.fes.diplo.sqlite_presencial02;

public class Constantes {
    public static final String BD="COLEGIO";
    public static final String TABLA_US= "usuario";
    public static final String TABLA_ALU= "alumno";

    //columnas de alumno
    public static final String ID="_id";
    public static final String NOMBRE="nombre";
    public static final String CARRERA="carrera";
    public static final String CURP="curp";

    public static final String CREATE_TABLE_ALU="CREATE TABLE "+
            TABLA_ALU+ "(" + ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NOMBRE + " TEXT NOT NULL, "+
            CARRERA + " VARCHAR, "+
            CURP + " VARCHAR"+");";

    public static final int BD_VERSION =1;

    public  static  final String ACCION= "accion";
    public  static  final String EDITAR= "editar";
    public  static  final String INSERTAR= "insertar";
    public  static  final String ELIMINAR= "eliminar";

}
