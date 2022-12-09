package com.udistrital.simplemathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class JuegoActivity : AppCompatActivity() {
    lateinit var tvOperacion: TextView
    lateinit var btnVerificar: Button
    lateinit var btnSiguiente: Button
    lateinit var tvEjercicio: TextView
    lateinit var etRespuesta: EditText
    lateinit var tvNotaDividir: TextView
    lateinit var tvPuntaje: TextView
    lateinit var tvVidas: TextView
    lateinit var tvTiempo: TextView
    lateinit var timer: CountDownTimer

    var puntaje: Int = 0
    var solucion: String = ""
    var respuestaUsuario: String = ""
    var vidas: Int = 3
    val startTimerInMillis: Long  = 61000
    var timeLeftInMillis: Long = startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        btnVerificar = findViewById(R.id.btnVerificar)
        btnSiguiente = findViewById(R.id.btnSiguiente)
        tvOperacion = findViewById(R.id.tvOperacion)
        tvEjercicio = findViewById(R.id.tvEjercicio)
        etRespuesta = findViewById(R.id.etRespuesta)
        tvNotaDividir = findViewById(R.id.tvNotaDividir)
        tvPuntaje = findViewById(R.id.tvPuntaje)
        tvVidas = findViewById(R.id.tvVidas)
        tvTiempo = findViewById(R.id.tvTiempo)

        val operacion: String = intent.getStringExtra("tituloOperacion").toString()
        tvOperacion.text = operacion

        if (operacion == "Dividir") {
            tvNotaDividir.visibility = View.VISIBLE
        }

        reiniciarJuego(operacion)

        btnVerificar.setOnClickListener {
            respuestaUsuario = etRespuesta.text.toString()

            if (!respuestaUsuario.isEmpty()) {
                pauseTimer()

                if (solucion == String.format("%.2f", respuestaUsuario.toDouble())){
                    puntaje += 10
                    actualizarEjercicioTextoEnRespuesta("Felicitaciones!, presiona Siguiente Ejercicio  :)")
                    tvPuntaje.text = puntaje.toString()

                } else {
                    modificarVida()
                    actualizarEjercicioTextoEnRespuesta("Respuesta errada: ${tvEjercicio.text} = $solucion")
                }
            } else {
                Toast.makeText(applicationContext, "Por favor ingresa un valor.", Toast.LENGTH_LONG).show()
            }
        }

        btnSiguiente.setOnClickListener {
            if (vidas > 0){
                reiniciarJuego(operacion)
            } else {
                println("")
            }
        }
    }

    fun reiniciarJuego(operacion: String): Unit{
        resetTimer()
        startTimer()
        tvEjercicio.textSize = 40.0f
        btnSiguiente.isEnabled = false
        btnVerificar.isEnabled = true
        etRespuesta.text.clear()

        when (operacion) {
            "Sumar" -> {
                var operando1: Int = Random.nextInt(1, 100)
                var operando2: Int = Random.nextInt(1, 100)
                tvEjercicio.text = "$operando1 + $operando2"
                solucion = String.format("%.2f",(operando1 + operando2).toDouble())
            }
            "Dividir" -> {
                var operando1: Int = Random.nextInt(20, 40)
                var operando2: Int = Random.nextInt(operando1-20, operando1)
                tvEjercicio.text = "$operando1 / $operando2"
                solucion = String.format("%.2f", (operando1.toDouble() / operando2.toDouble()))
            }
            "Multiplicar" -> {
                var operando1: Int = Random.nextInt(1, 100)
                var operando2: Int = Random.nextInt(1, 100)
                tvEjercicio.text = "$operando1 x $operando2"
                solucion = String.format("%.2f", (operando1 * operando2).toDouble())
            }
        }
    }

    fun startTimer(){
        timer = object: CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                actualizarTiempoTexto()
            }

            override fun onFinish() {
                timeLeftInMillis = 0
                actualizarTiempoTexto()
                pauseTimer()
                modificarVida()

                actualizarEjercicioTextoEnRespuesta("Se acabó el tiempo. Te quedan $vidas vidas")
            }

        }.start()
    }

    fun actualizarEjercicioTextoEnRespuesta(texto: String) {
        tvEjercicio.textSize = 23.0f
        btnSiguiente.isEnabled = true
        btnVerificar.isEnabled = false
        tvEjercicio.text = texto
    }

    fun actualizarTiempoTexto(){
        // Dividimos el tiempo restante entre 1000 para pasarlo de milisegundos a segundos
        tvTiempo.text = String.format("%02d",(timeLeftInMillis / 1000).toInt())
    }

    fun pauseTimer(){
        timer.cancel()
    }

    fun resetTimer(){
        timeLeftInMillis = startTimerInMillis
        actualizarTiempoTexto()
    }

    fun modificarVida(){
        vidas -= 1
        tvVidas.text = vidas.toString()
    }


}