package com.esgi.uparser.code

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.esgi.uparser.R
import com.esgi.uparser.api.code.model.CodeHistoryResponse

class CodeViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_code, parent, false)),
    View.OnClickListener {
    interface OnCodeClickedListener {
        fun onCodeClicked(code: CodeHistoryResponse?)
    }

    private var code: CodeHistoryResponse? = null

    private var loader: ContentLoadingProgressBar? = null
    private var rankNumberTextView: TextView? = null
    private var codeNameTextView: TextView? = null
    private var codeSubNameTextView: TextView? = null
    private var onCodeClickedListener: OnCodeClickedListener? = null

    init {
        loader = itemView.findViewById(R.id.loader)
        loader?.visibility = View.VISIBLE
        rankNumberTextView = itemView.findViewById(R.id.rankNumberTextView)
        codeNameTextView = itemView.findViewById(R.id.codeNameTextView)
        codeSubNameTextView = itemView.findViewById(R.id.codeSubNameTextView)
    }

    fun bind(rank: Int, code: CodeHistoryResponse, onCodeClickedListener: OnCodeClickedListener) {
        this.code = code
        loader?.visibility = View.GONE
        this.onCodeClickedListener = onCodeClickedListener

        rankNumberTextView?.text = "$rank"
        codeNameTextView?.text = code.date
        codeSubNameTextView?.text = code.language

        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        onCodeClickedListener?.onCodeClicked(code)
    }
}
