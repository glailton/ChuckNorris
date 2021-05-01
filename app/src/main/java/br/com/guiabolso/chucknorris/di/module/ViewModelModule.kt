package br.com.guiabolso.chucknorris.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.guiabolso.chucknorris.viewmodel.ViewModelKey
import br.com.guiabolso.chucknorris.ui.categories.CategoryViewModel
import br.com.guiabolso.chucknorris.ui.jokes.JokeViewModel
import br.com.guiabolso.chucknorris.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    protected abstract fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(JokeViewModel::class)
//    protected abstract fun bindJokeViewModel(jokeViewModel: JokeViewModel): ViewModel
}