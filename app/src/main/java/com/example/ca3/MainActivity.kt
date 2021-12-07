package com.example.ca3

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

var contactsArrayList = ArrayList<contacts>()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listview = findViewById<ListView>(R.id.listview)
        val tv = findViewById<TextView>(R.id.default_text)

        /***
         * FloatingButton with Custom Dialog
         */
        val float = findViewById<FloatingActionButton>(R.id.floating)
        float.setOnClickListener {
            tv.visibility = View.GONE
            val DialogView = layoutInflater.inflate(R.layout.customalert, null)

            val builder = AlertDialog.Builder(this)
                .setView(DialogView)
                .setTitle(R.string.contact_details)
                .setPositiveButton(R.string.ok_tv) { _, _ ->
                    /**
                     * this is how we can take data from xml from Custom dialog
                     * */
                    val name = DialogView.findViewById<EditText>(R.id.nameEdit)
                    val phone = DialogView.findViewById<EditText>(R.id.phoneEdit)
//                  add elements to our list and call function add contacts
//                    contactsArrayList = ArrayList()
                    contactsArrayList.add(
                        contacts(
                            name.text.toString(),
                            phone.text.toString()
                        )
                    )
//                  listview adapter
                    listview.adapter = contactsAdapter(this, contactsArrayList)
                    registerForContextMenu(listview)

                }
            builder.show()
        }

        /**
         * Listview listener and intent
         * */
        listview.setOnItemClickListener { adapterview, view, pos, id ->
            val intent = Intent(this, ContactsScreen::class.java)
            val bundle = Bundle()
            var firstChar: Char = contactsArrayList[pos].name.get(0)
            var fullName: String = contactsArrayList[pos].name

            bundle.putString("firstChar", firstChar.toString())
            bundle.putString("Name", fullName)
            intent.putExtra("contacts", bundle)
            startActivity(intent)
        }

    }

    /**
     * Option menu
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activitymenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.call_history -> {
                Toast.makeText(applicationContext, "call History Item", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.settings -> {
                Toast.makeText(applicationContext, "Settings Item", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.help -> {
                Toast.makeText(applicationContext, "Feedback Item", Toast.LENGTH_SHORT).show()
                true
            }
            else ->
                true
        }
    }

    /**
     * Context Menu
     * */

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
//        object creation
        val inflater = menuInflater
        inflater.inflate(R.menu.contextmenu, menu)
//        menu?.setHeaderTitle()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.spam) {
            Toast.makeText(applicationContext, "Marked Spam", Toast.LENGTH_LONG).show()
            return true
        } else if (item.title == "Share") {
            Toast.makeText(applicationContext, "Share mode", Toast.LENGTH_LONG).show()
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "123456789")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

            return true
        } else if (item.title == "Edit") {
            Toast.makeText(applicationContext, "Edit mode", Toast.LENGTH_LONG).show()
            return true
        } else if (item.title == "Block") {
            Toast.makeText(applicationContext, "Block mode", Toast.LENGTH_LONG).show()
            return true
        } else if (item.title == "Sms") {
            Toast.makeText(applicationContext, "Sms mode", Toast.LENGTH_LONG).show()
            return true
        } else
            return false
    }

}

