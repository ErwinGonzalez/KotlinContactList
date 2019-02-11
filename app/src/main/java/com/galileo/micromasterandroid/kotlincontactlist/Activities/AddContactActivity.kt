package com.galileo.micromasterandroid.kotlincontactlist.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.galileo.micromasterandroid.kotlincontactlist.DataModels.Contact
import com.galileo.micromasterandroid.kotlincontactlist.R
import kotlinx.android.synthetic.main.activity_main.*

class AddContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact_activity)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_ok -> {
                val name_til = findViewById<TextInputLayout>(R.id.name_til)
                val name_tv = findViewById<TextInputEditText>(R.id.name_edit_text)
                val phone_til = findViewById<TextInputLayout>(R.id.phone_til)
                val phone_tv = findViewById<TextInputEditText>(R.id.phone_edit_text)
                var empty = false;
                if(TextUtils.isEmpty(name_tv.getText().toString())) {
                    name_til.setError("Name cannot be empty");
                    empty = true
                }
                if(TextUtils.isEmpty(phone_tv.getText().toString())) {
                    phone_til.setError("Phone cannot be empty");
                    empty = true
                }
                if(!empty){
                    val new_contact = Contact(null,
                        name_tv.text.toString(),
                        phone_tv.text.toString()
                    )
                    val bundle = Bundle()
                    bundle.putSerializable("NEW_CONTACT",new_contact)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("RESULT_BUNDLE",bundle)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
                return true;
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}