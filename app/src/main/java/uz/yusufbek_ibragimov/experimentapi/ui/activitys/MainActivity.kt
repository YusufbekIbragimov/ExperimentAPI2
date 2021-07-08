package uz.yusufbek_ibragimov.experimentapi.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.yusufbek_ibragimov.experimentapi.R
import uz.yusufbek_ibragimov.experimentapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}