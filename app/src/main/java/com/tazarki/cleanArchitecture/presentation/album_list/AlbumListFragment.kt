package com.tazarki.cleanArchitecture.presentation.album_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tazarki.cleanArchitecture.databinding.AlbumListFragmentBinding
import com.tazarki.cleanArchitecture.domain.model.Album
import com.tazarki.cleanArchitecture.presentation.ui.adapter.AlbumItemAdapter

class AlbumListFragment: Fragment() {

    private var _binding: AlbumListFragmentBinding? = null
    private val binding get() = _binding!!
    private val _albumListViewModel : AlbumListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(_albumListViewModel.state.value!!.albums.isEmpty())
             _albumListViewModel.state.observe(viewLifecycleOwner) {
                 if(it.isLoading) binding.albumListFragmentPb.visibility = View.VISIBLE
                 else binding.albumListFragmentPb.visibility = View.GONE
                 setAlbumListIntoRecyclerview(it.albums) }
        else setAlbumListIntoRecyclerview(_albumListViewModel.state.value!!.albums)
    }

    private fun setAlbumListIntoRecyclerview(albums: List<Album>) {
        binding.albumListFragmentRv.apply {
            adapter = AlbumItemAdapter(albums)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}