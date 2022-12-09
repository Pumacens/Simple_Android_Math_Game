package com.udistrital.simplemathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btnSumar: Button
    lateinit var btnDividir: Button
    lateinit var btnMultiplicar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSumar = findViewById(R.id.btnSumar)
        btnDividir = findViewById(R.id.btnDividir)
        btnMultiplicar = findViewById(R.id.btnMultiplicar)

        var juegoActivityIntent = Intent(this@MainActivity, JuegoActivity::class.java)

        btnSumar.setOnClickListener {
            juegoActivityIntent.putExtra("tituloOperacion", "Sumar")
            startActivity(juegoActivityIntent)
        }

        btnDividir.setOnClickListener {
            juegoActivityIntent.putExtra("tituloOperacion", "Dividir")
            startActivity(juegoActivityIntent)
        }

        btnMultiplicar.setOnClickListener {
            juegoActivityIntent.putExtra("tituloOperacion", "Multiplicar")
            startActivity(juegoActivityIntent)
        }
    }


}