package com.example.group5_mapd711_assign2_pizzaonline

//Group 5 - Assignment 3
//Student1: Abdeali Mody - Student ID: 301085484
//Student2: Juliana de Carvalho - Student ID: 301137060

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.group5_mapd711_assign2_pizzaonline.R
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_main.*
import org.intellij.lang.annotations.RegExp

class CheckoutActivity1 : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // Creating a function with listener.
        buttonOrder.setOnClickListener{

            var validInputs = true
            var msg = ""

            //Getting a reference to an inflated widget by calling the following Activity method: public View findViewById(int id)
            val customer_Name = findViewById<EditText>(R.id.txtCustomerName)
            val address = findViewById<EditText>(R.id.txtAddr)
            val postal_code = findViewById<EditText>(R.id.txtPostalCode)
            val mobile_num = findViewById<EditText>(R.id.txtMoileNumber)
            val card_Type = findViewById<View>(R.id.txtCardType) as Spinner
            val card_Num = findViewById<EditText>(R.id.txtCCNum)
            val expiry_date = findViewById<EditText>(R.id.txtExpiryDate)

            //Converting the above input into string assign to below variables
            val name = customer_Name.text.toString()
            val addr = address.text.toString()
            val postcode = postal_code.text.toString()
            val mobNum = mobile_num.text.toString()
            val cType = card_Type.selectedView as TextView
            val cNum = card_Num.text.toString()
            val exp_Date = expiry_date.text.toString()

            //validations
            if (name == ""){
                validInputs= false
                msg = "Please Enter Your Name"
            }
            if (addr == "") {
                validInputs= false
                msg = "Please Enter Your Address"
            }
            if (postcode.length < 6) {
                    validInputs= false
                    msg = "Please Enter a valid Postal Code"
                }
            if (checkLuhn(cNum) == false) {
                validInputs = false
                msg = "Please Enter a Valid Card Number"
            }
            if (mobNum.length != 10) {
                validInputs = false
                msg ="Please Enter a Valid Mobile Number"
            }
            if (exp_Date.length > 7) {
                validInputs= false
                msg = "Please Enter a valid Expiry Date"
            }

            val intent = intent

            //getting the info from PizzaDetails Activity
            val typeofPizza = intent.getStringExtra("typeofPizza")
            val size = intent.getStringExtra("size")
            val chkGreenPepper = intent.getStringExtra("chkGreenPepper")
            val chkBlackOlivies = intent.getStringExtra("chkBlackOlivies")
            val chkSmokedHam = intent.getStringExtra("chkSmokedHam")
            val chkSpanishOnions = intent.getStringExtra("chkSpanishOnions")
            val chkSpinach = intent.getStringExtra("chkSpinach")
            val chkExtraMozzarella = intent.getStringExtra("chkExtraMozzarella")

            //validating the extra toppings
            var toppings = ""

            //concatenating the extra toppings to show in the Checkout Display Activity
            if(chkGreenPepper == "yes")
                toppings += "Green Pepper,"

            if(chkBlackOlivies == "yes")
                toppings += "Black Olives,"

            if(chkSmokedHam == "yes")
                toppings += "Smoked Ham,"

            if(chkSpanishOnions == "yes")
                toppings += "Spanish Onions,"

            if(chkSpinach == "yes")
                toppings += "Spinach,"

            if(chkExtraMozzarella == "yes")
                toppings += "Extra Mozzarella"


            // Passing values using intent
            val i = Intent(this, CheckoutDisplayActivity::class.java)

            //storing value with key values pair of intent object using putExtra() method
            i.putExtra("CustomersName", name)
            i.putExtra("Type", typeofPizza)
            i.putExtra("Size", size)
            i.putExtra("Toppings", toppings)
            i.putExtra("Address", addr)
            i.putExtra("Postal Code", postcode)

            //after validating the inputs we are starting the Checkout Display Activity
            if(validInputs){
                startActivity(i)
            }
            else{ //showing to the user which input is not correct
                Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    //The Luhn algorithm, is a simple checksum formula used to validate a variety of identification numbers, such as credit card numbers.
    fun checkLuhn(txtCCNum:String):Boolean {
        var nDigits = txtCCNum.length
        var nSum = 0
        var isSecond = false
        for (i in nDigits - 1 downTo 0)
        {
            var d = txtCCNum.get(i) - '0'
            if (isSecond == true)
                d = d * 2

            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10
            nSum += d % 10
            isSecond = !isSecond
        }
        return (nSum % 10 == 0)
    }
}