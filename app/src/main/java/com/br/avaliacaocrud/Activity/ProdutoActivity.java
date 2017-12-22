package com.br.avaliacaocrud.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.br.avaliacaocrud.Adapter.AdaptadorListProduto;
import com.br.avaliacaocrud.Entidade.Login;
import com.br.avaliacaocrud.Entidade.Produto;
import com.br.avaliacaocrud.Persistencia.ProdutoDAO;
import com.br.avaliacaocrud.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProdutoActivity extends AppCompatActivity {

    Spinner spCarrinho, spQuantidade;
    TextView tvUsuarioLogado;
    EditText etQuantidade, etPreco, etTotal;
    Login login;
    ProdutoDAO produtoDAO;
    String getDescricao;
    AdaptadorListProduto adaptadorListProduto;
    TextView tvTotalCompraView;
    Double tmpTotalCompra = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        etPreco = (EditText) findViewById(R.id.etPreco);
        etQuantidade = (EditText) findViewById(R.id.etQuantidade);
        etTotal = (EditText) findViewById(R.id.etValorTotal);
        spCarrinho = (Spinner) findViewById(R.id.spCarrinho);
        tvTotalCompraView = (TextView) findViewById(R.id.tvTotalCompra);

        ArrayAdapter<CharSequence> spAdapterProd = ArrayAdapter.createFromResource(this,
                R.array.array_produto, android.R.layout.simple_spinner_item);
        spAdapterProd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCarrinho.setAdapter(spAdapterProd);

        spCarrinho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDescricao = String.valueOf(parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etQuantidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String qtde = etQuantidade.getText().toString();
                if (qtde == null || qtde.isEmpty()) {
                    qtde = "0";
                    etTotal.setText(qtde);
                } else {
                    Double preco = Double.parseDouble(etPreco.getText().toString());
                    Double total = (preco * Double.parseDouble(qtde));
                    etTotal.setText("" + total);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        produtoDAO = new ProdutoDAO(this);

    }

    public void gravaProduto(View view) {


        Produto umProduto = new Produto();
        umProduto.descricao = getDescricao;
        umProduto.quantidade = etQuantidade.getText().toString();
        umProduto.total = etTotal.getText().toString().trim();
        umProduto.id = produtoDAO.inserir(umProduto);
        if (umProduto.id > 0) {

            ProdutoDAO produtoDAO = new ProdutoDAO(this);
            ArrayList<Produto> listaProdutos = produtoDAO.listar();
            for (Produto p : listaProdutos) {
                tmpTotalCompra = tmpTotalCompra + Double.parseDouble(p.total);
            }
            tmpTotalCompra = tmpTotalCompra + Double.parseDouble(umProduto.total);
            tvTotalCompraView.setText(tmpTotalCompra.toString());

            Toast.makeText(this, "Adicionado ao carrinho!", Toast.LENGTH_SHORT).show();

            ListView listView = (ListView) findViewById(R.id.listaCompras);
            adaptadorListProduto = new AdaptadorListProduto(this, listaProdutos);
            listView.setAdapter(adaptadorListProduto);

        } else {

            Toast.makeText(this, "Operação não realizada", Toast.LENGTH_SHORT).show();

        }


    }

}
