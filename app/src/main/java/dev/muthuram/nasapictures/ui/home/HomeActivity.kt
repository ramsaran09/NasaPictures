package dev.muthuram.nasapictures.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import dev.muthuram.nasapictures.R
import dev.muthuram.nasapictures.adapter.SpaceImageAdapter
import dev.muthuram.nasapictures.data.model.SpaceNewsModel
import dev.muthuram.nasapictures.data.model.NavigationModel
import dev.muthuram.nasapictures.helper.observeLiveData
import dev.muthuram.nasapictures.helper.startActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel : HomeViewModel by viewModel()
    private val spaceImageAdapter by lazy { SpaceImageAdapter(this ,::onMoreDetailClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupUi()
    }

    private fun setupUi() {
        setupAdapter()
        homeViewModel.navigate.observeLiveData(this, ::navigate)
        homeViewModel.error.observeLiveData(this, ::handleError)
        homeViewModel.loader.observeLiveData(this, ::handleLoaderVisibility)
        homeViewModel.spaceImageData.observeLiveData(this, ::addListToAdapter)
    }

    private fun setupAdapter() {
        uiRvNews.apply {
            layoutManager = GridLayoutManager(this@HomeActivity,2)
            adapter = spaceImageAdapter
        }
    }

    private fun navigate(navigationModel: NavigationModel) {
        startActivity(navigationModel)
    }

    private fun onMoreDetailClick(spaceNewsModel: SpaceNewsModel) {
        homeViewModel.moreDetails(spaceNewsModel)
    }

    private fun handleError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    private fun handleLoaderVisibility(isLoading: Boolean) {
        if (isLoading){
            uiAnimationLoader.visibility = View.VISIBLE
            uiRvNews.visibility = View.GONE
        }else {
            uiAnimationLoader.visibility = View.GONE
            uiRvNews.visibility = View.VISIBLE
        }
    }

    private fun addListToAdapter(spaceImageData: ArrayList<SpaceNewsModel>) {
        Log.d("spaceImageModel",spaceImageData.toString())
        spaceImageAdapter.updateList(spaceImageData)
    }

}