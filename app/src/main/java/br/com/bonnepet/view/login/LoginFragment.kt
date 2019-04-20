package br.com.bonnepet.view.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.bonnepet.R
import br.com.bonnepet.data.model.Credential
import br.com.bonnepet.util.extension.setSafeOnClickListener
import br.com.bonnepet.view.base.BaseFragment
import br.com.bonnepet.view.login.userRegister.UserRegisterActivity
import br.com.bonnepet.view.main.MainActivity
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment() {
    private lateinit var mainActivity: MainActivity

    override val layoutResource = R.layout.login_fragment

    override val fragmentTitle = R.string.get_in

    private val inputEmail by lazy { input_email }

    private val inputPassword by lazy { input_password }

    private val btnLogin by lazy { btn_login }

    private val registerLink by lazy { register_link }

    private val progressBar by lazy { progress_bar }

    private lateinit var viewModel: LoginViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideActionBarDisplayHome()
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        btnLogin.setSafeOnClickListener { doLogin() }
        registerLink.setSafeOnClickListener { goToRegister() }

        viewModel.message.observe(this, Observer { message ->
            hideProgressBar()
            showToast(message)
        })

        viewModel.onLoginSuccess.observe(this, Observer { authenticationResult ->
            hideProgressBar()
            if (authenticationResult) {
                mainActivity.userAuthenticated()
            }
        })
    }

    private fun doLogin() {
        showProgressBar()
        val credential = Credential(inputEmail.text.toString(), inputPassword.text.toString())
        viewModel.authenticateUser(credential)
    }

    private fun goToRegister() {
        startActivityForResult(Intent(context, UserRegisterActivity::class.java), RequestCode.SIGN_UP)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RequestCode.SIGN_UP -> mainActivity.userAuthenticated()
            }
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        inputEmail.isEnabled = false
        inputPassword.isEnabled = false
        btnLogin.visibility = View.INVISIBLE
        registerLink.visibility = View.INVISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
        inputEmail.isEnabled = true
        inputPassword.isEnabled = true
        btnLogin.visibility = View.VISIBLE
        registerLink.visibility = View.VISIBLE
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivity = context as MainActivity

    }
}
