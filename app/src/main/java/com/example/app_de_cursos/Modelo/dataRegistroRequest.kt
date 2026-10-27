package com.example.app_de_cursos.Modelo

import kotlinx.serialization.Serializable

@Serializable
data class dataRegistroRequest(
    val curso_id: Int,
    val matricula: String,
    val fecha_registro: String,
    val correo_institucional: String
)
