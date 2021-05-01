package br.com.guiabolso.chucknorris.utils

import br.com.guiabolso.chucknorris.data.entities.Category
import br.com.guiabolso.chucknorris.data.remote.response.CategoryResponse

fun mapperResponseToCategory(categoriesResponse: List<CategoryResponse>): List<Category> {
    var categories: MutableList<Category> = mutableListOf()
    categoriesResponse.forEach{category ->
        categories.add(Category(category))
    }

    return categories
}
