
package br.edu.utfpr.apptruco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class InformarNomes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_informar_nomes)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val voltar = findViewById<Button>(R.id.voltar)
        val jogador1 = findViewById<TextInputEditText>(R.id.inputJogador1)
        val jogador2 = findViewById<TextInputEditText>(R.id.inputJogador2)
        val salvar = findViewById<Button>(R.id.salvar)

        salvar.setOnClickListener {
            val nomeJogador1 = jogador1.text.toString()
            val nomeJogador2 = jogador2.text.toString()

            val resultado = Intent()
            resultado.putExtra("nomeJogador1", nomeJogador1)
            resultado.putExtra("nomeJogador2", nomeJogador2)

            setResult(RESULT_OK, resultado)
            finish()
        }

        voltar.setOnClickListener {
            finish()
        }
    }
}
