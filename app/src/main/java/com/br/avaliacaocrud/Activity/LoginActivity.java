package com.br.avaliacaocrud.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.br.avaliacaocrud.Entidade.Login;
import com.br.avaliacaocrud.Persistencia.LoginDAO;
import com.br.avaliacaocrud.R;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuario, etSenha;
    Login umLogin;
    LoginDAO loginDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etSenha = (EditText) findViewById(R.id.etSenha);

        loginDAO = new LoginDAO(this);
    }

    public void loginOnClick(View view){

        String usuarioView = etUsuario.getText().toString();
        String senhaView = etSenha.getText().toString();

        String validacao = loginDAO.valida(usuarioView, senhaView);

        if(validacao.equals("valido")){
            startActivity(new Intent(this,ProdutoActivity.class));
            etUsuario.setText("");
            etSenha.setText("");
        }else{
            String mensagemErro = "Usuário ou senha não encontrados";
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void confirmaRegistro(View view){
        startActivity(new Intent(this, ConfirmaRegistroActivity.class));
    }

    public void formRegistrarOnClick(View view){
        startActivity(new Intent(this, RegistraLoginActivity.class));
    }
}
