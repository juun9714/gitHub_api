package com.example.github_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.github_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

//        깃헙 사용자의 정보가 담겨있는 json 파일을 받아올 것임
//                --> 받아오기 위한 그릇(data type)을 미리 준비해놓아야 함



    }
}