package com.br.avaliacaonew.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.br.avaliacaonew.Entidade.Login;
import com.br.avaliacaonew.Persistencia.LoginDAO;
import com.br.avaliacaonew.R;

public class RegistraLoginActivity extends AppCompatActivity {

    EditText etRegUsuario, etRegSenha, etRegEmail;
    LoginDAO loginDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_login);

        etRegUsuario = (EditText)findViewById(R.id.etRegistraUsuario);
        etRegSenha = (EditText)findViewById(R.id.etRegistraSenha);
        etRegEmail = (EditText)findViewById(R.id.etRegistraEmail);

        loginDAO = new LoginDAO(this);

    }


    public void registrarOnClick(View view){
        Login umLogin = new Login();
        umLogin.usuario = etRegUsuario.getText().toString();
        umLogin.senha = etRegSenha.getText().toString();
        umLogin.email = etRegEmail.getText().toString();
        umLogin.id = loginDAO.registra(umLogin);
        if(umLogin.id > 0){
            Toast.makeText(this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            etRegUsuario.setText("");
            etRegSenha.setText("");
            etRegEmail.setText("");
            finish();
        }else {
            Toast.makeText(this, "Operação não realizada", Toast.LENGTH_SHORT).show();
        }
    }
}