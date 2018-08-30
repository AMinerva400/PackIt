package com.anthonyminerva.packit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_pack.*
import android.text.Editable
import android.text.TextUtils


class packActivity : AppCompatActivity() {

    private lateinit var mIndexCardViewModel: indexCardViewModel
    private lateinit var mCards: List<indexCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pack)
        setSupportActionBar(toolbar)

        val intent: Intent = getIntent()
        val packName: String = intent.getStringExtra("PackName")
        val recyclerView: RecyclerView = findViewById(R.id.packRecyclerView)
        val packSearch: EditText = findViewById(R.id.packSearch)
        val adapter = packListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        mIndexCardViewModel = ViewModelProviders.of(this).get(indexCardViewModel::class.java)
        mIndexCardViewModel.getPack(packName).observe(this@packActivity, object : Observer<List<indexCard>> {
            override fun onChanged(cards: List<indexCard>?) {
                mCards = if(cards !=  null) cards else emptyList<indexCard>()
                adapter.setCards(mCards)
            }
        })
        //TODO: Fix Searching Feature -> Currently seems to be issue w/ Query?
        /*packSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val searchTerm = packSearch.text.toString()
                if(TextUtils.isEmpty(searchTerm)){ //If Search Bar Empty -> Display Entire Pack
                    mIndexCardViewModel.getPack(packName).observe(this@packActivity, object : Observer<List<indexCard>> {
                        override fun onChanged(cards: List<indexCard>?) {
                            mCards = if(cards !=  null) cards else emptyList<indexCard>()
                            adapter.setCards(mCards)
                        }
                    })
                } else { //If Search Bar Not Empty -> Display Search Results
                    mIndexCardViewModel.search(packName, searchTerm).observe(this@packActivity, object : Observer<List<indexCard>> {
                        override fun onChanged(cards: List<indexCard>?) {
                            mCards = if(cards !=  null) cards else emptyList<indexCard>()
                            adapter.setCards(mCards)
                        }
                    })
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })*/

        adapter.onItemClick = { mBtn ->
            val card: indexCard = mBtn.tag as indexCard
            if(mBtn.text.equals(card.frontCard)){
                mBtn.setText(card.backCard)
            } else {
                mBtn.setText(card.frontCard)
            }
        }

        packFab.setOnClickListener { view -> //TODO: Figure out which display to use
            Snackbar.make(view, "Choose an Action", Snackbar.LENGTH_LONG)
                    .setAction("Review Cards", View.OnClickListener {
                        //TODO: Add Review Card Section - Possibly Fragment?
                    })
                    .setAction("Add New Card", View.OnClickListener {
                        //TODO: Move to newCardActivity and preset PackName
                    }).show()
        }
    }

}
