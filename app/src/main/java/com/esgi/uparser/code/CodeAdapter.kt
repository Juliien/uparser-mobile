package com.esgi.uparser.code

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esgi.uparser.api.code.model.CodeHistoryResponse

class CodeAdapter (
    private val codes: List<CodeHistoryResponse>,
    private val onCodeClickedListener: CodeViewHolder.OnCodeClickedListener
) :
    RecyclerView.Adapter<CodeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return CodeViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = codes.size

    override fun onBindViewHolder(holder: CodeViewHolder, position: Int) {
        val code = codes[position]
        holder.bind(position + 1, code, onCodeClickedListener)
    }
}
