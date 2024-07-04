package com.desafiolatam.surveydonkey.viewmodel

import androidx.lifecycle.ViewModel
import com.desafiolatam.surveydonkey.ui.COLOR
import com.desafiolatam.surveydonkey.ui.COLORS
import com.desafiolatam.surveydonkey.ui.MATERIAL
import com.desafiolatam.surveydonkey.ui.MATERIALS
import com.desafiolatam.surveydonkey.ui.STRAP_COLOR
import com.desafiolatam.surveydonkey.ui.STRAP_COLORS

class MainViewModel : ViewModel() {

    private var firstAnswer: ArrayList<String> = arrayListOf()
    private var secondAnswer: ArrayList<String> = arrayListOf()
    private var thirddAnswer: ArrayList<String> = arrayListOf()
    private var email: String = ""
    private var sugerencia: String = ""

    private val separator = ", "

    /**
     * Guarda la primera pregunta y retorna un listado de String,
     * ordenado y sin repetir opciones
     */
    fun addFirstAnswer(value: String): List<String> {
        if (!firstAnswer.contains(value)) {
            firstAnswer.add(value)
        }
        return firstAnswer.distinct().sorted().toList()
    }

    /**
     * Si el usuario deseleccion una opcion, entonces la eliminamos de la lista "firstAnswer"
     */
    fun removeFirstAnswer(value: String): List<String> {
        if (firstAnswer.contains(value)) {
            firstAnswer.remove(value)
        }
        return firstAnswer.sorted().toList()
    }

    /**
     * Guarda la segunda pregunta y retorna un listado de String,
     * ordenado y sin repetir opciones
     */
    fun addSecondAnswer(value: String): List<String> {
        if (!secondAnswer.contains(value)) {
            secondAnswer.add(value)
        }
        return secondAnswer.distinct().sorted().toList()
    }

    /**
     * Si el usuario deseleccion una opcion, entonces la eliminamos de la lista "secondAnswer"
     */
    fun removeSecondAnswer(value: String): List<String> {
        if (secondAnswer.contains(value)) {
            secondAnswer.remove(value)
        }
        return secondAnswer.sorted().toList()
    }

    /**
     * Muestra el resultado de la primera pregunta, separado por ","
     */
    fun getFirstResult(): String =
        when (firstAnswer.size) {
            1 -> "$COLOR ${firstAnswer.joinToString(separator)}"
            else -> "$COLORS ${firstAnswer.joinToString(separator)}"
        }

    /**
     * Muestra el resultado de la segunda pregunta, separado por ","
     */
    fun getSecondResult(): String =
        when (secondAnswer.size) {
            1 -> "$MATERIAL: ${secondAnswer.joinToString(separator)}"
            else -> "$MATERIALS ${secondAnswer.joinToString(separator)}"
        }


    // Tercer fragmento

    fun addThirdAnswer(value: String): List<String> {
        if (!thirddAnswer.contains(value)) {
            thirddAnswer.add(value)
        }
        return thirddAnswer.distinct().sorted().toList()
    }

    fun removeThirdAnswer(value: String): List<String> {
        if (thirddAnswer.contains(value)) {
            thirddAnswer.remove(value)
        }
        return thirddAnswer.sorted().toList()
    }

    fun getThirdResult(): String =
        when (thirddAnswer.size) {
            1 -> "$STRAP_COLOR:  ${thirddAnswer.joinToString(separator)}"
            else -> "$STRAP_COLORS ${thirddAnswer.joinToString(separator)}"
        }

    fun setEmail(value: String): String{
        email = value
        return email
    }

    fun setSugerencia(value: String): String{
        sugerencia = value
        return sugerencia
    }

    fun getEmail(): String = email

    fun getUserComment(): String = sugerencia
}
