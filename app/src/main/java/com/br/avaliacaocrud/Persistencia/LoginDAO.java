package com.br.avaliacaocrud.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.avaliacaocrud.Entidade.Login;

import java.util.ArrayList;

/**
 * Created by Junior on 18/12/2017.
 */

public class LoginDAO {

    Context context;
    DBSQLite dbsqLite;

    public LoginDAO(Context context) {
        this.context = context;
        dbsqLite = new DBSQLite(context);
    }

    public Long registra(Login login) {
        SQLiteDatabase sqLiteDatabase = dbsqLite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Login.CAMPO_USUARIO, login.usuario);
        values.put(Login.CAMPO_SENHA, login.senha);
        values.put(Login.CAMPO_EMAIL, login.email);
        Long id = sqLiteDatabase.insert(Login.TABLEA, null, values);
        sqLiteDatabase.close();
        return id;
    }

    public String valida(String usuario, String senha) {
        SQLiteDatabase sqLiteDatabase = dbsqLite.getReadableDatabase();
        String sql = "SELECT * FROM " + Login.TABLEA + " WHERE " + Login.CAMPO_USUARIO + " =? AND " + Login.CAMPO_SENHA + " =? ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{usuario, senha});
        if (cursor.getCount() > 0) {
            return "valido";
        }
        return "invalido";
    }

    public ArrayList<Login> listar() {
        SQLiteDatabase db = dbsqLite.getReadableDatabase();

        ArrayList<Login> listaLogin = new ArrayList<>();

        String selectQuery = "SELECT  " +
                Login.CAMPO_ID + "," +
                Login.CAMPO_USUARIO+ "," +
                Login.CAMPO_SENHA+ "," +
                Login.CAMPO_EMAIL +
                " FROM " + Login.TABLEA;

        Cursor cursor = db.rawQuery(selectQuery, null);
        Login umLogin;

        if (cursor.moveToFirst()) {
            do {
                umLogin = new Login();
                umLogin.id = cursor.getLong(0);
                umLogin.usuario = cursor.getString(1);
                umLogin.senha= cursor.getString(2);
                umLogin.email = cursor.getString(3);
                listaLogin.add(umLogin);
            } while (cursor.moveToNext());
            db.close();
        }
        return  listaLogin;
    }

}
