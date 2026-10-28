package com.example.app_de_cursos.Presentador

import com.example.app_de_cursos.Modelo.clsEstadoModelo
import com.example.app_de_cursos.Vista.EstadoInscripcionesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsEstadoPresentador(private val vista: EstadoInscripcionesActivity) {

    private val modelo = clsEstadoModelo()

    fun cargarEstadoInscripciones(carreraId: Int) {
        // Validación antes de llamar a la API
        if (carreraId <= 0) {
            vista.mostrarError("Carrera no válida para filtrar")
            return
        }

        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Retrofit maneja la red internamente, por lo que es seguro llamarlo desde Main
                val respuesta = modelo.obtenerEstado(carreraId)

                // Pasamos los datos a la Vista (Activity)
                vista.mostrarDatos(respuesta.totalInscritos, respuesta.cursos)

            } catch (e: Exception) {
                vista.mostrarError("Error de conexión: " + (e.localizedMessage ?: "Error desconocido"))
            } finally {
                vista.mostrarCargando(false)
            }
        }
    }
}