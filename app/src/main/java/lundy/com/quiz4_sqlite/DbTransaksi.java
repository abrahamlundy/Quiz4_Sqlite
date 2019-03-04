package lundy.com.quiz4_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DbTransaksi {

    //Kasus ini ada class di dalam class init untuk get-set
    public static class Transaksi{
        public String KODEBAYAR;
        public String JUMLAH;
    }

    //Private private
    private SQLiteDatabase db;
    private  final OpenHelper dbHelper;

    //public custom variabel bernama DbMahasiswa dengan mengklon Context utk dbHelper
    //Ini sebagai Constructor
    public DbTransaksi(Context c){
        //dengan ini maka database akan dikelola
        dbHelper = new OpenHelper(c);
    }

    //method open
    public void open(){
        //database db adalah database yang diolah oleh dbHelper saat ini
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    // method custom variabel
    public long inserttransaksi (String KODEBAYAR, String JUMLAH){
        //Instansiasi content value
        ContentValues newValues = new ContentValues();
        newValues.put("KODEBAYAR", KODEBAYAR);
        newValues.put("JUMLAH", JUMLAH);
        return  db.insert("TRANSAKSI", null, newValues);
    }

    //variabel methd dengan mengisi nilai sendiri
    public Transaksi getTransaksi (String KODEBAYAR){
        Cursor cur = null;
        Transaksi M = new Transaksi();

        //Kolom yang diambil
        String[] cols = new String[]{"JUMLAH"};
        String [] param = {KODEBAYAR};

        cur = db.query("TRANSAKSI", cols,"KODE=?",param,null,null,null);

        if (cur.getCount()>0){
            cur.moveToFirst();
            M.KODEBAYAR = cur.getString(0);
            M.JUMLAH = cur.getString(1);
        }

        cur.close();
        return M;
    }

    //ambil semua data mahasiswa (dibatasi 10)
    //menggunakan raw query
    public ArrayList<Transaksi> getAllTransaksi() {
        Cursor cur = null;
        ArrayList<Transaksi> out = new ArrayList<>();
        cur = db.rawQuery("SELECT  *FROM TRANSAKSI Limit 10", null);
        if (cur.moveToFirst()) {
            do {
                Transaksi t = new Transaksi();
                t.KODEBAYAR = cur.getString(0);
                t.JUMLAH = cur.getString(1);
                out.add(t);
            } while (cur.moveToNext());
        }
        cur.close();
        return out;
    }

}
