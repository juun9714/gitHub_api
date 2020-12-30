package com.example.github_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.github_api.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.gson.GsonConverterFactory.*

class MainActivity : AppCompatActivity() {
    val arr = ArrayList<String>()
    private lateinit var binding:ActivityMainBinding
    var BASE_URL="https://api.github.com/search/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        var retrofit : Retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var api = retrofit.create(API::class.java)

        var call = api.getUsers()

        call.enqueue(object : Callback<UserData>{
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                Log.e("MAINACTIVITY",response.body()?.items.toString())
                //호출이 성공했을 때, 실행
                for(str in response?.body()?.items!!){
                    Log.e("MAINACTIVITY",str.login)
                    arr.add(str.login)
                }

                val list_adapter = MainListAdapter(this@MainActivity, arr)
                findViewById<ListView>(R.id.listview_api).adapter = list_adapter

//                findViewById<ListView>(R.id.listview_api).onItemClickListener{
//                    Toast.makeText(this@MainActivity,"CLICK",Toast.LENGTH_LONG).show()
//                }
            }

            override fun onFailure(call: Call<UserData>, t: Throwable) {
                //호출이 실패했을 때, 실행
            }


        })
//        깃헙 사용자의 정보가 담겨있는 json 파일을 받아올 것임
//                --> 받아오기 위한 그릇(data type)을 미리 준비해놓아야 함


        findViewById<ListView>(R.id.listview_api).setOnItemClickListener{parent,view,position,id->
            Toast.makeText(this@MainActivity,arr.get(position),Toast.LENGTH_LONG).show()
        }
    }
}