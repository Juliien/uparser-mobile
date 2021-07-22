package com.esgi.uparser.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.esgi.uparser.R
import com.esgi.uparser.api.catalog.model.CodeResponse

class CatalogViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_catalog, parent, false)),
    View.OnClickListener {
    interface OnCatalogClickedListener {
        fun onCatalogClicked(code: CodeResponse?)
    }

    private var code: CodeResponse? = null

    private var loader: ContentLoadingProgressBar? = null
    private var rankNumberTextView: TextView? = null
    private var catalogNameTextView: TextView? = null
    private var catalogSubNameTextView: TextView? = null
    private var onCatalogClickedListener: OnCatalogClickedListener? = null

    init {
        loader = itemView.findViewById(R.id.loader)
        loader?.visibility = View.VISIBLE
        rankNumberTextView = itemView.findViewById(R.id.rankNumberTextView)
        catalogNameTextView = itemView.findViewById(R.id.codeNameTextView)
        catalogSubNameTextView = itemView.findViewById(R.id.codeSubNameTextView)
    }

    fun bind(rank: Int, code: CodeResponse, onCatalogClickedListener: OnCatalogClickedListener) {
        this.code = code
        loader?.visibility = View.GONE
        this.onCatalogClickedListener = onCatalogClickedListener

        rankNumberTextView?.text = "$rank"
        catalogNameTextView?.text = code.id
        catalogSubNameTextView?.text = code.userId

        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        onCatalogClickedListener?.onCatalogClicked(code)
    }
}
