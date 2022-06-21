package com.example.capfinal.ui.register


import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.capfinal.App
import com.example.capfinal.core.base.BaseFragment
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kg.geektech.finalprojectcustomcap.R.color.custom_yellow
import kg.geektech.finalprojectcustomcap.databinding.FragmentRegisterBinding
import java.util.*


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var callbackManager: CallbackManager

    override fun inflateVB(inflater: LayoutInflater): FragmentRegisterBinding =
        FragmentRegisterBinding.inflate(layoutInflater)

    override fun initView() {
        mAuth = Firebase.auth
        val currentUser = mAuth.currentUser


        binding.confirmPassword.addTextChangedListener(object : TextWatcher {
            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(s: Editable?) {
                binding.btnRegister.setBackgroundColor(custom_yellow)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.llRegisterOrGoogleFacebook.visibility = View.GONE
                binding.llLogin.visibility = View.VISIBLE
            }
        })
    }

    override fun initListener() = with(binding){
        binding.btnRegister.setOnClickListener {
            numberAndPasswordValidation()
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(phoneNumber.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Вы успешно зарегистрированы", Toast.LENGTH_SHORT).show()
                    }
                }
        }


            callbackManager = CallbackManager.Factory.create()

            btnFacebook.setReadPermissions("email", "public_profile")
            btnFacebook.registerCallback(callbackManager, object :
                FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d("huh", "facebook:onSuccess:$loginResult")
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.d("huh", "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d("huh", "facebook:onError", error)
                }
            })


        binding.btnGoogle.setOnClickListener {
            launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                try {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                    val account = task.getResult(ApiException::class.java)
                    if (account != null){
                        firebaseAuthWithGoogle(account)
                    }
                } catch (e: ApiException) {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                }

                val signInIntent: Intent? = App().getGoogleSignInClient()?.signInIntent
            launcher.launch(signInIntent)
        }
    }

        binding.logIn.setOnClickListener {

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth.signInWithCredential(credential).addOnSuccessListener { authResult: AuthResult ->
                val firebaseUser = mAuth.currentUser!!
                val email = firebaseUser.email
                if (Objects.requireNonNull(authResult.additionalUserInfo)?.isNewUser == true) {
                    Toast.makeText(requireContext(), "Account Created \n$email", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "User \n$email", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("huh", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)

        activity?.let {
            mAuth.signInWithCredential(credential).addOnCompleteListener(it) { task ->
                if (task.isSuccessful) {
                Log.d("huh", "signInWithCredential:success")
                val user = mAuth.currentUser
            } else {
                Log.d("huh", "signInWithCredential:failure", task.exception)
                Toast.makeText(context, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
            }
        }
    }

    private fun numberAndPasswordValidation() = with(binding) {
        if (name.text.isEmpty()){
            name.error = "Имя пустое"
        }
        if (phoneNumber.text.isEmpty()){
            phoneNumber.error = "Номер телефона пустой"
        }
        if (password.text.isEmpty()){
            password.error = "Введите пароль"
        }
        if (confirmPassword.text.isEmpty()){
            confirmPassword.error = "Введите пароль повторно "
            if (confirmPassword.text.equals(password.text)){
                confirmPassword.error = "Не совпадают пароли"
            }
        }
    }
}