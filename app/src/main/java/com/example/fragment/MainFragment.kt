package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fragment.databinding.FragmentMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainFragment : Fragment() {

    private val user = Firebase.auth.currentUser
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        getUserMail()

var activity = activity as MainActivity
        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(LoginFragment())
        fragmentTransaction.commit();
        fragmentManager.popBackStack();



        return binding.root
    }

    private fun getUserMail(){
       binding.textView.text = "Hoş geldiniz ${user!!.email.toString()}"
    }

    



}