package br.com.guiabolso.chucknorris.data.remote

import br.com.guiabolso.chucknorris.data.remote.response.CategoryResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface ChuckNorrisService {

    @GET("/jokes/categories")
    fun getCategories(): Flowable<List<CategoryResponse>>
}