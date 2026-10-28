package com.example.app_de_cursos.Presentador

import android.util.Patterns
import com.example.app_de_cursos.Modelo.clsRegistroModelo
import com.example.app_de_cursos.Vista.RegistroActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsRegistroPresentador(private val vista: RegistroActivity) {
    private val modelo = clsRegistroModelo()

    // Se llama al presionar la tecla del teclado (o al salir del campo matrícula)
    fun buscarAlumno(matricula: String, mostrarAvisos: Boolean) {
        if (!Regex("^[0-9]{5,15}$").matches(matricula)) {
            if (mostrarAvisos) {
                vista.mostrarError("Escribe una matrícula válida para buscar")
            }
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val alumno = modelo.buscarAlumno(matricula)
                if (alumno.existe) {
                    vista.mostrarAlumnoEncontrado(alumno.correoInstitucional, alumno.fechaRegistro)
                } else if (mostrarAvisos) {
                    vista.mostrarMensaje("Matrícula nueva: captura el correo institucional")
                }
            } catch (e: Exception) {
                if (mostrarAvisos) {
                    vista.mostrarError("Error de conexión: " + (e.localizedMessage ?: "Error desconocido"))
                }
            }
        }
    }

    fun registrar(cursoId: Int, matricula: String, fechaApi: String, correo: String) {
        if (cursoId <= 0) {
            vista.mostrarError("No se pudo identificar el curso")
            return
        }
        if (matricula.isEmpty() || fechaApi.isEmpty() || correo.isEmpty()) {
            vista.mostrarError("Debe llenar todos los campos del registro")
            return
        }
        if (!Regex("^[0-9]{5,15}$").matches(matricula)) {
            vista.mostrarError("La matrícula debe contener solo números")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            vista.mostrarError("El correo institucional no tiene un formato válido")
            return
        }

        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.registrarInscripcion(cursoId, matricula, fechaApi, correo)
                if (respuesta.exito) {
                    vista.registroExitoso(respuesta.mensaje)
                } else {
                    // Ya inscrita, sin cupo, etc.
                    vista.mostrarError(respuesta.mensaje)
                }
            } catch (e: Exception) {
                vista.mostrarError("Error de conexión: " + (e.localizedMessage ?: "Error desconocido"))
            } finally {
                vista.mostrarCargando(false)
            }
        }
    }
}
