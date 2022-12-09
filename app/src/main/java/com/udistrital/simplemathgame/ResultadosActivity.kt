package com.udistrital.simplemathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultadosActivity : AppCompatActivity() {
    lateinit var tvPuntajeResultado: TextView
    lateinit var tvPartidasResultado: TextView
    lateinit var btnVolverMain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        tvPartidasResultado = findViewById(R.id.tvPartidasResultado)
        tvPuntajeResultado = findViewById(R.id.tvPuntajeResultado)
        btnVolverMain = findViewById(R.id.btnVolverMain)

        tvPartidasResultado.text = intent.getIntExtra("partidasJugadas", 0).toString()
        tvPuntajeResultado.text = intent.getIntExtra("puntaje", 0).toString()

        btnVolverMain.setOnClickListener {
            var mainActivityIntent = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivityIntent)
        }
    }



}