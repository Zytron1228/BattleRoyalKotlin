package com.example.battleroyale

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat

class MainActivity : AppCompatActivity() {

    private lateinit var p1hpBar: View
    private lateinit var e1hpBar: View
    private lateinit var e2hpBar: View
    private lateinit var p1hp: TextView
    private lateinit var e1hp: TextView
    private lateinit var e2hp: TextView
    private lateinit var fightLog: TextView
    private lateinit var attackBtn: Button
    private lateinit var defendBtn: Button
    private lateinit var counterBtn: Button
    private lateinit var dodgeBtn: Button
    private lateinit var restartBtn: Button
    private lateinit var attack1Btn: Button
    private lateinit var attack2Btn: Button
    private lateinit var attackMenu: LinearLayout

    private var health1: Int = 200 // player1
    private var health2: Int = 100 // ai1
    private var health3: Int = 100 // ai2
    private var hp1Color: Int = Color.GREEN
    private var hp2Color: Int = Color.GREEN
    private var hp3Color: Int = Color.GREEN
    private var playerDefending: Boolean = false
    private var enemy1Defending: Boolean = false
    private var enemy2Defending: Boolean = false
    private var playerDodging: Boolean = false
    private var enemy1Dodging: Boolean = false
    private var enemy2Dodging: Boolean = false
    private var playerCountering: Boolean = false
    private var enemy1Countering: Boolean = false
    private var enemy2Countering: Boolean = false
    private var playerDead: Boolean = false
    private var enemy1Dead: Boolean = false
    private var enemy2Dead: Boolean = false
    private var playerWon: Boolean = false
    private var fightLog1 = "has not made a move yet" // or make a string?
    private var fightLog2 = "has not made a move yet" // or make a string?
    private var fightLog3 = "has not made a move yet" // or make a string?

