package com.example.pixcelcolector.pixcelcollector.view

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.example.pixcelcolector.pixcelcollector.R
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import android.R.raw



class GameActivity : AppCompatActivity(), SensorEventListener {

    val mediaPlayer by lazy {
        MediaPlayer.create(this, R.raw.sax)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        mediaPlayer.start()

        var rand = Random()
        var newCoordX = 1 + rand.nextInt(900)
        var newCoordY = 1 + rand.nextInt(900)

        square.x = newCoordX.toFloat()
        square.y = newCoordY.toFloat()

        object : CountDownTimer(15000, 1000) {

            override fun onTick(millisUntilFinished: Long)
            {
                chronometer.setText("seconds remaining: " + millisUntilFinished / 1000)
                //here you can have your logic to set text to edittext
            }

            override fun onFinish() {
                val inent = Intent(this@GameActivity, ScoresActivity::class.java)
                startActivity(inent)
                finish()
            }

        }.start()

    }

    var score = 0

    val mSensorManager: SensorManager by lazy{
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    val mGravity: Sensor by lazy{
        mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) as Sensor
    }

    val vibratorService: Vibrator by lazy {
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onSensorChanged(event: SensorEvent) {
        magicball.translationX = - (event.values[2] * 6)
        magicball.translationY = - event.values[1] * 12

        //Log.i("tag", (boulemagique.x + boulemagique.width).toString())

        if ((magicball.y < square.y && magicball.y + magicball.height > square.y + square.height) && (magicball.x < square.x && magicball.x + magicball.width > square.x + square.width)) {
            score++
            displayedScore.setText(score.toString())
            Log.i("Succes", score.toString())

            var rand = Random()
            var newCoordX = 1 + rand.nextInt(900)
            var newCoordY = 1 + rand.nextInt(900)

            square.x = newCoordX.toFloat()
            square.y = newCoordY.toFloat()
        }

        if(magicball.x < -40 || magicball.x + magicball.width > 1100){
            vibratorService.vibrate(100)
        }
    }



    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this,mGravity, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onRestart() {
        super.onRestart()
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mediaPlayer.release()

    }
}
