package com.udistrital.simplemathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
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

    var puntaje: Int = 0
    var solucion: String = ""
    var respuestaUsuario: String = ""
    var vidas: Int = 3

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

        val operacion: String = intent.getStringExtra("tituloOperacion").toString()
        tvOperacion.text = operacion

        if (operacion == "Dividir") {
            tvNotaDividir.visibility = View.VISIBLE
        }

        reiniciarJuego(operacion)

        btnVerificar.setOnClickListener {
            respuestaUsuario = etRespuesta.text.toString()

            if (!respuestaUsuario.isEmpty()) {
                tvEjercicio.textSize = 20.0f
                btnSiguiente.isEnabled = true
                btnVerificar.isEnabled = false

                if (solucion == String.format("%.2f", respuestaUsuario.toDouble())){
                    puntaje += 10
                    tvEjercicio.text = "Felicitaciones!, presiona Siguiente Ejercicio  :)"
                    tvPuntaje.text = puntaje.toString()

                } else {
                    vidas -= 1
                    tvEjercicio.text = "Respuesta errada  :( "
                    tvVidas.text = vidas.toString()

                }
            } else {
                Toast.makeText(applicationContext, "Por favor ingresa un valor.", Toast.LENGTH_LONG).show()
            }
        }

        btnSiguiente.setOnClickListener {
            reiniciarJuego(operacion)
        }
    }

    fun reiniciarJuego(operacion: String): Unit{
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
}