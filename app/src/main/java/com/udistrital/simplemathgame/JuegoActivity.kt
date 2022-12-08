package com.udistrital.simplemathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class JuegoActivity : AppCompatActivity() {
    lateinit var tvOperacion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        tvOperacion = findViewById(R.id.tvOperacion)
        tvOperacion.text = intent.getStringExtra("tituloOperacion").toString()
    }
}