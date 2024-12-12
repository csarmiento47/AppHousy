package com.inforcap.apphousy.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.inforcap.apphousy.adapter.MansionAdapter
import com.inforcap.apphousy.databinding.FragmentMainBinding
import com.inforcap.apphousy.model.Mansion
import kotlinx.coroutines.flow.collectLatest


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MansionViewModel by activityViewModels()
    private lateinit var adapter: MansionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)

        Log.d("TAG","aqui estamos")
        lifecycleScope.launchWhenCreated {
            viewModel.getMansionList().collect() {
                initRecyclerView(it)
            }
        }


        return binding.root

    }

    private fun initRecyclerView(list: List<Mansion>){
        Log.d("ALOHA","aqui estamos")
        adapter = MansionAdapter(list)
        binding.rvMansiones.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.rvMansiones.adapter = adapter
        onItemSelected()
    }

    private fun onItemSelected(){
        adapter.onClick = {
            id ->
            lifecycleScope.launchWhenCreated {
                viewModel.getMansionById(id).collectLatest {
                    mansion -> mansion.let {
                        startActivity(Intent(activity, DetailActivity::class.java).apply {
                            putExtra("BUNDLE", Bundle().apply {
                                if (mansion != null) {
                                    putParcelable("MANSION",mansion)
                                }
                            })
                        })
                    }
                }
            }
        }
    }




}