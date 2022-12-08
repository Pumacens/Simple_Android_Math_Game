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

        var juego_activity_intent = Intent(this@MainActivity, JuegoActivity::class.java)

        btnSumar.setOnClickListener {
            juego_activity_intent.putExtra("tituloOperacion", "Sumar")
            startActivity(juego_activity_intent)
        }

        btnDividir.setOnClickListener {
            juego_activity_intent.putExtra("tituloOperacion", "Dividir")
            startActivity(juego_activity_intent)
        }

        btnMultiplicar.setOnClickListener {
            juego_activity_intent.putExtra("tituloOperacion", "Multiplicar")
            startActivity(juego_activity_intent)
        }
    }


}