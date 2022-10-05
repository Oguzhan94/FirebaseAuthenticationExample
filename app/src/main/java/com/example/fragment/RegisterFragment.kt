package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.fragment.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        auth = Firebase.auth
        init()

        return binding.root
    }

    private fun init() {
        binding.button.setOnClickListener {
            register()
        }
    }

    private fun register() {

        val email: String = binding.editTextTextEmailAddress2.text.toString()
        val password: String = binding.editTextTextPassword2.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                binding.root.context,
                "Tüm alanları doldurunuz",
                Toast.LENGTH_SHORT
            )
                .show()
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity()) { task ->
                    if (task.isSuccessful) {
                        // Register success, update UI with the signed-in user's information
                        Toast.makeText(
                            binding.root.context.applicationContext,
                            "Başarılı",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        openMainFragment()

                    } else {
                        // If register in fails, display a message to the user.
                        Toast.makeText(
                            binding.root.context.applicationContext,
                            "Başarısız",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                }
        }
    }

    private fun openMainFragment() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_registerFragment_to_mainFragment)

    }

}