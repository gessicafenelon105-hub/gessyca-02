package com.example.lab2gessy

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val cancel_btn = findViewById<ImageView>(R.id.imageView2)
        val save_btn = findViewById<ImageView>(R.id.imageView3)
        val questionF = findViewById<EditText>(R.id.champ1)
        val answerF = findViewById<EditText>(R.id.champ2)

        cancel_btn.setOnClickListener{
            setResult(RESULT_CANCELED) // set result code and bundle data for response
            finish()
        }

        save_btn.setOnClickListener{
            val question = questionF.text.toString()
            val answer = answerF.text.toString()
            val data = Intent() // create a new Intent, this is where we will put our data
            data.putExtra(
                "question_key",
                question
            ) // puts one string into the Intent, with the key as 'string1'

            data.putExtra(
                "answer_key",
                answer
            ) // puts another string into the Intent, with the key as 'string2

            setResult(RESULT_OK, data) // set result code and bundle data for response

            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}