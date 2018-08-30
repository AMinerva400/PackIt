package com.anthonyminerva.packit

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class packListAdapter(context: Context): RecyclerView.Adapter<packListAdapter.packViewHolder>() {

    class packViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val indexCardItemView: Button

        init {
            indexCardItemView = itemView.findViewById(R.id.recyclerButton)
        }

    }

    private val mInflater: LayoutInflater
    private var mCards: List<indexCard> = emptyList()
    var onItemClick: ((Button) -> Unit)? = null
    lateinit var Card: indexCard
    var side: Boolean = true

    init{
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): packViewHolder {
        val itemView: View = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return packViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: packViewHolder, position: Int) {
        if(mCards != emptyList<String>()){
            val current: indexCard = mCards.get(position)
            Card = current
            val mBtn: Button = holder.indexCardItemView
            mBtn.setTag(mCards.get(position))
            holder.indexCardItemView.setText(current.frontCard)
            holder.indexCardItemView.setOnClickListener(View.OnClickListener {
                onItemClick?.invoke(mBtn)
            })
        } else {
            holder.indexCardItemView.setText("No Pack")
        }
    }

    fun setCards(cards: List<indexCard>){
        mCards = cards
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if(mCards != emptyList<String>()){
            return mCards.size
        } else {
            return 0
        }
    }

}