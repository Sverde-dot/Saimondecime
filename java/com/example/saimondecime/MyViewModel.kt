package com.example.saimondecime



import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


import kotlin.random.Random

class MyViewModel : ViewModel() {

    var listaReto = MutableLiveData<MutableList<Int>>()
    val listaJugador = MutableLiveData<MutableList<Int>>()
    var dificultad = MutableLiveData("easy")

    init {
        listaReto.value = mutableListOf()
        listaJugador.value = mutableListOf()

    }

    fun sumarValor() {
        val numero = Random.nextInt(4) + 1
        listaReto.value?.add(numero)
        listaReto.postValue(listaReto.value)
    }

    fun guardarSecuencia(listaJugador: MutableLiveData<MutableList<Int>>, number: Int) {
        when (number) {
            1 -> listaJugador.value!!.add(1)
            2 -> listaJugador.value!!.add(2)
            3 -> listaJugador.value!!.add(3)
            else -> listaJugador.value!!.add(4)
        }
    }

    fun resetear() {
        listaReto.value!!.clear()
        listaJugador.value!!.clear()
    }

    fun mostrarSecuencia(listaBotones: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            for (colors in listaReto.value!!) {
                if (dificultad.value == "easy"){
                delay(350)
                listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))
                delay(900)
                }
                else{
                    delay(150)
                    listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFFFFF"))
                    delay(200)
                }
                when (colors){
                    1-> listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#00C853"))
                    2-> listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D50000"))
                    3-> listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#0091EA"))
                    4-> listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFD600"))
                }
            }
        }

    }

    fun compararSecuencia():Boolean{
        return if (listaReto.value == listaJugador.value) {
            sumarValor()
            listaJugador.value?.clear()
            true
        } else {
            false
        }
    }

    fun cambiarDificultad():Int{
        return if (dificultad.value=="easy") {
            dificultad.value = "hard"
            1
        }
        else {
            dificultad.value="easy"
            2
        }
    }






}