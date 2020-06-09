package ir.alirezanazari.vimeoapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.alirezanazari.vimeoapi.R
import ir.alirezanazari.vimeoapi.internal.Navigator
import ir.alirezanazari.vimeoapi.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigator.openSearch(supportFragmentManager)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1){
            val fragment = supportFragmentManager.findFragmentById(R.id.fragments_container)
            if(fragment is SearchFragment){
                if(fragment.onBackPressed()) finish()
                return
            }else {
                finish()
                return
            }
        }
        super.onBackPressed()
    }
}
