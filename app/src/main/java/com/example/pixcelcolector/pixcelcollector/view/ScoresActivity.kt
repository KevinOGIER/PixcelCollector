package com.example.pixcelcolector.pixcelcollector.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.example.pixcelcolector.pixcelcollector.R
import com.example.pixcelcolector.pixcelcollector.model.DataBaseHandler
import com.example.pixcelcolector.pixcelcollector.model.Score
import kotlinx.android.synthetic.main.activity_scores.*

class ScoresActivity : AppCompatActivity(), View.OnClickListener {

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
        currentScore.text = intent.getIntExtra("SCORE", 0).toString()
        buttonPlay.setOnClickListener(this)
    }

    override fun onClick(view: View)
    {
        val intent = Intent(this, GameActivity::class.java)
        // calling an activity using <intent-filter> action name
        //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");
        startActivity(intent)
        finish()
    }
}