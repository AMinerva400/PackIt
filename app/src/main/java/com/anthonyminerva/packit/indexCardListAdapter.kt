package com.anthonyminerva.packit

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class indexCardListAdapter(context: Context): RecyclerView.Adapter<indexCardListAdapter.indexCardViewHolder>() {

    class indexCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val indexCardItemView: Button

        init {
            indexCardItemView = itemView.findViewById(R.id.recyclerButton)
        }

    }

    private val mInflater: LayoutInflater
    private var mPackNames: List<String> = emptyList()
    var onItemClick: ((String) -> Unit)? = null

    init{
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): indexCardViewHolder {
        val itemView: View = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return indexCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: indexCardViewHolder, position: Int) {
        if(mPackNames != emptyList<String>()){
            val current: String = mPackNames.get(position)
            holder.indexCardItemView.setText(current)
            holder.indexCardItemView.setOnClickListener(View.OnClickListener {
                onItemClick?.invoke(mPackNames.get(position))
            })
        } else {
            holder.indexCardItemView.setText("No Pack")
        }
    }

    fun setPackNames(packs: List<String>){
        mPackNames = packs
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if(mPackNames != emptyList<String>()){
            return mPackNames.size
        } else {
            return 0
        }
    }

}