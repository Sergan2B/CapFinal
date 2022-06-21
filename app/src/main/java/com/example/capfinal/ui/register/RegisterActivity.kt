package com.example.capfinal.ui.register

import android.view.LayoutInflater
import com.example.capfinal.core.base.BaseActivity
import kg.geektech.finalprojectcustomcap.databinding.ActivityRegisterBinding

class RegisterActivity: BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(inflater)
    }

    override fun initView() {
    }

    override val viewModel: RegisterViewModel = TODO()
}