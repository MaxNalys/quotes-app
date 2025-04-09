package com.example.quoteapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quoteapp.data.remote.ApiClient
import com.example.quoteapp.data.repository.QuoteRepositoryImpl
import com.example.quoteapp.databinding.FragmentQuoteBinding
import com.example.quoteapp.domain.usecase.GetQuoteUseCase
import com.example.quoteapp.ui.viewmodel.QuoteFragmentViewModel
import com.example.quoteapp.ui.viewmodel.QuoteViewModelFactory

class QuoteFragment : Fragment() {

    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: QuoteFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = ApiClient.apiService
        val repository = QuoteRepositoryImpl(apiService)
        val useCase = GetQuoteUseCase(repository)
        val factory = QuoteViewModelFactory(useCase)

        viewModel = ViewModelProvider(this, factory)[QuoteFragmentViewModel::class.java]
        viewModel.loadAllQuotes()
        viewModel.quoteLiveData.observe(viewLifecycleOwner) { quote ->
            if (quote != null) {
                binding.quoteTextView.text = quote.quote
                binding.authorTextView.text = "— ${quote.author}"
            } else {
                binding.quoteTextView.text = "Помилка отримання цитати"
                binding.authorTextView.text = ""
            }
        }

        binding.getQuoteButton.setOnClickListener {
            viewModel.showNextQuote()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
