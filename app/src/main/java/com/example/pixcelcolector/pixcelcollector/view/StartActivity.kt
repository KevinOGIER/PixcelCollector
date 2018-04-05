package com.example.pixcelcolector.pixcelcollector.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.pixcelcolector.pixcelcollector.R
import com.example.pixcelcolector.pixcelcollector.model.DataBaseHandler
import com.example.pixcelcolector.pixcelcollector.model.Score
import kotlinx.android.synthetic.main.activity_start.*
import android.content.Intent

class StartActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

            val context = this
            val db = DataBaseHandler(context)

            var score1 = Score(1,"YoMama","01/01/2001")
            db.insertData(score1)

        val temp = 0
        //textView.setText(db.getFirstScore().toString())
    }
}