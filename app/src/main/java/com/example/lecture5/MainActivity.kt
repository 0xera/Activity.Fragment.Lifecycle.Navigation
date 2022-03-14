package com.example.lecture5

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var inputText: String = ""

    //    findViewById<TextView>(R.id.text).text = inputText
//    findViewById<EditText>(R.id.edit_text).addTextChangedListener(
//    onTextChanged = { charSeq, _, _, _ ->
//        inputText = charSeq.toString()
//    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val savedText = savedInstanceState?.getString("SampleKey")
    }


    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putString("SampleKey", "SampleValue")
        super.onSaveInstanceState(outState)
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        val text = persistentState?.getString("PersistKey")
        super.onCreate(savedInstanceState, persistentState)
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outPersistentState.putString("PersistKey", "PersistValue")
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        log("onConfigurationChanged: $newConfig")
    }
}


