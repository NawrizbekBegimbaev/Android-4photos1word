package com.example.a4photos1word
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.a4photos1word.databinding.ActivityGameBinding
import kotlin.random.Random
class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    lateinit var questions: List<Questions>
    lateinit var prefs: SharedPreferences
    var questionId = -1
    var clicked1 = -1
    var clicked2 = -1
    var clicked3 = -1
    var clicked4 = -1
    var score = 1
    var answerLetters = mutableListOf<TextView>()
    var quesLetter = mutableListOf<TextView>()
    var answerList = mutableListOf<Char>()
    var quesList = mutableListOf<Pair<String,TextView>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        questions = Content.getQuestion()
        prefs = getSharedPreferences(Content.PREFS, Context.MODE_PRIVATE)
        binding.back.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.apply {
            answerLetters.addAll(
                listOf(
                    ans1,ans2,ans3,ans4,ans5,ans6,
                    ans7,ans8,ans9,ans10,ans11,ans12
                )
            )
            quesLetter.addAll(
                listOf(
                    qs1,qs2,qs3,qs4,qs5,qs6,qs7,qs8
                )
            )
        }

        answerLetters.forEach{ answer ->
            answer.addTextChangedListener {
                val letter = it.toString()
                answer.isClickable = letter.isNotEmpty()
            }
        }
        quesLetter.forEach{ answer ->
            answer.addTextChangedListener {
                val letter = it.toString()
                answer.isClickable = letter.isNotEmpty()
            }
        }
        questionId = prefs.getInt(Content.LEVEL,0)
        setQuestion()

        binding.apply {
            ans1.setOnClickListener {
                Click(it as TextView)
            }
            ans2.setOnClickListener {
                Click(it as TextView)
            }
            ans3.setOnClickListener {
                Click(it as TextView)
            }
            ans4.setOnClickListener {
                Click(it as TextView)
            }
            ans5.setOnClickListener {
                Click(it as TextView)
            }
            ans6.setOnClickListener {
                Click(it as TextView)
            }
            ans7.setOnClickListener {
                Click(it as TextView)
            }
            ans8.setOnClickListener {
                Click(it as TextView)
            }
            ans9.setOnClickListener {
                Click(it as TextView)
            }
            ans10.setOnClickListener {
                Click(it as TextView)
            }
            ans11.setOnClickListener {
                Click(it as TextView)
            }
            ans12.setOnClickListener {
                Click(it as TextView)
            }
            qs1.setOnClickListener {
                ClickS(it as TextView)
            }
            qs2.setOnClickListener {
                ClickS(it as TextView)
            }
            qs3.setOnClickListener {
                ClickS(it as TextView)
            }
            qs4.setOnClickListener {
                ClickS(it as TextView)
            }
            qs5.setOnClickListener {
                ClickS(it as TextView)
            }
            qs6.setOnClickListener {
                ClickS(it as TextView)
            }
            qs7.setOnClickListener {
                ClickS(it as TextView)
            }
            qs8.setOnClickListener {
                ClickS(it as TextView)
            }

            img1.setOnClickListener{
                clicked1 = 0
                big1.setImageResource(questions[questionId].images[0])
                big1.visibility = View.VISIBLE
                img1.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@GameActivity,R.anim.animation_up_1
                    )
                )
            }
            big1.setOnClickListener {
                when(clicked1){
                    0 -> {
                        big1.startAnimation(AnimationUtils.loadAnimation(this@GameActivity,R.anim.animation_down_1))
                        Handler().postDelayed({
                            big1.visibility = View.GONE
                        },200L)
                    }
                }
            }
            img2.setOnClickListener{
                clicked2 = 0
                big2.setImageResource(questions[questionId].images[1])
                big2.visibility = View.VISIBLE
                img2.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@GameActivity,R.anim.animation_up_2
                    )
                )
            }
            big2.setOnClickListener {
                when(clicked2){
                    0 -> {
                        big2.startAnimation(AnimationUtils.loadAnimation(this@GameActivity,R.anim.animation_down_2))
                        Handler().postDelayed({
                            big2.visibility = View.GONE
                        },200L)
                    }
                }
            }
            img3.setOnClickListener{
                clicked3 = 0
                big3.setImageResource(questions[questionId].images[2])
                big3.visibility = View.VISIBLE
                img3.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@GameActivity,R.anim.animation_up_3
                    )
                )
            }
            big3.setOnClickListener {
                when(clicked3){
                    0 -> {
                        big3.startAnimation(AnimationUtils.loadAnimation(this@GameActivity,R.anim.animation_down_3))
                        Handler().postDelayed({
                            big3.visibility = View.GONE
                        },200L)
                    }
                }
            }
            img4.setOnClickListener{
                clicked4 = 0
                big4.setImageResource(questions[questionId].images[3])
                big4.visibility = View.VISIBLE
                img4.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@GameActivity,R.anim.animation_up_4
                    )
                )
            }
            big4.setOnClickListener {
                when(clicked4){
                    0 -> {
                        big4.startAnimation(AnimationUtils.loadAnimation(this@GameActivity,R.anim.animation_down_4))
                        big4.visibility = View.GONE
                    }
                }
            }
        }
    }
    private fun setQuestion() {
        val question = questions[questionId]

        prefs.edit().putInt(Content.LEVEL,questionId).apply()
        answerLetters.forEach{
            it.isClickable = true
        }
        binding.apply {
            updateAnswer(question)
            quesList.clear()
            showOverLay(false)
            var cycle = prefs.getInt(Content.CYCLE,0)
            levelId.text = (cycle * questions.size+questionId+1).toString()
            img1.setImageResource(question.images[0])
            img2.setImageResource(question.images[1])
            img3.setImageResource(question.images[2])
            img4.setImageResource(question.images[3])
            val answers = question.answer.toMutableList()
            repeat(12-answers.size){
                answers.add(Random.nextInt(1040,1072).toChar())
            }
            answers.shuffle()
            answerList.clear()
            answerList.addAll(answers)
            answers.forEachIndexed{ index, letter ->
                answerLetters[index].text = letter.toString()
            }
            quesLetter.forEach{
                it.text = ""
                it.isVisible = true
                it.isClickable = true
            }
            for(i in question.answer.length until  quesLetter.size){
                quesLetter[i].isVisible = false
            }
        }
    }
    private fun showOverLay(show: Boolean){
        binding.apply {
            bgOverlay.isVisible = show
            btnPlay.isVisible = show
            shine.isVisible = show
            textPlay.isVisible = show
            quesLetter.forEach{
                it.isClickable = false
            }
            btnPlay.setOnClickListener{
                if (questionId == questions.size-1){
                    var cycle = prefs.getInt(Content.CYCLE,0)
                    cycle++
                    prefs.edit().putInt(Content.CYCLE,cycle).apply()
                    questionId -= questions.size

                }else{
                    questionId++
                    score++
                    setQuestion()
                }
            }
        }
    }
    private fun Click(ansClick: TextView){
        val question = questions[questionId]
        val index = answerLetters.indexOf(ansClick)
        val letter = answerList[index]

        val pairIndex = quesList.indexOfFirst { it.first == "" }
        if (pairIndex == -1){
            quesList.add(Pair(letter.toString(),ansClick))
        } else {
            quesList[pairIndex] = Pair(letter.toString(),ansClick)
        }
        updateAnswer(question)
        ansClick.text = ""
    }
    private fun ClickS(ansClick: TextView) {
        ansClick.text = ""
        val index = quesLetter.indexOf(ansClick)
        val pair = quesList[index]

        pair.second.text = pair.first
        quesList[index] = Pair("", pair.second)
        updateAnswer(questions[questionId])
    }
    private fun updateAnswer(question:Questions) {
        quesList.forEachIndexed{index, letter ->
            quesLetter[index].text = letter.first
        }
        if (question.answer.length == quesList.filter { it.first.isNotEmpty()}.size){
            if (question.answer == quesList.map{ it.first }.joinToString ("")){
                showOverLay(true)
            }
            answerLetters.forEach{
                it.isClickable = false
            }
        }else {
            answerLetters.forEach{
                it.isClickable = true
            }
        }
    }
}