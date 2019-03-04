package lundy.com.quiz4_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {


    //Deklarasi Variabel dalam Class
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="dbTransaksi";
    public static final String TABLE_CREATE="CREATE TABLE TRANSAKSI (KODEBAYAR TEXT, JUMLAH TEXT)";


    //Constructor, sebagai bantuan deklarasi
    public OpenHelper(@Nullable Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        //Menggunakan heritance dari induk
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //akan mengexecute secara langgsung buat tabel saat tidak ada
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //tujuan untuk mengupgrade
    db.execSQL("DROP TABLE IF EXISTS TRANSAKSI");
    }
}
