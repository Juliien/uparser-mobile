package com.esgi.uparser.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esgi.uparser.api.catalog.model.CodeResponse

class CatalogAdapter(
    private val codes: List<CodeResponse>,
    private val onCatalogClickedListener: CatalogViewHolder.OnCatalogClickedListener
) :
    RecyclerView.Adapter<CatalogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return CatalogViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = codes.size

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val code = codes[position]
        holder.bind(position + 1, code, onCatalogClickedListener)
    }
}
