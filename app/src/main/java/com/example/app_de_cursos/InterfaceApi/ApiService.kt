package com.example.app_de_cursos.InterfaceApi

import com.example.app_de_cursos.Modelo.dataCarreras
import com.example.app_de_cursos.Modelo.dataCursoDetalle
import com.example.app_de_cursos.Modelo.dataCursos
import com.example.app_de_cursos.Modelo.dataDetalleCurso
import com.example.app_de_cursos.Modelo.dataEstadoInscripciones
import com.example.app_de_cursos.Modelo.dataRegistroRequest
import com.example.app_de_cursos.Modelo.dataRespuestaRegistro
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    // Spinner
    // Responsable:Tomasa
    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>

    // Catalogo de cursos
    // Responsable:Tomasa
    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("carrera_id") carreraId: Int): List<dataCursos>

    // Vista detalle
    // Responsable:David
    @GET("obtener_detalle_curso.php")
    suspend fun obtenerDetalleCurso(@Query("curso_id") cursoId: Int): dataDetalleCurso

    // Registro al curso
    // Responsable:Juan Francisco
    @POST("registrar_inscripcion.php")
    suspend fun registrarInscripcion(@Body registro: dataRegistroRequest): dataRespuestaRegistro

    // Ver registros
    // Responsable:Oscar
    @GET("obtener_estado_inscripciones.php")
    suspend fun obtenerEstadoInscripciones(@Query("carrera_id") carreraId: Int): dataEstadoInscripciones
}