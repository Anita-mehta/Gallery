package com.bhanguz.mvvmhilt.presentation.listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bhanguz.mvvmhilt.databinding.FragmentListingBinding
import com.bhanguz.mvvmhilt.presentation.adapter.ImagelistAdapter
import com.bhanguz.mvvmhilt.presentation.detail.DetailViewModel
import com.bhanguz.mvvmhilt.utils.Resources
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListingFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()

    private val imagelistAdapter: ImagelistAdapter by lazy {
        ImagelistAdapter(onImageClick = {
            findNavController().navigate(
                ListingFragmentDirections.actionListingFragmentToDetailsFragment(
                    it
                )
            )
        })
    }
    private var _binding: FragmentListingBinding? = null

    private val binding: FragmentListingBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListingBinding.inflate(inflater, container, false)
        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.img.collect{
                   when(it){
                       is Resources.Loading ->{

                       }
                       is Resources.Success ->{
                           imagelistAdapter.submitList(it.data)
                       }
                       is Resources.Error ->{

                       }
                   }
               }
            }

               }



    private fun setRecyclerView() {
        val mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager)
        //scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
//            override fun onLoadMore() {
//                observe()
//            }
//        })


        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = imagelistAdapter
            //addOnScrollListener(scrollListener)
        }
    }
}