package com.example.app_de_cursos.Presentador

import com.example.app_de_cursos.Modelo.dataDetalleCurso
import com.example.app_de_cursos.Vista.DetalleCursoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleCursoPresentador(private  val vista: DetalleCursoActivity) {
    private val modelo = clsCursosModelo()

    fun cargarDetalle(cursoId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Aquí se consume obtenerDetalleCurso del modelo (devuelve un solo objeto)
                val curso: dataDetalleCurso = modelo.obtenerDetalleCurso(cursoId)
                vista.mostrarDetalle(curso)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar detalle: " + e.localizedMessage)
            }
        }
    }
}