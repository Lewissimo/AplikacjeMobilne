package com.example.recipeapp

import android.os.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.File
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

data class RecipeClass(val title: String, val list: Array<String>)

class Recipe : AppCompatActivity() {
    private lateinit var minutesInput: EditText
    private lateinit var secondsInput: EditText
    private lateinit var countdownText: TextView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button


    private var countdownTimer: CountDownTimer? = null

    private var isPaused: Boolean = false
    private var timeLeft: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val parameterValue = intent.getStringExtra("title")

        try {
            val gson = Gson();
            val inputStream = resources.openRawResource(R.raw.data)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val person = gson.fromJson(jsonString, Array<RecipeClass>::class.java)
            for(p in person){

                if(parameterValue == p.title){
                    val arrayAdapter: ArrayAdapter<*>
                    var mListView = findViewById<ListView>(R.id.userlist)
                    arrayAdapter = ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, p.list)
                    mListView.adapter = arrayAdapter
                }

            }
        } catch (e:Exception) {
            println("Blad"+e)


        }
        minutesInput = findViewById(R.id.minutes_input)
        secondsInput = findViewById(R.id.seconds_input)
        countdownText = findViewById(R.id.countdown_text)
        startButton = findViewById(R.id.start_button)
        pauseButton = findViewById(R.id.pause_button)

        startButton.setOnClickListener {
            val min = minutesInput.text.toString().toIntOrNull() ?: 0
            val sec = secondsInput.text.toString().toIntOrNull() ?: 0
            startTimer(min * 60 + sec)
        }

        pauseButton.setOnClickListener {
            if (countdownTimer != null) {
                pauseTimer()
            }
        }
    }
    private fun startTimer(timeInSeconds: Int) {
        countdownTimer?.cancel()
        countdownTimer = object : CountDownTimer(timeInSeconds * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (!isPaused) {
                    timeLeft = millisUntilFinished
                    val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(minutes)
                    countdownText.text = String.format("%02d:%02d", minutes, seconds)
                }
            }

            override fun onFinish() {
                countdownText.text = "00:00"
            }
        }.start()
    }

    private fun pauseTimer() {
        countdownTimer?.cancel()
        isPaused = !isPaused
        pauseButton.text = if(isPaused) "Resume" else "Pause"
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeft)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeft) -
                TimeUnit.MINUTES.toSeconds(minutes)
        if(isPaused){

            countdownText.text = String.format("%02d:%02d", minutes, seconds)

        } else {
            startTimer(minutes.toInt()*60+seconds.toInt())
        }

    }

}