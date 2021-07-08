package uz.yusufbek_ibragimov.experimentapi.ui.fragments.home_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import uz.yusufbek_ibragimov.experimentapi.core.adapters.NewsAdapter
import uz.yusufbek_ibragimov.experimentapi.core.model.news_model.NewsResponseItem
import uz.yusufbek_ibragimov.experimentapi.core.network.NewsAPI
import uz.yusufbek_ibragimov.experimentapi.core.utils.example_image_url_2_
import uz.yusufbek_ibragimov.experimentapi.core.utils.example_image_url_3_
import uz.yusufbek_ibragimov.experimentapi.databinding.HomeFragmentBinding

/**
 * Bismillah
 * Ibragimov Yusufbek
 * 07.07.2021
 * ibragimovy390@gmail.com
 * */

class HomeFragment: Fragment(),NewsAdapter.OnLongClickListener {

    private var _binding: HomeFragmentBinding? = null
    val binding get() = _binding!!

    private var adapterNews:NewsAdapter?=null
    private val viewModel: HomeVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= HomeFragmentBinding.inflate(inflater)
        requireActivity().actionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews()
        setObservers()
        loadData()

    }

    private fun loadData() {
        viewModel.getNews()
    }

    private fun setObservers() {

        viewModel.newsLiveData.observe(viewLifecycleOwner, {
            Log.d("MyTag", "setObservers: $it")
            adapterNews?.setData(it)
        })

        viewModel.editLiveData.observe(viewLifecycleOwner, Observer {
            if (it){
                loadData()
                Toast.makeText(requireContext(), "Edited \"NewsItem\"", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "No edited \"NewsItem\"", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setViews() {

        adapterNews= NewsAdapter(requireContext(),this)
        binding.listId.layoutManager=LinearLayoutManager(requireContext())
        binding.listId.adapter=adapterNews
        binding.floatBtnId.setOnClickListener{
            addNewsItem()
        }

    }

    override fun onLongClick(newsResponseItem: NewsResponseItem) {
        val newsResponseItemExample = NewsResponseItem(
            "new 12.02.2020",
            newsResponseItem.description,
            newsResponseItem.id,
            example_image_url_2_,
            "News Title ${newsResponseItem.id}",
            newsResponseItem.updated_at
        )
        viewModel.editNews(newsResponseItem.id.toInt() , newsResponseItemExample)
    }

    fun addNewsItem() {

        val newsResponseItem = NewsResponseItem(
            "new 12.02.2020",
            "description new",
            1,
            example_image_url_3_,
            "News Title 3",
            "update at 12.12.2021"
        )
        viewModel.addNews(newsResponseItem)
        viewModel.getNews()
        Toast.makeText(requireContext(), "Input new \"NewsItem\"", Toast.LENGTH_SHORT).show()

    }

}
