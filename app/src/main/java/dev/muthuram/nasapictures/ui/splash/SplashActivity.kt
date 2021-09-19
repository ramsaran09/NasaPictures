package dev.muthuram.nasapictures.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.muthuram.nasapictures.R
import dev.muthuram.nasapictures.data.model.NavigationModel
import dev.muthuram.nasapictures.helper.observeLiveData
import dev.muthuram.nasapictures.helper.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupUi()
    }

    private fun setupUi() {
        splashViewModel.navigate.observeLiveData(this, ::navigate)
    }

    private fun navigate(navigationModel: NavigationModel) {
        startActivity(navigationModel)
    }
}