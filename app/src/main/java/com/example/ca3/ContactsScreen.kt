package com.example.ca3

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ContactsScreen : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_screen)

        /***
         * Receive data from bundle
         */
        val firstChar: TextView = findViewById(R.id.profile)
        val displayName: TextView = findViewById(R.id.nametv)


        val i = intent
        val b: Bundle? = i.getBundleExtra("contacts")
        val fullName = b?.getString("Name")
        val firstChar_ = b?.getString("firstChar")


        firstChar.setText(firstChar_.toString())
        displayName.setText(fullName.toString())


        /**
         * pop up menu
         * */

        val deletimg = findViewById<LinearLayout>(R.id.delete_contact) as ImageView
        deletimg.setOnClickListener {
            Toast.makeText(applicationContext, "Working Fine", Toast.LENGTH_SHORT).show()
            val popup = PopupMenu(this, deletimg)
            popup.menuInflater.inflate(R.menu.deletecontactmenu, popup.menu)


            popup.setOnMenuItemClickListener {
                Toast.makeText(applicationContext, "item pressed : " + it.title, Toast.LENGTH_LONG)
                    .show()
                if (it.itemId == R.id.deleteP) {
                    /**
                     * alert dialog
                     * */
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(R.string.deleteFor)
                        .setMessage(R.string.areusure)
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_delete)

                    builder.setPositiveButton(R.string.proceed) { dialogInterface, which ->
                        Toast.makeText(applicationContext, "Deleting...", Toast.LENGTH_LONG).show()
                    }
                    builder.setNegativeButton(R.string.cancel) { dialogInterface, which ->
                        Toast.makeText(applicationContext, "Cancelling...", Toast.LENGTH_LONG)
                            .show()
                    }
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.show()
                }
                true
            }



            popup.show()
        }

    }
}