package com.example.basiccalculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.function.UnaryOperator

class MainActivity : AppCompatActivity() {
    var currentnumber:String?=null
    var Result:TextView?=null
    var Fresult:TextView?=null
    var CurrentOperator:String?=null
    var oldnumber:Double?=0.0
    var CalculateResult:Double?=0.0
    var calculateString:String?=null
    var calculateString2:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentnumber=""
        CurrentOperator=""
        calculateString=""
        calculateString2=""
        Result= findViewById(R.id.result)
        Fresult=findViewById(R.id.FinalResult)
    }

    fun buttonTapped(view: View){
        when(view.id){
            R.id.zero->{numberclicked(0)}
            R.id.one->{numberclicked(1)}
            R.id.two->{numberclicked(2)}
            R.id.three->{numberclicked(3)}
            R.id.four->{numberclicked(4)}
            R.id.five->{numberclicked(5)}
            R.id.six->{numberclicked(6)}
            R.id.seven->{numberclicked(7)}
            R.id.eight->{numberclicked(8)}
            R.id.nine->{numberclicked(9)}
            R.id.plus->{operatorClicked("+")}
            R.id.minis->{operatorClicked("-")}
            R.id.mul->{operatorClicked("*")}
            R.id.div->{operatorClicked("/")}
            R.id.modulo->{operatorClicked("%")}
            R.id.equal->{displayresult()}
            R.id.ac->{
                clearscreen()
            }
            R.id.dot->{
                currentnumber=currentnumber+"."
                calculateString+="."
                Result?.setText((calculateString))
            }

        }
    }

    fun numberclicked(number: Int){

        currentnumber+=number
        if (CurrentOperator!=null && oldnumber!=0.0){
            Result?.setText(calculateString+currentnumber)
            return
        }
        calculateString=currentnumber
        calculateString2=currentnumber
        Result?.setText(currentnumber)

    }
    fun operatorClicked(operator: String){
        if (currentnumber!=null){
            oldnumber=currentnumber?.toDouble()
            currentnumber=""
        }
        when(operator){
            "+"->{CurrentOperator="+"}
            "-"->{CurrentOperator="-"}
            "*"->{CurrentOperator="*"}
            "/"->{CurrentOperator="/"}
            "%"->{CurrentOperator="%"}
        }
        calculateString+=CurrentOperator
        calculateString2+=CurrentOperator
        Result?.setText(calculateString2)

    }

    fun displayresult(){
        try {
            if (oldnumber!=null && currentnumber!=null){
                calculateString=""
                calculateString2+=currentnumber
                when(CurrentOperator){
                    "+"->{CalculateResult = oldnumber!!.toDouble() + currentnumber!!.toDouble()}
                    "-"->{CalculateResult = oldnumber!!.toDouble() - currentnumber!!.toDouble()}
                    "*"->{CalculateResult = oldnumber!!.toDouble() * currentnumber!!.toDouble()}
                    "/"->{CalculateResult = oldnumber!!.toDouble() / currentnumber!!.toDouble()}
                    "%"->{CalculateResult = oldnumber!!.toDouble() % currentnumber!!.toDouble()}
                }
            }
            currentnumber=CalculateResult.toString()
            calculateString=currentnumber
            Fresult?.setText(CalculateResult.toString())
            Result?.setText(calculateString2.toString() )
            CurrentOperator=""

        }catch (e:Exception){
            e.printStackTrace()
        }

    }
    fun clearscreen(){
        Fresult?.setText("")
        Result?.setText("")
        CalculateResult=0.0
        oldnumber=0.0
        currentnumber=""
        CurrentOperator=""
        calculateString=""
        calculateString2=""

    }
}