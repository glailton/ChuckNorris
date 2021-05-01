package br.com.guiabolso.chucknorris.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.guiabolso.chucknorris.data.entities.Category
import br.com.guiabolso.chucknorris.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var inflater: LayoutInflater? = null
    private val categories: MutableList<Category> = mutableListOf()
    var onItemClick: ((entity: Category) -> Unit)? = null

    fun setItems(categories: List<Category>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }

        val binding = ItemCategoryBinding.inflate(inflater!!, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.bind(category)
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.description.text = category.description

            binding.root.setOnClickListener {
                onItemClick?.invoke(category)
            }
        }

    }
}