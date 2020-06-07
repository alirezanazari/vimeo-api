package ir.alirezanazari.vimeoapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.internal.Navigator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigator.openSearch(supportFragmentManager)
    }
}
