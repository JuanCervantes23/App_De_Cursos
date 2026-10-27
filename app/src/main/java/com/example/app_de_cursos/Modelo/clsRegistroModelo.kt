package com.example.app_de_cursos.Modelo

class clsRegistroModelo {
    suspend fun registrarInscripcion(
        cursoId: Int,
        matricula: String,
        fechaRegistro: String,
        correo: String
    ): dataRespuestaRegistro {
        val request = dataRegistroRequest(
            cursoId = cursoId,
            matricula = matricula,
            fechaRegistro = fechaRegistro,
            correoInstitucional = correo
        )
        return RetrofitCliente.apiService.registrarInscripcion(request)
    }
}