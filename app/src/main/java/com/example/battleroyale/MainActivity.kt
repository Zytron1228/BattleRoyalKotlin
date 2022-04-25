package com.example.battleroyale

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    var health1: Int = 100 // player1
    var health2: Int = 100 // ai1
    var health3: Int = 100 // ai2
    var playerDefending: Boolean = false
    var enemy1Defending: Boolean = false
    var enemy2Defending: Boolean = false
    var playerDodging: Boolean = false
    var enemy1Dodging: Boolean = false
    var enemy2Dodging: Boolean = false
    var playerCountering: Boolean = false
    var enemy1Countering: Boolean = false
    var enemy2Countering: Boolean = false
    var playerDead: Boolean = false
    var enemy1Dead: Boolean = false
    var enemy2Dead: Boolean = false
    var playerWon: Boolean = false
    public var fightLog1 = "has not made a move yet" // or make a string?
    public var fightLog2 = "has not made a move yet" // or make a string?
    public var fightLog3 = "has not made a move yet" // or make a string?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

    }

    private fun playerAttack1() {
        if(enemy1Defending) health2 -= 10
        else if(enemy1Dodging) health2 -= 0
        else if(enemy1Countering) {health2 -= 0
            health1 -= 5}
        else if(!enemy1Defending) health2 -= 15

        fightLog1 = "attacks Enemy 1"
        hpColorsUpdate()
        checkDie()
        println("player attacks enemy 1")
        checkPlayerWins()
    }

    private fun hpColorsUpdate() {
        TODO("Not yet translated")//also update text maybe?
    }

    private fun playerAttack2() {
        if(enemy2Defending) health3 -= 10
        else if(enemy2Dodging) health3 -= 0
        else if(enemy2Countering) {health3 -= 0
            health1 -= 5}
        else if(!enemy2Defending) health3 -= 15

        fightLog1 = "attacks Enemy 2"
        hpColorsUpdate()
        checkDie()
        println("player attacks enemy 2")
        checkPlayerWins()
    }

    private fun checkDie() {
        checkDie1()
        checkDie2()
        checkDie3()
        checkPlayerWins()
    }

    private fun checkDie1() {
        TODO("Not yet translated")
    }

    private fun checkDie2() {
        TODO("Not yet translated")
    }

    private fun checkDie3() {
        if (health3 <= 0) {
        println("Enemy 2 died.")
        hp3Color = getColor(R.color.black)
        health3 = 0
        enemy2Dead = true
        fightLog3 = "is dead"
    }
    }

    private fun playerDefend() {
        playerDefending = true
        println("player defends")
        checkPlayerWins()
        fightLog1 = "defends"
    }

    private fun playerCounter() {
        println("player counters")
        when((1..3).random()) {
            1 -> {
                println("counter failed")
                playerCountering = false
                fightLog1 = "counters, but fails"
            }
            2, 3 -> {
                println("counter success")
                playerCountering = true
                fightLog1 = "counters"
            }
        }
        checkPlayerWins()
    }

    private fun checkPlayerWins() {
        TODO("Not translated yet")
    }

    private fun playerDodge() {
        println("player dodges")
        when((1..5).random()) {
            1 -> {
                println("Dodge failed")
                fightLog1 = "dodges, but fails"
            }
            in (2..5) -> {
                println("player dodges successfully")
                playerDodging = true
                fightLog1 = "dodges"
            }
        }
        checkPlayerWins()
    }

//    private fun checkWinAAA() {
//        if(playerWon) {//toast saying press restart to play again. }
//    }
}