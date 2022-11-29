package com.example.diceroller

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.MainActivity.RollType.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

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

        rollDice(ALL)

        rolledButton1.setOnClickListener(this)
        rolledButton2.setOnClickListener(this)
        rolledButton3.setOnClickListener(this)

//        rolledButton1.setOnClickListener {
//            rollDice(LEFT)
//        }
//        rolledButton2.setOnClickListener {
//            rollDice(RIGHT)
//        }
//        rolledButton3.setOnClickListener {
//            rollDice(ALL)
//        }

    }

    /////////////////////////////////////////////////////////////////////

    enum class RollType {LEFT, RIGHT, ALL}

    private fun rollDice(providedParameter : RollType){
        when (providedParameter){
            LEFT -> random("left")
            RIGHT -> random("right")
            ALL -> random("right")
        }
    }

/////////////////////////////////////////////////////////////////////

    private fun random(position : String){

        val dice = Dice(6)
        val varRoll = dice.roll()
        val drawableResources = when (varRoll){
            1-> R.drawable.dice_1
            2-> R.drawable.dice_2
            3-> R.drawable.dice_3
            4-> R.drawable.dice_4
            5-> R.drawable.dice_5
            else-> R.drawable.dice_6
        }

        when(position){
            "left"-> setImageAndDescription(diceImage1, drawableResources, description = varRoll.toString())
            "right"->setImageAndDescription(diceImage2, drawableResources, description = varRoll.toString())
        }

    }

/////////////////////////////////////////////////////////////////////

    private fun setImageAndDescription(diceImage: ImageView, drawableResources: Int, description : String) {
        diceImage.setImageResource(drawableResources)
        diceImage.contentDescription=description
    }

    override fun onClick(p0: View) {
        when (p0.id){
            R.id.randomB1 -> rollDice(LEFT)
            R.id.randomB2 -> rollDice(RIGHT)
            R.id.randomAll -> rollDice(ALL)
        }
    }

}

/////////////////////////////////////////////////////////////////////

class Dice(private val numSides: Int){
    fun roll() : Int{
        return (1..numSides).random()
    }
}