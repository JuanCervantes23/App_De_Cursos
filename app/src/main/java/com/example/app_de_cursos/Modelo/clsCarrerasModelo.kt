package com.example.app_de_cursos.Modelo

class clsCarrerasModelo {
    suspend fun obtenerCarreras(): List<dataCarreras> {
        val datos = RetrofitCliente.apiService.obtenerCarreras() // Aquí hace el pedido a la BD

        val listaFinal = mutableListOf(dataCarreras(0, "Todas las carreras"))
        listaFinal.addAll(datos)

        return listaFinal
    }
}
