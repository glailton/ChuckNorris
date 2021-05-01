package br.com.guiabolso.chucknorris.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: ChuckNorrisService
) {
    fun getCategories() = service.getCategories()
}