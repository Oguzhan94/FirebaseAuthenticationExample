package com.example.fragment




import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.fragment.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)

        auth = Firebase.auth
        init()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun init() {
        binding.buttonLogin.setOnClickListener {
            signIn()
        }
        binding.buttonRegister.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun signIn() {
        var email: String = binding.editTextTextEmailAddress.text.toString()
        var password: String = binding.editTextTextPassword.text.toString()
        if(email.isEmpty() || password.isEmpty()){

            Toast.makeText(binding.root.context,"Email veya şifre alanı boş olamaz",Toast.LENGTH_SHORT).show()

        }
        else{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(binding.root.context, "Başarılı", Toast.LENGTH_SHORT)
                            .show()
                        openMainFragment()
                    }
                } .addOnFailureListener {
                    Toast.makeText(binding.root.context,"Giriş yapılamadı",Toast.LENGTH_SHORT).show()
                }
        }

    }



    private fun openMainFragment() {
        Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_mainFragment)
    }

}