    private var toastShowed: Boolean = false
    private var toast2Showed: Boolean = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        p1hpBar = findViewById(R.id.p1hpBar)
        e1hpBar = findViewById(R.id.e1hpBar)
        e2hpBar = findViewById(R.id.e2hpBar)
        p1hp = findViewById(R.id.p1hpText)
        e1hp = findViewById(R.id.e1hpText)
        e2hp = findViewById(R.id.e2hpText)
        fightLog = findViewById(R.id.fightLog)
        attackMenu = findViewById(R.id.atkMnu)
        attack1Btn = findViewById(R.id.button1)
        attack2Btn = findViewById(R.id.button2)
        attackBtn = findViewById(R.id.buttonAt)
        defendBtn = findViewById(R.id.buttonDf)
        counterBtn = findViewById(R.id.buttonCt)
        dodgeBtn = findViewById(R.id.buttonDg)
        restartBtn = findViewById(R.id.restartButton)
        hpColorsUpdate()
        updateStrings()
        attackBtn.setOnClickListener {
            p1AttackChoose()
        }
        attack1Btn.setOnClickListener {
            if (!playerWon) {
                if (!playerDead) {
                    playerAttack1()
                    p1AttackChoose()
                    if (!enemy1Dead) {
                        ai1TakeTurn()
                    } else if (enemy1Dead) {
                        println("Enemy 1 cannot fight. 1 is dead")
                        if (!enemy2Dead) {
                            ai2TakeTurn()
                        } else if (enemy2Dead) {
                            println("Enemy 2 cannot fight. 2 is also dead")
                            println("No enemies can fight. 1 and 2 are dead.")
                            checkPlayerWins()
                        }
                    }
                    checkDie()
                } else if (playerDead) {
                    println("cannot attack, player is dead.")
                }
            } else if (playerWon) {
                println("cannot attack, all enemies are dead.")
            }
            updateStrings()
        }
        attack2Btn.setOnClickListener {
            if (!playerWon) {
                if (!playerDead) {
                    playerAttack2()
                    p1AttackChoose()
                    if (!enemy1Dead) {
                        ai1TakeTurn()
                    } else if (enemy1Dead) {
                        println("Enemy 1 cannot fight. 1 is dead")
                        if (!enemy2Dead) {
                            ai2TakeTurn()
                        } else if (enemy2Dead) {
                            println("Enemy 2 cannot fight. 2 is also dead")
                            println("No enemies can fight. 1 and 2 are dead.")
                            checkPlayerWins()
                        }
                    }
                    checkDie()
                } else if (playerDead) {
                    println("cannot attack, player is dead.")
                }
            } else if (playerWon) {
                println("cannot attack, all enemies are dead.")
            }
            updateStrings()
        }
                defendBtn.setOnClickListener {
                    if (!playerWon) {
                        if (!playerDead)  {
                            playerDefend()
                            hp1ColorUpdate()
                            if (!enemy1Dead) {
                                ai1TakeTurn()
                            } else if (enemy1Dead) {
                                println("Enemy 1 cannot fight. 1 is dead")
                                if (!enemy2Dead) {
                                    ai2TakeTurn() }
                                else if (enemy2Dead) {
                                    println("Enemy 2 cannot fight. 2 is also dead")
                                    println("No enemies can fight. 1 and 2 are dead.")
                                    checkPlayerWins()
                                }
                            }
                        }
                        else if (playerDead) {
                            println("cannot defend, player is dead.")
                        }
                    }
                    else if (playerWon) {
                        println("cannot defend, all enemies are dead.")
                    }
                }
                counterBtn.setOnClickListener {
                    if (!playerWon)  {
                        if (!playerDead) {
                            playerCounter()
                            hp1ColorUpdate()
                            if (!enemy1Dead) {
                                ai1TakeTurn()
                            } else if (enemy1Dead) {
                                println("Enemy 1 cannot fight. 1 is dead")
                                if (!enemy2Dead) {
                                    ai2TakeTurn() }
                                else if (enemy2Dead) {
                                    println("Enemy 2 cannot fight. 2 is also dead")
                                    println("No enemies can fight. 1 and 2 are dead.")
                                    checkPlayerWins()
                                }
                            }
                        }
                        else if (playerDead) {
                            println("cannot counter, player is dead.")
                        }
                    }
                    else if (playerWon) {
                        println("cannot counter, all enemies are dead.")
                    }
                }
                dodgeBtn.setOnClickListener {
                    if (!playerWon) {
                        if (!playerDead) {
                            playerDodge()
                            hp1ColorUpdate()
                            if (!enemy1Dead) {
                                ai1TakeTurn()
                            } else if (enemy1Dead) {
                                println("Enemy 1 cannot fight. 1 is dead")
                                if (!enemy2Dead) {
                                    ai2TakeTurn() }
                                else if (enemy2Dead) {
                                    println("Enemy 2 cannot fight. 2 is also dead")
                                    println("No enemies can fight. 1 and 2 are dead.")
                                    checkPlayerWins()
                                }
                            }
                        }
                        else if (playerDead) {
                            println("cannot dodge, player is dead.")
                        }
                    }
                    else if (playerWon) {
                        println("cannot dodge, all enemies are dead.")
                    }
                }
                restartBtn.setOnClickListener {
                    restart()
                }
            }

            private fun updateStrings() {
                p1hp.text = getString(R.string.playerHp, (health1 / 2).toString())
                e1hp.text = getString(R.string.enemy1Hp, health2.toString())
                e2hp.text = getString(R.string.enemy2Hp, health3.toString())
                fightLog.text = getString(R.string.fightLogText, fightLog1.toString(), fightLog2.toString(), fightLog3.toString())

            }

            @RequiresApi(Build.VERSION_CODES.M)
            private fun p1AttackChoose() {
                if(attackMenu.visibility == View.GONE) {
                    if (!playerWon && !playerDead) attackMenu.visibility = View.VISIBLE
                }
                else if(attackMenu.visibility == View.VISIBLE) attackMenu.visibility = View.GONE
            }

            @RequiresApi(Build.VERSION_CODES.M)
            private fun playerAttack1() {
                if(enemy1Defending) health2 -= 15
                else if(enemy1Dodging) health2 -= 0
                else if(enemy1Countering) {health2 -= 0
                    health1 -= 5}
                else if(!enemy1Defending) health2 -= 25

                fightLog1 = "attacks Enemy 1"
                hpColorsUpdate()
                checkDie()
                println("player attacks enemy 1")
                checkPlayerWins()
            }

            @RequiresApi(Build.VERSION_CODES.M)
            private fun playerAttack2() {
                if(enemy2Defending) health3 -= 15
                else if(enemy2Dodging) health3 -= 0
                else if(enemy2Countering) {health3 -= 0
                    health1 -= 5}
                else if(!enemy2Defending) health3 -= 25

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
                if (playerDead) {
                if(!toastShowed) {
                        Toast.makeText(
                            this,
                            "You Lost. Tap \"RESTART\" to Play again.",
                            Toast.LENGTH_SHORT
                        ).show()
                        toastShowed = true
                    attackBtn.alpha = 0.5f
                    defendBtn.alpha = 0.5f
                    counterBtn.alpha = 0.5f
                    dodgeBtn.alpha = 0.5f
                    }
                }
//
            }

            private fun checkDie1() {
                if (health1 <= 0) {
                    println("Player died.")
                    hp1Color = Color.DKGRAY
                    health1 = 0
                    playerDead = true
                    fightLog1 = "is dead"
                }
            }

            private fun checkDie2() {
                if (health2 <= 0) {
                    println("Enemy 1 died.")
                    hp2Color = Color.DKGRAY
                    health2 = 0
                    enemy1Dead = true
                    fightLog2 = "is dead"
                }
            }

            private fun checkDie3() {
                if (health3 <= 0) {
                    println("Enemy 2 died.")
                    hp3Color = Color.DKGRAY
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

            @RequiresApi(Build.VERSION_CODES.M)
            private fun ai1TakeTurn() {
                playerTurnsEnd()
                if(!playerWon) {
                    when ((1..8).random()) {
                        1 -> {
                            println("Enemy 1 defends.")
                            enemy1Defend()
                        }
                        2 -> {
                            println("Enemy 1 counters.")
                            enemy1Counter()
                        }
                        3 -> {
                            println("Enemy 1 dodges.")
                            enemy1Dodge()
                        }
                        in 4..8 -> {
                            println("Enemy 1 attacks Player.")
                            enemy1Attack()
                        }
                    }
                    checkDie()
                    checkPlayerWins()
                    if(enemy2Dead) {
                        println("Enemy 2 cannot fight. 2 is dead.")
                        aiTurnsEnd()
                    } else if(!enemy2Dead) ai2TakeTurn()
                } else println("Enemies cannot attack. Player has won.")
            }

            @RequiresApi(Build.VERSION_CODES.M)
            private fun enemy1Attack() {
                if (playerDefending) health1 -= 19
                else if (playerDodging) health1 -= 0
                else if (playerCountering) {
                    health2 -= 5
                } else if (!playerDefending) health1 -= 15
                fightLog2 = "attacks player"
                hpColorsUpdate()
                checkDie()
            }

            private fun enemy1Defend() {
                enemy1Defending = true
                when((1..5).random()) {
                    1 -> fightLog2 = "might dodge"
                    2 -> fightLog2 = "might counter"
                    3 -> fightLog2 = "leaves no indication of what they may do"
                    4, 5 -> fightLog2 = "might defend"
                }
            }

            private fun enemy1Counter() {
                when((1..3).random()) {
                    3 -> {
                        println("Counter failed")
                        when ((1..5).random()) {
                            1 -> fightLog2 = "might defend"
                            2 -> fightLog2 = "might dodge"
                            3 -> fightLog2 = "leaves no indication of what they may do"
                            4, 5 -> fightLog2 = "might counter"
                        }
                        enemy1Countering = false
                    }
                    1,2 -> {
                        println("Enemy 1 counters successfully.")
                        when ((1..5).random()) {
                            1 -> fightLog2 = "might defend"
                            2 -> fightLog2 = "might dodge"
                            3 -> fightLog2 = "leaves no indication of what they may do"
                            4, 5 -> fightLog2 = "might counter"
                        }
                        enemy1Countering = true
                    }
                }
            }

            private fun enemy1Dodge() {
                when((1..5).random()) {
                    1 -> {
                        println("Dodge failed")
                        when ((1..5).random()) {
                            1 -> fightLog2 = "might defend"
                            2 -> fightLog2 = "might counter"
                            3 -> fightLog2 = "leaves no indication of what they may do"
                            4, 5 -> fightLog2 = "might dodge"
                        }
                        enemy1Dodging = false
                    }
                    in 2..5 -> {
                        println("Enemy 1 dodges successfully.")
                        when ((1..5).random()) {
                            1 -> fightLog2 = "might defend"
                            2 -> fightLog2 = "might counter"
                            3 -> fightLog2 = "leaves no indication of what they may do"
                            4, 5 -> fightLog2 = "might dodge"
                        }
                        enemy1Dodging = true
                    }
                }
            }

            // ~~~~~~~~~~~ai~2~~~~~~~~~~~~~~

            @RequiresApi(Build.VERSION_CODES.M)
            private fun ai2TakeTurn() {
                checkPlayerWins()
                if(!playerWon) {
                    when((1..8).random()) {
                        1 -> {
                            println("Enemy 2 attacks Player.")
                            enemy2Attack()
                            println("If you see this the app is running the 254th line of code.")
                        }
                        2 -> {
                            println("Enemy 2 Defends.")
                            enemy2Defend()
                        }
                        3 -> {
                            println("Enemy 2 Counters.")
                            enemy2Counter()
                        }
                        4 -> {
                            println("Enemy 2 Dodges.")
                            enemy2Dodge()
                        }
                        in 5..8 -> {
                            println("Enemy 2 attacks Player.")
                            enemy2Attack()
                        }
                    }
                    checkDie()
                    checkPlayerWins()
                } else if(playerWon) println("Enemy 2 cannot attack. Player has won.")
                aiTurnsEnd()
            }

           @RequiresApi(Build.VERSION_CODES.M)
            private fun enemy2Attack() {
                if (playerDefending) health1 -= 10
                else if (playerDodging) health1 -= 0
                else if (playerCountering) {
                    health3 -= 5
                } else if (!playerDefending) health1 -= 15
                fightLog3 = "attacks player"
                hpColorsUpdate()
                checkDie()
            }

            private fun enemy2Defend() {
                enemy2Defending = true
                when((1..5).random()) {
                    1 -> fightLog3 = "might dodge"
                    2 -> fightLog3 = "might counter"
                    3 -> fightLog3 = "leaves no indication of what they may do"
                    4, 5 -> fightLog3 = "might defend"
                }
            }

            private fun enemy2Counter() {
                when((1..3).random()) {
                    3 -> {
                        println("Counter failed")
                        when ((1..5).random()) {
                            1 -> fightLog3 = "might defend"
                            2 -> fightLog3 = "might dodge"
                            3 -> fightLog3 = "leaves no indication of what they may do"
                            4, 5 -> fightLog3 = "might counter"
                        }
                        enemy2Countering = false
                    }
                    1,2 -> {
                        println("Enemy 2 counters successfully.")
                        when ((1..5).random()) {
                            1 -> fightLog3 = "might defend"
                            2 -> fightLog3 = "might dodge"
                            3 -> fightLog3 = "leaves no indication of what they may do"
                            4, 5 -> fightLog3 = "might counter"
                        }
                        enemy2Countering = true
                    }
                }
            }

            private fun enemy2Dodge() {
                when((1..5).random()) {
                    1 -> {
                        println("Dodge failed")
                        when ((1..5).random()) {
                            1 -> fightLog3 = "might defend"
                            2 -> fightLog3 = "might counter"
                            3 -> fightLog3 = "leaves no indication of what they may do"
                            4, 5 -> fightLog3 = "might dodge"
                        }
                        enemy2Dodging = false
                    }
                    in 2..5 -> {
                        println("Enemy 2 dodges successfully.")
                        when ((1..5).random()) {
                            1 -> fightLog3 = "might defend"
                            2 -> fightLog3 = "might counter"
                            3 -> fightLog3 = "leaves no indication of what they may do"
                            4, 5 -> fightLog3 = "might dodge"
                        }
                        enemy2Dodging = true
                    }
                }
            }

            // ~~~~~~~

            private fun aiTurnsEnd() {
                playerDefending = false
                playerDodging = false
                playerCountering = false
                checkDie()
                updateStrings()
            }

            private fun playerTurnsEnd() {
                enemy1Defending = false
                enemy1Dodging = false
                enemy1Countering = false
                enemy2Defending = false
                enemy2Dodging = false
                enemy2Countering = false
                checkDie()
                updateStrings()
            }

            @RequiresApi(Build.VERSION_CODES.M)
            private fun hp1ColorUpdate() {
                when(health1) {
                    in 151..200 -> hp1Color = Color.GREEN
                    in 101..150 -> hp1Color = Color.YELLOW
                    in 51..100 -> hp1Color = getColor(R.color.orange1)
                    in 1..50 -> hp1Color = Color.RED
                    0 -> hp1Color = Color.DKGRAY
                }
                if(health1 > 200) hp1Color = Color.CYAN
                if(health1 <= 0) {
                    hp1Color = Color.DKGRAY
                    checkDie1()
                }
                var buttonDrawable: Drawable? = p1hpBar.getBackground()
                buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
                DrawableCompat.setTint(buttonDrawable, hp1Color)
                p1hpBar.setBackground(buttonDrawable)
            }

            @RequiresApi(Build.VERSION_CODES.M)
            private fun hp2ColorUpdate() {
                when(health2) {
                    in 76..100 -> hp2Color = Color.GREEN
                    in 51..75 -> hp2Color = Color.YELLOW
                    in 26..50 -> hp2Color = getColor(R.color.orange1)
                    in 1..25 -> hp2Color = Color.RED
                    0 -> hp2Color = Color.DKGRAY
                }
                if(health2 > 100) hp2Color = Color.CYAN
                if(health2 <= 0) {
                    hp2Color = Color.DKGRAY
                    checkDie2()
                }
                var buttonDrawable: Drawable? = e1hpBar.getBackground()
                buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
                DrawableCompat.setTint(buttonDrawable, hp2Color)
                e1hpBar.setBackground(buttonDrawable)
            }

            @RequiresApi(Build.VERSION_CODES.M)
            private fun hp3ColorUpdate() {
                when(health3) {
                    in 76..100 -> hp3Color = Color.GREEN
                    in 51..75 -> hp3Color = Color.YELLOW
                    in 26..50 -> hp3Color = getColor(R.color.orange1)
                    in 1..25 -> hp3Color = Color.RED
                    0 -> hp3Color = Color.DKGRAY
                }
                if(health3 > 100) hp3Color = Color.CYAN
                if(health3 <= 0) {
                    hp3Color = Color.DKGRAY
                    checkDie3()
                }
                var buttonDrawable: Drawable? = e2hpBar.getBackground()
                buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
                DrawableCompat.setTint(buttonDrawable, hp3Color)
                e2hpBar.setBackground(buttonDrawable)
            }


            @RequiresApi(Build.VERSION_CODES.M)
            private fun hpColorsUpdate() {
                hp1ColorUpdate()
                hp2ColorUpdate()
                hp3ColorUpdate()
            }

            private fun checkPlayerWins() {
                if (enemy1Dead) {
                    if (enemy2Dead) {
                        println("Player wins!")
                        playerWon = true
                        if(!toast2Showed) {
                            Toast.makeText(this, "You Won! Tap \"RESTART\" to Play again.", Toast.LENGTH_SHORT).show()
                        toast2Showed = true
                            attackBtn.alpha = 0.5f
                            defendBtn.alpha = 0.5f
                            counterBtn.alpha = 0.5f
                            dodgeBtn.alpha = 0.5f
                        }
                    }
                }
            }

            @RequiresApi(Build.VERSION_CODES.M)
            private fun restart() {
                playerDead = false
                enemy1Dead = false
                enemy2Dead = false
                health1 = 200
                health2 = 100
                health3 = 100
                hpColorsUpdate()
                playerDefending = false
                enemy1Defending = false
                enemy2Defending = false
                playerCountering = false
                enemy1Countering = false
                enemy2Countering = false
                playerDodging = false
                enemy1Dodging = false
                enemy2Dodging = false
                playerWon = false
                fightLog1 = "has not made a move yet"
                fightLog2 = "has not made a move yet"
                fightLog3 = "has not made a move yet"
                println("Restarting Game...")
                updateStrings()
                toastShowed = false
                toast2Showed = false
                attackBtn.alpha = 1f
                defendBtn.alpha = 1f
                counterBtn.alpha = 1f
                dodgeBtn.alpha = 1f
            }


//    private fun checkWinAAA() {
//        if(playerWon) {//toast saying press restart to play again. }
//    }

}