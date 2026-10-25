package com.example.app_de_cursos.Modelo

import kotlinx.serialization.Serializable

@Serializable
data class dataRespuestaRegistro(val exito: Boolean, val mensaje: String)
