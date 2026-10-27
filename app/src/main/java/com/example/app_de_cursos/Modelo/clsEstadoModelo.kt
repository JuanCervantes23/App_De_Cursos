package com.example.app_de_cursos.Modelo

class clsEstadoModelo {

    suspend fun obtenerEstado(carreraId: Int): dataEstadoInscripciones {
        return RetrofitCliente.apiService.obtenerEstadoInscripciones(carreraId = carreraId)
    }
}