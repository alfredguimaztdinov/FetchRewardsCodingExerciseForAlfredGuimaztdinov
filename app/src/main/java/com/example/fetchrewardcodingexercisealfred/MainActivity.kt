package com.example.fetchrewardcodingexercisealfred

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardcodingexercisealfred.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private lateinit var fetchAdapter: FetchAdapter



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        lifecycleScope.launchWhenCreated {
            binding.pbBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getId()
            } catch (e: IOException){
                Log.e(TAG,"IOException, no internet connection")
                binding.pbBar.isVisible = false
                return@launchWhenCreated
            } catch (e:HttpException){
                Log.e(TAG,"HTTP exception, no response")
                binding.pbBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null){
                fetchAdapter.items = response.body()!!
            } else{
                Log.e(TAG, "Response not successful")
            }
            binding.pbBar.isVisible = false

        }


    }
 
    private fun setupRecyclerView() = binding.rvListId.apply {
        fetchAdapter = FetchAdapter()
        adapter = fetchAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)

    }





        }



