package com.example.app_de_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRespuestaRegistro(
    @SerialName("exito") val exito: Boolean = false,
    @SerialName("mensaje") val mensaje: String = ""

)