package com.example.app_de_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataEstadoCurso(
    @SerialName("id") val id: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("url_imagen_principal") val urlImagenPrincipal: String = "",
    @SerialName("inscritos") val inscritos: Int = 0,
    @SerialName("cupo_total") val cupoTotal: Int = 0
)