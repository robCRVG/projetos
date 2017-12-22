package com.br.avaliacaonew.Activity;

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

import com.br.avaliacaonew.Adapter.AdaptadorListProduto;
import com.br.avaliacaonew.Entidade.Login;
import com.br.avaliacaonew.Entidade.Produto;
import com.br.avaliacaonew.Persistencia.ProdutoDAO;
import com.br.avaliacaonew.R;

public class ProdutoActivity extends AppCompatActivity {

    Spinner spCarrinho, spQuantidade;
    TextView tvUsuarioLogado;
    EditText etQuantidade, etPreco, etTotal;
    Login login;
    ProdutoDAO produtoDAO;
    String getDescricao;
    AdaptadorListProduto adaptadorListProduto;
    TextView tvTotalCompra;
    String tmpTotalCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        etPreco = (EditText) findViewById(R.id.etPreco);
        etQuantidade = (EditText) findViewById(R.id.etQuantidade);
        etTotal = (EditText) findViewById(R.id.etValorTotal);
        spCarrinho = (Spinner) findViewById(R.id.spCarrinho);
//        spQuantidade = (Spinner) findViewById(R.id.spQuantidade);
        tvTotalCompra = (TextView) findViewById(R.id.tvTotalCompra);
//        tvTotalCompra = produtoDAO.queryTotalCompra();

//        tvUsuarioLogado = (TextView) findViewById(R.id.tvUsuarioLogado);
//        preco = Double.parseDouble(etPreco.getText().toString());

        ArrayAdapter<CharSequence> spAdapterProd = ArrayAdapter.createFromResource(this,
                R.array.array_produto, android.R.layout.simple_spinner_item);
        spAdapterProd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCarrinho.setAdapter(spAdapterProd);

//        ArrayAdapter<CharSequence> spAdapterQtde = ArrayAdapter.createFromResource(this,
//                R.array.arry_quantidade, android.R.layout.simple_spinner_item);
//        spAdapterQtde.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spQuantidade.setAdapter(spAdapterQtde);

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

                Double qtde = Double.parseDouble(etQuantidade.getText().toString());
                Double preco = Double.parseDouble(etPreco.getText().toString());
                Double total = (qtde * preco);
                etTotal.setText("" + total);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        tmpTotalCompra = produtoDAO.pegaValorTotal();
//        tvTotalCompra.setText(tmpTotalCompra);

        produtoDAO = new ProdutoDAO(this);

    }

    public void gravaProduto(View view) {


        Produto umProduto = new Produto();
        umProduto.descricao = getDescricao;
        umProduto.quantidade = etQuantidade.getText().toString();
        umProduto.total = etTotal.getText().toString().trim();
        umProduto.id = produtoDAO.inserir(umProduto);
        if (umProduto.id > 0) {

            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
//            etQuantidade.setText(null);
            ListView listView = (ListView) findViewById(R.id.listaCompras);
            adaptadorListProduto = new AdaptadorListProduto(this);
            listView.setAdapter(adaptadorListProduto);

//            Double tmpTotal = Double.parseDouble(etTotal.getText().toString());
//            tmpTotal += tmpTotal;
//            tmpTotalCompra = String.valueOf(tmpTotal);
//            tvTotalCompra.setText(tmpTotalCompra);
//
        } else {

            Toast.makeText(this, "Operação não realizada", Toast.LENGTH_SHORT).show();

        }

    }

//    public void pegaTotalCompra(Double totalCompra, int q)

}
