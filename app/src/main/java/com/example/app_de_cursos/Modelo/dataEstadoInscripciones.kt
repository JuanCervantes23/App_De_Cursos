package com.example.app_de_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataEstadoInscripciones(
    @SerialName("total") val total: Int = 0,
    @SerialName("cursos") val cursos: List<dataEstadoCurso> = emptyList()
)