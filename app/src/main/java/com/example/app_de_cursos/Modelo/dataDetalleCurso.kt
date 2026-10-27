package com.example.app_de_cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDetalleCurso(
    @SerialName("id") val id: Int = 0,
    @SerialName("carrera_id") val carreraId: Int = 0,
    @SerialName("codigo") val codigo: String = "",
    @SerialName("nombre") val nombre: String = "",
    @SerialName("descripcion") val descripcion: String = "Sin descripcion",
    @SerialName("edificio") val edificio: String = "",
    @SerialName("salon") val salon: String = "",
    @SerialName("total_horas") val totalHoras: Int = 0,
    @SerialName("dias_atencion") val diasAtencion: String = "",
    @SerialName("horario") val horario: String = "",
    @SerialName("cupo_total") val cupoTotal: Int = 0,
    @SerialName("url_imagen_detalle") val urlImagenDetalle: String = ""
)
