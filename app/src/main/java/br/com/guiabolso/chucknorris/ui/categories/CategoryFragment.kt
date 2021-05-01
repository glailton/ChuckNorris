package br.com.guiabolso.chucknorris.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.guiabolso.chucknorris.MainApplication
import br.com.guiabolso.chucknorris.databinding.CategoryFragmentBinding
import br.com.guiabolso.chucknorris.ui.extensions.hide
import br.com.guiabolso.chucknorris.ui.extensions.show
import br.com.guiabolso.chucknorris.utils.Resource
import br.com.guiabolso.chucknorris.utils.mapperResponseToCategory
import br.com.guiabolso.chucknorris.viewmodel.ViewModelFactory
import javax.inject.Inject

class CategoryFragment : Fragment() {

    private var _binding: CategoryFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainApplication.appComponent.inject(this)
        _binding = CategoryFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryViewModel = ViewModelProvider(this, viewModelFactory).get(CategoryViewModel::class.java)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        categoryViewModel.getCategories().observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    resource.data?.let {
                        adapter.setItems(mapperResponseToCategory(it))
                    }
                    binding.progressBar.hide()
                    binding.recyclerView.show()
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, resource.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                    binding.recyclerView.hide()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = CategoryAdapter().apply {
            onItemClick = { category ->

            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}