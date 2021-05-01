package br.com.guiabolso.chucknorris.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.guiabolso.chucknorris.data.remote.response.CategoryResponse

data class Category(
    val description: String = ""
)