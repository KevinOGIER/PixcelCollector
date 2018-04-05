package com.example.pixcelcolector.pixcelcollector.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.WindowManager
import com.example.pixcelcolector.pixcelcollector.R
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity(), SensorEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var rand = Random()
        var newCoordX = 1 + rand.nextInt(900)
        var newCoordY = 1 + rand.nextInt(900)

        square.x = newCoordX.toFloat()
        square.y = newCoordY.toFloat()
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

    override fun onDestroy() {
        super.onDestroy()
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
