package com.example.pixcelcolector.pixcelcollector.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.pixcelcolector.pixcelcollector.R
import com.example.pixcelcolector.pixcelcollector.model.DataBaseHandler
import com.example.pixcelcolector.pixcelcollector.model.Score
import kotlinx.android.synthetic.main.activity_start.*
import android.content.Intent
import android.graphics.Point
import android.view.View
import android.widget.Toast

class StartActivity : AppCompatActivity(), View.OnClickListener
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val context = this
        val db = DataBaseHandler(context)

        val text = "Règles : vous avez 15 secondes pour récupérer le plus de carré possible en inclinant le téléphone afin de déplacer la boule. ATTENTION : Si vous touchez les bords, vous perdez 1 point !"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext, text, duration)
        val buttonHelp = buttonHelp.setOnClickListener({toast.show()})

        var score1 = Score(1,"YoMama","01/01/2001")
        db.insertData(score1)

        val temp = 0
        //textView.setText(db.getFirstScore().toString())

        buttonPlay.setOnClickListener(this);
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