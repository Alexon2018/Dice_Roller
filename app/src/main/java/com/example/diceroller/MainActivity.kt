package com.example.diceroller

import com.example.diceroller.R
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var diceImage1 : ImageView
    private lateinit var diceImage2 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rolledButton1: Button = findViewById(R.id.randomB1)
        val rolledButton2: Button = findViewById(R.id.randomB2)
        val rolledButton3: Button = findViewById(R.id.randomAll)
        diceImage1 = findViewById(R.id.iView1)
        diceImage2 = findViewById(R.id.iView2)


        rollDice("all")

        rolledButton1.setOnClickListener {
            rollDice("left")
        }
        rolledButton2.setOnClickListener {
            rollDice("right")
        }
        rolledButton3.setOnClickListener {
            rollDice("all")
        }

    }

    /////////////////////////////////////////////////////////////////////

    private fun rollDice(providedParameter : String){
        when (providedParameter){
            "left"-> random("left")
            "right"-> random("right")
            else->{
                random("left")
                random("right")
            }
        }
    }

    /////////////////////////////////////////////////////////////////////s

    private fun random(position : String){

        val dice = Dice(6)
        val drawableResources = when (dice.roll()){
            1-> R.drawable.dice_1
            2-> R.drawable.dice_2
            3-> R.drawable.dice_3
            4-> R.drawable.dice_4
            5-> R.drawable.dice_5
            else-> R.drawable.dice_6
        }
        when(position){
            "left"-> {

                diceImage1.setImageResource(drawableResources)
                diceImage1.contentDescription=dice.roll().toString()
            }
            "right"-> {

                diceImage2.setImageResource(drawableResources)
                diceImage2.contentDescription=dice.roll().toString()
            }
        }
    }

}

/////////////////////////////////////////////////////////////////////

class Dice(private val numSides: Int){
    fun roll() : Int{
        return (1..numSides).random()
    }
}