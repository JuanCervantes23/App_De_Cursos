package com.example.app_de_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursos(
    @SerialName("id_curso")
    val id: Int = 0,

    @SerialName("codigo")
    val codigo: String = "",

    @SerialName("nombre")
    val nombre: String = "",

    @SerialName("descripcion")
    val descripcion: String = "Sin descripcion",

    @SerialName("espacio")
    val espacio: String = "",

    @SerialName("total_horas")
    val totalHoras: Int = 0,

    @SerialName("cupo")
    val cupo: Int = 0,

    @SerialName("imagen_url")
    val urlimagen: String = "",

    @SerialName("inscritos")
    val inscritos: Int = 0
)
