package com.example.app_de_cursos.Modelo

import kotlinx.serialization.Serializable

@Serializable
data class dataDetalleCurso(
    val id: Int,
    val carrera_id: Int,
    val codigo: String,
    val nombre: String,
    val descripcion: String? = null,
    val edificio: String? = null,
    val salon: String? = null,
    val total_horas: Int,
    val cupo_total: Int,
    val url_imagen_detalle: String? = null,
    val dias_atencion: String,
    val horario: String

)
