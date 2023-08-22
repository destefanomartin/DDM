package com.utn.myfirstapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var textLabel : TextView
    lateinit var activationButton : Button
    lateinit var blueButton : Button
    lateinit var greenButton : Button
    lateinit var desactivationButton : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        textLabel = findViewById(R.id.textLabel)
        activationButton = findViewById(R.id.activationBotton)
        desactivationButton = findViewById(R.id.desactivationButton)
        greenButton = findViewById(R.id.greenButton)
        blueButton = findViewById(R.id.blueButton)

        textLabel.text = ""
        activationButton.text = "Activar mensaje"
        blueButton.text = "Mensaje en azul"
        greenButton.text = "Mensaje en verde"
        desactivationButton.text = "Desactivar mensaje"

        activationButton.setOnClickListener{
            textLabel.text = "Primer proyecto"
            textLabel.setTextColor(Color.BLACK)
        }

        desactivationButton.setOnClickListener {
            textLabel.text = ""
            textLabel.setTextColor(Color.BLACK)
        }

        blueButton.setOnClickListener {
            textLabel.setTextColor(Color.BLUE)
        }

        greenButton.setOnClickListener {
            textLabel.setTextColor(Color.GREEN)
        }
    }
}

