package com.badcompany.pitakpass.fragments

//import com.codingwithmitch.openapi.di.main.MainScope
//import com.codingwithmitch.openapi.ui.main.account.AccountFragment
//import com.codingwithmitch.openapi.ui.main.account.ChangePasswordFragment
//import com.codingwithmitch.openapi.ui.main.account.UpdateAccountFragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.badcompany.pitakpass.di.auth.AuthScope
import com.badcompany.pitakpass.ui.auth.confirm.PhoneConfirmFragment
import com.badcompany.pitakpass.ui.auth.login.LoginFragment
import com.badcompany.pitakpass.ui.auth.register.RegisterFragment
import javax.inject.Inject

@AuthScope
class AuthFragmentFactory @Inject constructor(private val viewModelFactory: ViewModelProvider.Factory) :
    FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String) =
        when (className) {
            LoginFragment::class.java.name -> {
                LoginFragment(viewModelFactory)
            }
            PhoneConfirmFragment::class.java.name -> {
                PhoneConfirmFragment(viewModelFactory)
            }
            RegisterFragment::class.java.name -> {
                RegisterFragment(viewModelFactory)
            }
            else -> {
                LoginFragment(viewModelFactory)
            }
        }


}