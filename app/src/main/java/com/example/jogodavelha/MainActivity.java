package com.example.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] botoes = new Button[3][3];

    private boolean turnoJogador1 = true;

    private int contadorRound;

    private int pontosJogador1;
    private int pontosJogador2;

    private TextView textViewJogador1;
    private TextView textViewJogador2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewJogador1 = findViewById(R.id.text_view_p1);
        textViewJogador2 = findViewById(R.id.text_view_p2);
        //Usar ids de 1-2 dos botões da activity_main.xml
        for(int i=0;i<3;i++){
            for(int k=0;k<3;k++){
                //Definir todos os ids
                String buttonId = "button_" + i + k;
                int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                botoes[i][k] = findViewById(resID);
                botoes[i][k].setOnClickListener(this);
            }
        }

        Button botaoResetar = findViewById(R.id.botao_resetar);
        botaoResetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetarCampo();
            }
        });

        Button botaoReiniciarPartida = findViewById(R.id.botao_resetar_partida);
        botaoReiniciarPartida.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetarPartida();
            }
        });



    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")){
            return;
        }

        //Jogador 1 = X
        if(turnoJogador1){
            ((Button)v).setText("X");
        //Jogador 2 = 0
        }else {
            ((Button)v).setText("O");
        }

        contadorRound++;

        if(checkForWin()){
            if(turnoJogador1){
                jogador1Vence();
            }else{
                jogador2Vence();
            }
        }else if(contadorRound ==9){
            empate();
        }else{
            turnoJogador1 = !turnoJogador1;
        }


    }
    //função booleana que retorna verdadeiro se ganhou o jogo
    //ou falso caso for uma perda
    private boolean checkForWin(){
        String[][] campo = new String[3][3];

        for(int i=0;i<3;i++){
            for(int k=0;k<3;k++){
                campo[i][k] = botoes[i][k].getText().toString();
            }
        }


        //Checando a vitória na vertical por meio de checagem de valores de string
        for(int i=0;i<3;i++){
            if(campo[i][0].equals(campo[i][1])
                && campo[i][0].equals(campo[i][2])
                && !campo[i][0].equals("")){
                return true;
            }

        }

        //Checando a vitória na horizontal por meio de checagem de valores de string
        for(int i=0;i<3;i++){
            if(campo[0][i].equals(campo[1][i])
                    && campo[0][i].equals(campo[2][i])
                    && !campo[0][i].equals("")){
                return true;
            }
        }

        //Checando a vitória na hexagonal por meio de checagem de valores de string
        if(campo[0][0].equals(campo[1][1])
                && campo[0][0].equals(campo[2][2])
                && !campo[0][0].equals("")){
                    return true;
        }

        if(campo[0][2].equals(campo[1][1])
                && campo[0][2].equals(campo[2][0])
                && !campo[0][2].equals("")){
            return true;
        }

        //Caso nenhuma vitória foi alcançada ainda, retorne falso
        return false;
    }

    private void jogador1Vence(){
        pontosJogador1++;
        Toast.makeText(this,"Jogador 1 ganhou!", Toast.LENGTH_SHORT).show();
        atualizarPontos();
        resetarCampo();
    }

    private void jogador2Vence(){
        pontosJogador2++;
        Toast.makeText(this,"Jogador 2 ganhou!", Toast.LENGTH_SHORT).show();
        atualizarPontos();
        resetarCampo();
    }



    private void empate(){
        Toast.makeText(this,"Empatou :<", Toast.LENGTH_SHORT).show();
        resetarCampo();
    }

    private void atualizarPontos() {
        textViewJogador1.setText("Jogador 1: " + pontosJogador1);
        textViewJogador2.setText("Jogador 2: " + pontosJogador2);

    }
    private void resetarCampo() {
        for(int i=0;i<3;i++){
            for(int k=0;k<3;k++){
                botoes[i][k].setText("");
            }
        }
        contadorRound = 0;
        turnoJogador1 = true;
    }
    private void resetarPartida(){
        pontosJogador1=0;
        pontosJogador2=0;
        atualizarPontos();
        contadorRound=0;
        turnoJogador1=true;
        for(int i=0;i<3;i++){
            for(int k=0;k<3;k++){
                botoes[i][k].setText("");
            }
        }

    }
}