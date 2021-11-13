package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList : ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0

    private var mUserName : String? = null
    private var mCorrectAnswers : Int = 0


    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null

    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private var btnSubmit: Button? = null

    private var isAlreadyAnswered = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        mUserName = intent.getStringExtra(Constants.USER_Name)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)




        mQuestionsList = Constants.getQuestions()

        setQuestion()

        // Make buttons from TextViews by attaching OnClick Listeners
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
    }

    /**
     * Sets the question to UI components.
     */
    private fun setQuestion() {

//        Log.i("QuestionsList size is ", "${questionsList.size}")
//        for (i in questionsList) {
//            Log.e("Questions", i.question)
//        }
        defaultOptionsView()
        isAlreadyAnswered = false
        val question: Question = mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.
        progressBar?.progress = mCurrentPosition // Setting the current progress in the progressbar using the position of question

        // Now set the current question and the options in the UI
        ivImage?.setImageResource(question.image)
        tvProgress?.text = "$mCurrentPosition / ${progressBar?.max?.plus(1)}"
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if(mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }

    }

    /**
     * Resets all of the options which we can select from
     */
    private  fun defaultOptionsView(){
        val options = ArrayList<TextView>()// Make a list with all of the options(buttons)
        tvOptionOne?.let {
            options.add(0, it)

        }
        tvOptionTwo?.let {
            options.add(1, it)

        }
        tvOptionThree?.let {
            options.add(2, it)

        }
        tvOptionFour?.let {
            options.add(3, it)

        }
        // Set the visual for all of the buttons
        for ( option in options){
            option.setTextColor(Color.parseColor("#363A43"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg


            )// Set the visual of the buttons(options)

        }
    }

    /**
     * Set the view of selected option view.
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        if(!isAlreadyAnswered){
            defaultOptionsView() // set all buttons to it's default state(not selected)
            mSelectedOptionPosition = selectedOptionNum //  stores the selected option
            tv.setTextColor(Color.parseColor("#363A43")) //  set the color to the selected button
            tv.setTypeface(tv.typeface, Typeface.BOLD)// Make the text ot the tv bold
            tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)// set the selected button border
        }

    }

    /**
     * Chose what to do on clicking on view element. It overrides the onclick function
     */
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option_one -> {
                tvOptionOne?.let{
                    selectedOptionView(it, selectedOptionNum = 1)
                }
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let{
                    selectedOptionView(it, selectedOptionNum = 2)
                }
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let{
                    selectedOptionView(it, selectedOptionNum = 3)
                }
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let{
                    selectedOptionView(it, selectedOptionNum = 4)
                }
            }
            R.id.btn_submit ->{
                if(mSelectedOptionPosition == 0){//If there is nothing selected
                    mCurrentPosition++//move forward

                    when{//If there are still questions
                        mCurrentPosition <=mQuestionsList!!.size ->{
                            setQuestion()
                        }
                        else->{//If there are no more questions

                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_Name, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            startActivity(intent)
                            finish()
                            //Toast.makeText(this, "You made it!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{ //If it is already selected
                    val question = mQuestionsList?.get(mCurrentPosition-1)
                    if( question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        if(!isAlreadyAnswered)
                            mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size ){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                    isAlreadyAnswered = true
                }
            }
        }
    }

    /**
     * Shows if the answer is correct
     */
    private fun answerView(answer: Int, drawableView : Int){
        when(answer){
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}