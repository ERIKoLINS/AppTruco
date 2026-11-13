package br.edu.utfpr.apptruco

import android.annotation.SuppressLint
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var jogador1: TextView
    private lateinit var jogador2: TextView
    private lateinit var txtPontosJ1: TextView
    private lateinit var txtPontosJ2: TextView
    private lateinit var historico: Button
    private lateinit var inf_nomes: Button
    private var pontosJ1 = 0
    private var pontosJ2 = 0
    private var vitoriasJ1 = 0
    private var vitoriasJ2 = 0
    private var nomeJ1 = "Jogador 1"
    private var nomeJ2 = "Jogador 2"


    @SuppressLint("SetTextI18n")
    private val laucherNomes = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { resultado ->
        if (resultado.resultCode == RESULT_OK) {
            val novoNome1 = resultado.data?.getStringExtra("nomeJogador1")
            val novoNome2 = resultado.data?.getStringExtra("nomeJogador2")

            if (!novoNome1.isNullOrBlank()){
                nomeJ1 = novoNome1
                jogador1.text = "$nomeJ1:"
            }
            if (!novoNome2.isNullOrBlank()){
                nomeJ2 = novoNome2
                jogador2.text = "$nomeJ2:"
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        historico = findViewById(R.id.historico)
        inf_nomes = findViewById(R.id.inf_nomes)
        jogador1 = findViewById(R.id.Jogador1)
        jogador2 = findViewById(R.id.jogador2)
        txtPontosJ1 = findViewById(R.id.txtPontosJ1)
        txtPontosJ2 = findViewById(R.id.txtPontosJ2)



        findViewById<Button>(R.id.ponto1_jog1).setOnClickListener {
            adicionarPontos(1, 1)
        }
        findViewById<Button>(R.id.ponto3_jog1).setOnClickListener {
            adicionarPontos(1, 3)
        }
        findViewById<Button>(R.id.ponto6_jog1).setOnClickListener {
            adicionarPontos(1, 6)
        }
        findViewById<Button>(R.id.ponto9_jog1).setOnClickListener {
            adicionarPontos(1, 9)
        }
        findViewById<Button>(R.id.ponto12_jog1).setOnClickListener {
            adicionarPontos(1, 12)
        }

        findViewById<Button>(R.id.ponto1_jog2).setOnClickListener {
            adicionarPontos(2, 1)
        }
        findViewById<Button>(R.id.ponto3_jog2).setOnClickListener {
            adicionarPontos(2, 3)
        }
        findViewById<Button>(R.id.ponto6_jog2).setOnClickListener {
            adicionarPontos(2, 6)
        }
        findViewById<Button>(R.id.ponto9_jog2).setOnClickListener {
            adicionarPontos(2, 9)
        }
        findViewById<Button>(R.id.ponto12_jog2).setOnClickListener {
            adicionarPontos(2, 12)
        }

        findViewById<Button>(R.id.zerar_historico).setOnClickListener {
            pontosJ1 = 0
            pontosJ2 = 0
            vitoriasJ1 = 0
            vitoriasJ2 = 0
            nomeJ1 = "Jogador 1"
            nomeJ2 = "Jogador 2"
            atualizarTela()
            Toast.makeText(this, "Histórico zerado!", Toast.LENGTH_SHORT).show()
        }


        historico.setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
                intent.putExtra("nomeJ1", nomeJ1)
                intent.putExtra("nomeJ2", nomeJ2)
                intent.putExtra("vitoriasJ1", vitoriasJ1)
                intent.putExtra("vitoriasJ2", vitoriasJ2)
            startActivity(intent)
            }

        inf_nomes.setOnClickListener {
            val intent = Intent(this, InformarNomes::class.java)
            laucherNomes.launch(intent)

        }
        atualizarTela()
    }

    @SuppressLint("SetTextI18n")
    private fun atualizarTela() {
        txtPontosJ1.text = pontosJ1.toString()
        txtPontosJ2.text = pontosJ2.toString()
        jogador1.text = "$nomeJ1:"
        jogador2.text = "$nomeJ2:"
    }

    private fun adicionarPontos(jogador: Int, valor: Int) {
        if (jogador == 1) {
            pontosJ1 += valor
        } else {
            pontosJ2 += valor
        }
        atualizarTela()

        if (pontosJ1 >= 12) {
            venceuPartida(1)
        } else if (pontosJ2 >= 12) {
            venceuPartida(2)
        }
    }

    private fun venceuPartida(jogador: Int) {
        if (jogador == 1) {
            vitoriasJ1++
            Toast.makeText(this, "Jogador 1 venceu!", Toast.LENGTH_SHORT).show()
        }else{
            vitoriasJ2++
            Toast.makeText(this, "Jogador 2 venceu!", Toast.LENGTH_SHORT).show()
        }
        pontosJ1 = 0
        pontosJ2 = 0
        atualizarTela()
    }
}

