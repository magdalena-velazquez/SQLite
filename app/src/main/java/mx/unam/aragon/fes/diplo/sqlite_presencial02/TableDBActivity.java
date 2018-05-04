package mx.unam.aragon.fes.diplo.sqlite_presencial02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.model.TableColumnDpWidthModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class TableDBActivity extends AppCompatActivity {

    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_db);

        dbManager= new DBManager(this);
        dbManager.open();
        String[][] alumnos=dbManager.getAlumnos();
        String[] colHeaders={"ID", "NOMBRE", "CARRERA", "CURP"};

        TableView<String[]> tableView=(TableView<String[]>) findViewById(R.id.tableView);

        tableView.setColumnCount(4);

        TableColumnDpWidthModel colModel=new TableColumnDpWidthModel(this,4,200);
        colModel.setColumnWidth(0,50);
        colModel.setColumnWidth(2,100);

        tableView.setColumnModel(colModel);
        tableView.setHeaderBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, colHeaders));
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, alumnos));
        tableView.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Toast.makeText(getApplicationContext(),((String[])clickedData)[1],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
