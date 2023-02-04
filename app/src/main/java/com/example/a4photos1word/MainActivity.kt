package com.example.a4photos1word

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.ImageView
import com.example.a4photos1word.databinding.ActivityGameBinding
import com.example.a4photos1word.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var prefs: SharedPreferences
    lateinit var questions: List<Questions>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        questions = Content.getQuestion()
        prefs = getSharedPreferences(Content.PREFS, Context.MODE_PRIVATE)
        var questionId = prefs.getInt(Content.LEVEL,0)
        setQuestion(questionId)
        binding.btnPlay.setOnClickListener{
            var intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(questionId: Int){
        val currentquestionsId = questions[questionId]

        binding.apply {
            var cycle = prefs.getInt(Content.CYCLE,0)
            number.text = (((cycle * questions.size) + questionId + 1)).toString()
            img1.setImageResource(currentquestionsId.images[0])
            img2.setImageResource(currentquestionsId.images[1])
            img3.setImageResource(currentquestionsId.images[2])
            img4.setImageResource(currentquestionsId.images[3])
        }
    }
}