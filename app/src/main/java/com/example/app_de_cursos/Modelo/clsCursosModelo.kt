package com.example.app_de_cursos.Modelo

class clsCursosModelo {
    suspend fun obtenerCursosPorCarrera(carreraId: Int): List<dataCursos> {
        return RetrofitCliente.apiService.obtenerCursos(carreraId)
    }

    suspend fun obtenerDetalleCurso(cursoId: Int): dataDetalleCurso {
        return RetrofitCliente.apiService.obtenerDetalleCurso(cursoId)
    }
}
