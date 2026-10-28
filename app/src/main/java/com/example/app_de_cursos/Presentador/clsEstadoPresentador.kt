package com.example.app_de_cursos.Presentador

import com.example.app_de_cursos.Modelo.clsCarrerasModelo
import com.example.app_de_cursos.Modelo.clsEstadoModelo
import com.example.app_de_cursos.Modelo.dataCarreras
import com.example.app_de_cursos.Vista.EstadoInscripcionesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsEstadoPresentador(val vista: EstadoInscripcionesActivity) {
    private val modeloCarreras = clsCarrerasModelo()
    private val modeloEstado = clsEstadoModelo()
    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Cargar la lista de carreras en el Spinner
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)

                // Cargar TODOS los estados por defecto
                cargarEstadoInscripciones(0)

            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int) {
        if (posicion >= 0 && posicion < listaCarreras.size) {
            val carrera = listaCarreras[posicion]
            cargarEstadoInscripciones(carrera.id)
        }
    }

    private fun cargarEstadoInscripciones(carreraId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modeloEstado.obtenerEstado(carreraId)
                // Usamos respuesta.total tal como viene en el PHP
                vista.mostrarDatosEstado(respuesta.total, respuesta.cursos)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar estado: " + e.localizedMessage)
            }
        }
    }
}