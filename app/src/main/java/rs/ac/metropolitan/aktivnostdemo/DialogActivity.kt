package rs.ac.metropolitan.aktivnostdemo

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DialogActivity : AppCompatActivity() {
    var items =
        arrayOf<CharSequence>("FIT", "Fakultet za menadžment", "Fakultet digitalnih umetnosti")
    var selectedFaculty: String? = null
    var progressDialog: ProgressDialog? = null

    /**
     * Poziva se prvim definisanjem aktivnosti.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View?) {
        showDialog(0)
    }

    fun onClick2(v: View?) {
        //---prikazuje dijalog---
        val dialog = ProgressDialog.show(
            this, "Nešto se dešava.", "Sačekajte...", true
        )
        Thread {
            try {
                //---simulacija da nešto radi---
                Thread.sleep(5000)
                //---odjavljuje dijalog---
                dialog.dismiss()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }

    fun onClick3(v: View?) {
        showDialog(1)
        progressDialog!!.progress = 0
        Thread {
            for (i in 1..15) {
                try {
                    //---simulacija da nešto radi---
                    Thread.sleep(1000)
                    //---osvežavanje dijaloga---
                    progressDialog!!.incrementProgressBy((100 / 15))
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            progressDialog!!.dismiss()
        }.start()
    }

    override fun onCreateDialog(id: Int): Dialog? {
        when (id) {
            0 -> {
                val builder = AlertDialog.Builder(this)
                val inflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.dialog_layout, null)
                builder.setView(dialogView)
                    .setTitle("Odaberite fakultet")
                    .setPositiveButton(
                        "OK"
                    ) { dialog, whichButton ->
                        if (!selectedFaculty.isNullOrEmpty()) {
                            openWebPage(selectedFaculty!!)
                        }
                    }
                    .setNegativeButton(
                        "Cancel"
                    ) { dialog, whichButton ->
                        Toast.makeText(
                            baseContext,
                            "Cancel je kliknut!", Toast.LENGTH_SHORT
                        ).show()
                    }
                val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radioGroup)
                for (i in items.indices) {
                    val radioButton = RadioButton(this)
                    radioButton.text = items[i]
                    radioButton.setOnClickListener {
                        selectedFaculty = items[i].toString()
                    }
                    radioGroup.addView(radioButton)
                }
                return builder.create()
            }
        }
        return null
    }

    private fun openWebPage(faculty: String) {
        val url = when (faculty) {
            "FIT" -> "https://www.metropolitan.ac.rs/osnovne-studije/fakultet-informacionih-tehnologija/"
            "Fakultet za menadžment" -> "https://www.metropolitan.ac.rs/osnovne-studije/fakultet-za-menadzment/"
            "Fakultet digitalnih umetnosti" -> "https://www.metropolitan.ac.rs/fakultet-digitalnih-umetnosti-2/"
            else -> ""
        }
        if (url.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}