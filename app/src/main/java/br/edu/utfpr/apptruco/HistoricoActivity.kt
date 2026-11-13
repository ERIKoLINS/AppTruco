package br.edu.utfpr.apptruco

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoricoActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nomeJ1 = intent.getStringExtra("nomeJ1") ?: "Jogador 1"
        val nomeJ2 = intent.getStringExtra("nomeJ2") ?: "Jogador 2"
        val vitoriasJ1 = intent.getIntExtra("vitoriasJ1", 0)
        val vitoriasJ2 = intent.getIntExtra("vitoriasJ2", 0)

        val voltar = findViewById<Button>(R.id.voltar)
        val historico = findViewById<TextView>(R.id.listaHistorico)

        historico.text = "🏆 Placares Acumulados:\n\n" +
                "• $nomeJ1: $vitoriasJ1 vitória(s)\n" +
                "• $nomeJ2: $vitoriasJ2 vitória(s)"

        voltar.setOnClickListener {
            finish()
        }
    }
}