package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart : Button = findViewById(R.id.btn_start)
        val etName : EditText = findViewById(R.id.et_name)

        btnStart.setOnClickListener(){

            if (etName.text.isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            }else{//Moves to another screen
                val intent = Intent(this, QuizQuestionsActivity::class.java) // Create a variable with the new activity, which is the new screen
                startActivity(intent) //  initiate the activity
                finish()//You cant go back. The back button takes you our of the app
            }

        }




    }
}