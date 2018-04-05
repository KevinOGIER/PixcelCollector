package com.example.pixcelcolector.pixcelcollector.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.pixcelcolector.pixcelcollector.R
import com.example.pixcelcolector.pixcelcollector.model.DataBaseHandler
import com.example.pixcelcolector.pixcelcollector.model.Score
import kotlinx.android.synthetic.main.activity_scores.*

class ScoresActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
        currentScore.text = intent.getIntExtra("SCORE", 0).toString()

        // Display the scores
        val context = this
        val db = DataBaseHandler(context)

        var topScoreList: MutableList<Score> = db.selectScoreList()

        var iterator = topScoreList.listIterator()

        for(monScore in iterator)
        {
            var tempText = topScore.text
            topScore.text = tempText.toString() + monScore.username + " : " + monScore.point + "\n"
        }

        // Events
        buttonPlay.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    override fun onClick(view: View)
    {
        // IF button "play" selected
        if(view.getId() == R.id.buttonPlay)
        {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        // If button "Save" selected
        else
        {
            // Get the values provided by the user via the UI
            val username = currentUsername.getText().toString()

            // Prepare database
            val context = this
            val db = DataBaseHandler(context)

            // Insert into DataBase
            var score = Score(intent.getIntExtra("SCORE", 0), username)
            db.insertData(score)

            var rank = db.getClassement(username)

            val text = "Classement : " + rank
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
    }
}