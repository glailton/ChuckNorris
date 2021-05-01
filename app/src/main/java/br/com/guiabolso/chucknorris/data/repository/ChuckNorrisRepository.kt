package br.com.guiabolso.chucknorris.data.repository

import br.com.guiabolso.chucknorris.data.remote.RemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class ChuckNorrisRepository @Inject  constructor(private val dataSource: RemoteDataSource){
    fun getCategories(): Flowable<List<String>> = dataSource.getCategories()
}