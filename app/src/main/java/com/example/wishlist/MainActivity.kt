package com.example.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val wishlist = mutableListOf<WishlistItem>()
    private lateinit var adapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = WishlistAdapter(wishlist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // "Add Item" Button
        val btnAddItem: Button = findViewById(R.id.btnAddItem)
        btnAddItem.setOnClickListener {
            showAddWishlistDialog()
        }
    }

    // Did not like the example method where you type into text boxes on the screen.
    // Took a bit of time to figure out, but now we have a proper separate box for entering information.
    private fun showAddWishlistDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.popup, null)

        val etName = dialogView.findViewById<EditText>(R.id.etItemName)
        val etPrice = dialogView.findViewById<EditText>(R.id.etItemPrice)
        val etStore = dialogView.findViewById<EditText>(R.id.etItemStore)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Submit", null)
            .setNegativeButton("Cancel") { d, _ -> d.dismiss() }
            .create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val name = etName.text.toString().trim()
            val price = etPrice.text.toString().trim()
            val store = etStore.text.toString().trim()

            if (name.isEmpty() || price.isEmpty() || store.isEmpty()) {
                Toast.makeText(this, "Please fill in all three fields", Toast.LENGTH_SHORT).show()
            } else {
                // Automatic dollar sign
                val formattedPrice = if (price.startsWith("$")) price else "$$price"

                val newItem = WishlistItem(name, formattedPrice, store)
                adapter.addItem(newItem)

                dialog.dismiss()
            }
        }
    }
}