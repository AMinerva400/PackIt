package com.anthonyminerva.packit

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*

class newCardActivity : AppCompatActivity() {

    private lateinit var mPackName: EditText
    private lateinit var mFront: EditText
    private lateinit var mBack: EditText
    private lateinit var mSpinner: Spinner
    private lateinit var mBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)
        mPackName = findViewById(R.id.newCardEditPack)
        mFront = findViewById(R.id.newCardEditFront)
        mBack = findViewById(R.id.newCardEditBack)
        mSpinner = findViewById(R.id.newCardSpinner)
        mBtn = findViewById(R.id.newCardBtnSave)
        val intent: Intent = getIntent()
        val packNames: Array<String> = intent.getStringArrayExtra("PackNames")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, packNames)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mSpinner.adapter = spinnerAdapter
        mSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mPackName.setText(packNames[position])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {  }
        }

        mBtn.setOnClickListener(View.OnClickListener {
            val replyIntent = Intent()
            val packName: String = mPackName.text.toString()
            val front: String = mFront.text.toString()
            val back: String = mBack.text.toString()
            if (TextUtils.isEmpty(packName) || TextUtils.isEmpty(front) || TextUtils.isEmpty(back)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra("PackName", packName)
                replyIntent.putExtra("Front", front)
                replyIntent.putExtra("Back", back)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        })
    }
}
