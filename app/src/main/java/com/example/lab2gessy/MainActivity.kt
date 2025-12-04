package com.example.lab2gessy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        if (data != null) { // Check that we have data returned
            val question = data.getStringExtra("question_key") // 'string1' needs to match the key we used when we put the string in the Intent
            val answer = data.getStringExtra("answer_key")

            // Log the value of the strings for easier debugging
            Log.i("MainActivity", "question: $question")
            Log.i("MainActivity", "answer: $answer")

            findViewById<TextView>(R.id.question).text=question
            findViewById<TextView>(R.id.reponse).text=answer
        } else {
            Log.i("MainActivity", "Returned null data from AddCardActivity")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val questionText = findViewById<TextView>(R.id.question)
        val reponseText = findViewById<TextView>(R.id.reponse)
        val add = findViewById<ImageView>(R.id.add)


        questionText.setOnClickListener {
            questionText.visibility = View.GONE
            reponseText.visibility = View.VISIBLE
        }
        reponseText.setOnClickListener {
            reponseText.visibility = View.GONE
            questionText.visibility = View.VISIBLE
        }
        add.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}