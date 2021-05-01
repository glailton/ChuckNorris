package br.com.guiabolso.chucknorris.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import br.com.guiabolso.chucknorris.data.remote.response.CategoryResponse
import br.com.guiabolso.chucknorris.data.repository.ChuckNorrisRepository
import br.com.guiabolso.chucknorris.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val repository: ChuckNorrisRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val categories = MutableLiveData<Resource<List<CategoryResponse>>>()

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        categories.value = Resource.loading(null)
        compositeDisposable.add(
            repository.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({categoryList ->
                    categories.value = Resource.success(categoryList)
                }, {
                    categories.value = Resource.error("Something Went Wrong", null)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getCategories(): LiveData<Resource<List<CategoryResponse>>> {
        return categories
    }
}