package com.example.app_de_cursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_de_cursos.Modelo.dataEstadoCurso
import com.example.app_de_cursos.R

class EstadoAdaptador(private var listaCursos: List<dataEstadoCurso>) :
    RecyclerView.Adapter<EstadoAdaptador.EstadoViewHolder>() {

    class EstadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCurso: ImageView = itemView.findViewById(R.id.imgEstado)
        val txtNombreCurso: TextView = itemView.findViewById(R.id.txtNombreEstado)
        val pbCupo: ProgressBar = itemView.findViewById(R.id.pbCupo)
        val txtInscritos: TextView = itemView.findViewById(R.id.txtInscritos)
        val txtTotalCupo: TextView = itemView.findViewById(R.id.txtCupo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estado, parent, false)
        return EstadoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        val curso = listaCursos[position]

        holder.txtNombreCurso.text = curso.nombre
        holder.txtInscritos.text = "${curso.inscritos} inscritos"
        holder.txtTotalCupo.text = "/ ${curso.cupoTotal} total"

        // Barra de progreso
        holder.pbCupo.max = curso.cupoTotal
        holder.pbCupo.progress = curso.inscritos

        val urlBase = "http://10.0.2.2/cursos_api/"
        val urlImagen = urlBase + curso.urlImagenPrincipal

        Glide.with(holder.itemView.context)
            .load(urlImagen)
            .into(holder.imgCurso)
    }

    fun actualizarLista(nuevaLista: List<dataEstadoCurso>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}