package com.br.avaliacaocrud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.avaliacaocrud.Entidade.Produto;
import com.br.avaliacaocrud.Persistencia.ProdutoDAO;
import com.br.avaliacaocrud.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Junior on 20/12/2017.
 */

public class AdaptadorListProduto extends BaseAdapter{

    LayoutInflater layoutInflater;
    ArrayList<Produto> listaprodutos = new ArrayList<>();

    public AdaptadorListProduto(Context context, ArrayList<Produto> listadeprodutos ){
        listaprodutos = listadeprodutos;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaprodutos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaprodutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        Produto umproduto = listaprodutos.get(position);
        return umproduto.id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView == null){
           convertView = layoutInflater.inflate(R.layout.layout_adaptador_produto, null);
       }

        TextView descricao = (TextView)convertView.findViewById(R.id.tvListAdaptadorDescricao);
        TextView total = (TextView)convertView.findViewById(R.id.tvListAdaptadorTotal);

        Produto umProduto = listaprodutos.get(position);

        descricao.setText(umProduto.descricao);
        total.setText(umProduto.total);

        return convertView;
    }
}
