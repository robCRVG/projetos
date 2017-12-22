package com.br.avaliacaonew.Entidade;

/**
 * Created by Junior on 21/12/2017.
 */


public class Produto {

    public static final String TABELA= "produto";
    public static final String CAMPO_ID = "id";
    public static final String DESCRICAO = "descricao";
    public static final String CAMPO_QUANTIDADE = "quantidade";
    public static final String CAMPO_TOTAL = "total";
    public static final String CAMPO_TOTAL_COMPRA = "totalCompra";

    //    public static final String CAMPO_PRECO = "preco";
    public Long id;
    public String descricao;
    public String total;
    public String totalCompra;
    public String quantidade;

}