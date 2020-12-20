package com.example.saimondecime


import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

     private lateinit var mediaPlayer:MediaPlayer
    //Annoying warning showing up and no other way to fix it
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.music)

        val miModelo by viewModels<MyViewModel>()

        val inicio = findViewById<Button>(R.id.inicio)
        val comprobar = findViewById<Button>(R.id.comparar)

        val azul = findViewById<Button>(R.id.azul)
        val rojo = findViewById<Button>(R.id.rojo)
        val amarillo = findViewById<Button>(R.id.amarillo)
        val verde = findViewById<Button>(R.id.verde)

        val listaBotones = listOf(verde, rojo, azul, amarillo)

        val toastStart = Toast.makeText(applicationContext, R.string.start, Toast.LENGTH_SHORT)
        val toastFinish = Toast.makeText(applicationContext, R.string.finish, Toast.LENGTH_SHORT)
        val puntuacion = findViewById<TextView>(R.id.infoText)
        val text = getString(R.string.Text)

        val difficultybutton = findViewById<Button>(R.id.velocidad)
        val toastEasy = Toast.makeText(applicationContext, R.string.easy, Toast.LENGTH_SHORT)
        val toastHard = Toast.makeText(applicationContext, R.string.hard, Toast.LENGTH_SHORT)
        val difficulty = findViewById<TextView>(R.id.dificultad)

        miModelo.listaReto.observe(this, {
            miModelo.mostrarSecuencia(listaBotones)
            puntuacion.text = "$text ${miModelo.listaReto.value!!.size}"
        })

        miModelo.dificultad.observe(this,{
            if (miModelo.dificultad.value=="easy")
                difficulty.text = getString(R.string.easy2)
            else
                difficulty.text = getString(R.string.hard2)
        })

        inicio.setOnClickListener{
            miModelo.resetear()
            miModelo.sumarValor()
            toastStart.show()

        }

        comprobar.setOnClickListener{
            if (!miModelo.compararSecuencia())
               toastFinish.show()
            puntuacion.text = "$text 0"
        }

        verde.setOnClickListener{
            miModelo.guardarSecuencia(miModelo.listaJugador, 1)
        }

        rojo.setOnClickListener{
            miModelo.guardarSecuencia(miModelo.listaJugador, 2)
        }

        azul.setOnClickListener{
            miModelo.guardarSecuencia(miModelo.listaJugador, 3)
        }

        amarillo.setOnClickListener{
            miModelo.guardarSecuencia(miModelo.listaJugador, 4)
        }

        difficultybutton.setOnClickListener{
        if (miModelo.cambiarDificultad()==1){
            toastHard.show()
        }
        else {
            toastEasy.show()
        }
        }

    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
        mediaPlayer.release()
    }


}