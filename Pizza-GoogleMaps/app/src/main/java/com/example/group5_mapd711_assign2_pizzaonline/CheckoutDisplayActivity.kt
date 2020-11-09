package com.example.group5_mapd711_assign2_pizzaonline

//Group 5 - Assignment 3
//Student1: Abdeali Mody - Student ID: 301085484
//Student2: Juliana de Carvalho - Student ID: 301137060

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import com.example.group5_mapd711_assign2_pizzaonline.R

class CheckoutDisplayActivity : AppCompatActivity()

{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_display)

        //defining an intent object
        val intent =getIntent()

        // getting info from Checkout Activity
        val name = intent.getStringExtra("CustomersName")
        val typeofPizza = intent.getStringExtra("Type")
        val size = intent.getStringExtra("Size")
        val extraToppings = intent.getStringExtra("Toppings")
        val address = intent.getStringExtra("Address")
        val postalCode = intent.getStringExtra("Postal Code")

        //defining a reference object for all text view controls
        val cust_name = findViewById<TextView> (R.id.txtCustomerName)
        val typePizza = findViewById<TextView> (R.id.txtTOP)
        val sizePizza = findViewById<TextView> (R.id.txtSOP)
        val toppings  = findViewById<TextView> (R.id.txtToppings)
        val addr      = findViewById<TextView> (R.id.txtAddr)
        val postcode  = findViewById<TextView> (R.id.txtPostalCode)

        //assigning message value to textview control And I am also concatenating the above input with the below variable.
        cust_name.text   = "Customer's Name :" +name.toString()
        typePizza.text   = "Type of Pizza:" +typeofPizza.toString()
        sizePizza.text   = "Size of Pizza :" +size.toString()
        toppings.text    = "Toppings :" +extraToppings.toString()
        addr.text        = "Address :" +address.toString()
        postcode.text    = "Postal Code :" +postalCode.toString()
    }
}