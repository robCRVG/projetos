package com.br.avaliacaocrud.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.avaliacaocrud.Entidade.Login;
import com.br.avaliacaocrud.Entidade.Produto;

/**
 * Created by Junior on 18/12/2017.
 */

public class DBSQLite extends SQLiteOpenHelper {

    private static final String NOME_BASE = "avaliacao4";
    private static final int VERSAO = 1;

    public DBSQLite(Context context) {

        super(context, NOME_BASE, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CRIA_TABELA_LOGIN = "CREATE TABLE " + Login.TABLEA+ " ( "
                + Login.CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Login.CAMPO_USUARIO + " TEXT, "
                + Login.CAMPO_SENHA + " TEXT, "
                + Login.CAMPO_EMAIL + " TEXT )";
        sqLiteDatabase.execSQL(CRIA_TABELA_LOGIN);

        String CRIA_TABELA_PRODUTO = "CREATE TABLE " + Produto.TABELA + " ( "
                + Produto.CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Produto.DESCRICAO + " TEXT, "
                + Produto.CAMPO_QUANTIDADE + " TEXT, "
                + Produto.CAMPO_TOTAL + " TEXT )";
        sqLiteDatabase.execSQL(CRIA_TABELA_PRODUTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
