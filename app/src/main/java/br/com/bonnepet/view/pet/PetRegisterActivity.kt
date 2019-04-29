package br.com.bonnepet.view.pet

import android.os.Bundle
import android.view.MenuItem
import br.com.bonnepet.R
import br.com.bonnepet.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_pet_register.*

class PetRegisterActivity : BaseActivity() {
    override val layoutResource = R.layout.activity_pet_register

    override val activityTitle = R.string.register_pet_title

    private val petPicture by lazy { view_pet_picture }

    override fun onPrepareActivity(state: Bundle?) {

    }


    /**
     *  Ação do botão de voltar da actionBar
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}