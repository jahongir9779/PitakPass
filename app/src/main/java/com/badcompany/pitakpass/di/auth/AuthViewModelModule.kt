package com.badcompany.pitakpass.di.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.badcompany.pitakpass.di.viewmodels.AuthViewModelFactory
import com.badcompany.pitakpass.ui.auth.AuthViewModel
import com.badcompany.pitakpass.ui.auth.confirm.PhoneConfirmViewModel
import com.badcompany.pitakpass.ui.auth.login.LoginViewModel
import com.badcompany.pitakpass.ui.auth.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @AuthScope
    @Binds
    abstract fun bindViewModelFactory(factory: AuthViewModelFactory) : ViewModelProvider.Factory

    @AuthScope
    @Binds
    @IntoMap
    @AuthViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

//    @AuthScope
    @Binds
    @IntoMap
    @AuthViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

//    @AuthScope
    @Binds
    @IntoMap
    @AuthViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

//    @AuthScope
    @Binds
    @IntoMap
    @AuthViewModelKey(PhoneConfirmViewModel::class)
    abstract fun bindPhoneConfirmViewModel(viewModel: PhoneConfirmViewModel): ViewModel


}