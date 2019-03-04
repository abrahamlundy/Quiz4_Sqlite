package lundy.com.quiz4_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DbTransaksi db;
    Button insert,view;
    TextView  hasil;

    //array dengan tipe objek dbTransaksi
    ArrayList<DbTransaksi.Transaksi> alTrx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert= findViewById(R.id.btn_insert);
        view= findViewById(R.id.btn_view);
        hasil= findViewById(R.id.tv_tampil);
        getSupportActionBar().setTitle("MyDatabase"); //untuk memberi nama pada activity
    }

    public void bClickIns(View v) {
        //insert data,
        db = new DbTransaksi(getApplicationContext());
        db.open();
        db.inserttransaksi("A1", "20000");
        Toast.makeText(getApplicationContext(),"Berhasil Di Input",Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void bClickVi(View v) {
        //insert data,
        db = new DbTransaksi(getApplicationContext());
        db.open();

        //array deklarasi awal
        alTrx= db.getAllTransaksi();

        //pencacahan
        for(DbTransaksi.Transaksi all:alTrx){
           Toast.makeText(getApplicationContext(),String.format("KODE BAYAR : %s \n JUMLAH BAYAR : %s ",all.KODEBAYAR,all.JUMLAH),Toast.LENGTH_SHORT).show();
            hasil.setText(String.format("KODE BAYAR : %s \nJUMLAH BAYAR : %s ",all.KODEBAYAR,all.JUMLAH));
        }
        db.close();

    }

}
