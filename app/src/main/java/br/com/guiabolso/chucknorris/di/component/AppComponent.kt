package br.com.guiabolso.chucknorris.di.component

import br.com.guiabolso.chucknorris.di.module.NetworkModule
import br.com.guiabolso.chucknorris.di.module.ViewModelModule
import br.com.guiabolso.chucknorris.ui.categories.CategoryFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(categoryFragment: CategoryFragment)
}