package com.br.avaliacaocrud.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.br.avaliacaocrud.Entidade.Produto;

import java.util.ArrayList;

/**
 * Created by Junior on 20/12/2017.
 */

public class ProdutoDAO {

    Context context;
    DBSQLite dbsqLite;

    public ProdutoDAO(Context context) {

        this.context = context;
        dbsqLite = new DBSQLite(context);

    }

    public Long inserir(Produto produto) {

        SQLiteDatabase db = dbsqLite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Produto.DESCRICAO, produto.descricao);
        values.put(Produto.CAMPO_QUANTIDADE, produto.quantidade);
        values.put(Produto.CAMPO_TOTAL, produto.total);
        Long id = db.insert(Produto.TABELA, null, values);
        db.close();

        return id;

    }

    public ArrayList<Produto> listar() {
        SQLiteDatabase db = dbsqLite.getReadableDatabase();

        ArrayList<Produto> listaProdutos = new ArrayList<>();

        String selectQuery = "SELECT  " +
                Produto.CAMPO_ID + "," +
                Produto.DESCRICAO + "," +
                Produto.CAMPO_QUANTIDADE + "," +
                Produto.CAMPO_TOTAL +
                " FROM " + Produto.TABELA;

        Cursor cursor = db.rawQuery(selectQuery, null);
        Produto umProduto;

        if (cursor.moveToFirst()) {
            do {
                umProduto = new Produto();
                umProduto.id = cursor.getLong(0);
                umProduto.descricao = cursor.getString(1);
                umProduto.quantidade = cursor.getString(2);
                umProduto.total = cursor.getString(3);
                listaProdutos.add(umProduto);
            } while (cursor.moveToNext());
            db.close();
        }
        return listaProdutos;
    }
}
