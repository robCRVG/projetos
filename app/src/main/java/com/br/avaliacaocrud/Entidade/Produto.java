package com.br.avaliacaocrud.Entidade;

/**
 * Created by Junior on 19/12/2017.
 */

public class Produto {

    public static final String TABELA= "produto";
    public static final String CAMPO_ID = "id";
    public static final String DESCRICAO = "descricao";
    public static final String CAMPO_QUANTIDADE = "quantidade";
    public static final String CAMPO_TOTAL = "total";
    public static final String CAMPO_TOTAL_COMPRA = "totalCompra";

    public Long id;
    public String descricao;
    public String total;
    public String totalCompra;
    public String quantidade;

}