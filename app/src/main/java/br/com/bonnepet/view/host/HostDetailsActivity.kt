package br.com.bonnepet.view.host

import Data
import Time
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.bonnepet.R
import br.com.bonnepet.data.model.HostDTO
import br.com.bonnepet.data.model.PetDTO
import br.com.bonnepet.util.component.CircularProgressBar
import br.com.bonnepet.util.data.PetSizeEnum
import br.com.bonnepet.view.base.BaseActivity
import br.com.bonnepet.view.pet.PetDetailsActivity
import br.com.bonnepet.view.pet.adapter.PetAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_host_details.*

class HostDetailsActivity : BaseActivity(), PetAdapter.ItemClickListener {
    override val layoutResource = R.layout.activity_host_details
    override val activityTitle = R.string.host_details_title

    private val collapsingToolbarLayout by lazy { collapse_toolbar }

    private val appBarLayout by lazy { app_bar_layout }

    private val hostImageView by lazy { image_view_host }

    private val textAboutMe by lazy { text_about_me }

    private val textAddress by lazy { text_address }

    private val textPhone by lazy { text_phone }

    private val textPreferencePetSize by lazy { text_size }

    private val price by lazy { text_money_value }

    private val recyclerView by lazy { recycler_view }

    private lateinit var petAdapter: PetAdapter

    override fun onPrepareActivity(state: Bundle?) {
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.gray_600))
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.gray_100))

        val hostDTO = intent.getSerializableExtra(Data.HOST_DTO) as HostDTO
        setHostImage(hostDTO.pictureURL)

        setFields(hostDTO)

        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_gray_24dp)
                    collapsingToolbarLayout.title = getString(activityTitle)
                    isShow = true
                } else if (isShow) {
                    if (hostDTO.pictureURL.isEmpty()) {
                        collapsingToolbarLayout.setExpandedTitleColor(
                            ContextCompat
                                .getColor(this@HostDetailsActivity, R.color.gray_600)
                        )
                        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_gray_24dp)
                    } else {
                        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
                    }
                    collapsingToolbarLayout.title = hostDTO.name
                    isShow = false
                }
            }
        })

        petAdapter = PetAdapter(this, hostDTO.pet.toMutableList(), this)
        recyclerView.adapter = petAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setFields(host: HostDTO) {
        textAboutMe.text = host.aboutMe

        val address = "${host.addressDTO.street}, ${host.addressDTO.number}\n" +
                "${host.addressDTO.district}\n" +
                "${host.addressDTO.city} - ${host.addressDTO.state}"
        textAddress.text = address

        var phone = ""
        host.phone.forEach {
            phone += "$it\n"
        }
        textPhone.text = phone

        var petSize = ""
        host.preferencePetSize.forEach { size ->
            when (size.toInt()) {
                PetSizeEnum.SMALL.ordinal -> {
                    petSize += "${getString(PetSizeEnum.SMALL.description)}, "
                }
                PetSizeEnum.MEDIUM.ordinal -> {
                    petSize += "${getString(PetSizeEnum.MEDIUM.description)}, "
                }
                PetSizeEnum.LARGE.ordinal -> {
                    petSize += "${getString(PetSizeEnum.LARGE.description)}, "
                }
            }
        }
        textPreferencePetSize.text = petSize.removeSuffix(", ")

        price.text = host.price
    }

    private fun setHostImage(imageURL: String) {
        val progressBar = CircularProgressBar(this)
        progressBar.start()

        Glide.with(this)
            .load(imageURL)
            .error(R.drawable.ic_account_circle)
            .placeholder(progressBar)
            .transition(DrawableTransitionOptions.withCrossFade(Time.IMAGE_FADE))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(hostImageView)
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

    override fun onItemClick(pet: PetDTO) {
        val intent = Intent(this, PetDetailsActivity::class.java).apply {
            putExtra(Data.PET_DTO, pet)
        }
        startActivity(intent)
    }
}