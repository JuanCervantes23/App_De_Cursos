package com.example.app_de_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRegistroRequest(
    @SerialName("curso_id") val cursoId: Int = 0,
    @SerialName("matricula") val matricula: String = "",
    @SerialName("fecha_registro") val fechaRegistro: String = "",
    @SerialName("correo_institucional") val correoInstitucional: String = ""
)
