package br.com.guiabolso.chucknorris.ui.jokes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.guiabolso.chucknorris.R

class JokeFragment : Fragment() {

    companion object {
        fun newInstance() = JokeFragment()
    }

    private lateinit var viewModel: JokeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.joke_fragment, container, false)
    }

}