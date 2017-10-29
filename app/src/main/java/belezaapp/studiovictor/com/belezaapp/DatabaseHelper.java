package belezaapp.studiovictor.com.belezaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rogério on 28/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_DATABASE = "BandoDeDadosTeste0";
    private static final String NOME_TABELA = "Tabela_Usuarios";
    // Generalizei todos como String para ficar mais fácil
    private static final String COL_1 = "usuario_nome";
    private static final String COL_2 = "usuario_cpf";
    private static final String COL_3 = "usuario_email";
    private static final String COL_4 = "usuario_senha";

    public DatabaseHelper(Context context) {
        super(context, NOME_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + NOME_TABELA + " ()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
        onCreate(db);
    }

    // Método criado para inserir os dados no DB
    // db.insert retorna uma long -1 em caso de falha
    public boolean inserirDados(String nome, String cpf, String email, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nome);
        contentValues.put(COL_2, cpf);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, senha);
        long result = db.insert(NOME_TABELA, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor pegarDados(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NOME_TABELA + " WHERE usuario_email = '" + email + "'", null);
        return cursor;
    }
}
