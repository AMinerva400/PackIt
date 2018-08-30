package com.anthonyminerva.packit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_home.*

class homeActivity : AppCompatActivity() {

    private lateinit var  mIndexCardViewModel: indexCardViewModel
    private lateinit var  packNames: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(homeToolbar)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adapter: indexCardListAdapter = indexCardListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        mIndexCardViewModel = ViewModelProviders.of(this).get(indexCardViewModel::class.java)
        mIndexCardViewModel.getAllPacks().observe(this, object : Observer<List<String>> {
            override fun onChanged(@Nullable packs: List<String>?) {
                packNames = if(packs != null) packs else emptyList<String>()
                adapter.setPackNames(if(packs != null) packs else emptyList<String>())
            }
        })
        adapter.onItemClick = { pack ->
            val intent: Intent = Intent(this, packActivity::class.java)
            intent.putExtra("PackName", pack)
            startActivity(intent)
        }
        homeNewEntry.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
            //TODO: Figure out how to pass Pack Names along to NewCardActivity
            val intent: Intent = Intent(this, newCardActivity::class.java)
            intent.putExtra("PackNames", packNames.toTypedArray())
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        if(requestCode == 1 && resultCode == RESULT_OK){
            val front: String = data.getStringExtra("Front")
            val back: String = data.getStringExtra("Back")
            val pack: String = data.getStringExtra("PackName")
            val card: indexCard = indexCard(front, back, pack)
            mIndexCardViewModel.insert(card)
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

}
