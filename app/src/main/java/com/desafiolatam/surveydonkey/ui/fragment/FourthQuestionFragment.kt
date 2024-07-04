package com.desafiolatam.surveydonkey.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.desafiolatam.surveydonkey.databinding.FragmentFourthQuestionBinding
import com.desafiolatam.surveydonkey.viewmodel.MainViewModel


class FourthQuestionFragment : Fragment() {

    private var _binding: FragmentFourthQuestionBinding? = null
    val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // Configurar TextWatcher para el EditText de email y de sugerencia
        setupTextWatcherForEmail()

        setupTextWatcherForSugerencia()


    }

    private fun setupTextWatcherForEmail() {
        binding.textEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.setEmail(s.toString())
            }
        })
    }

    private fun setupTextWatcherForSugerencia() {
        binding.textSugerencia.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val editText = binding.textSugerencia

                    // Obtener el layout del EditText para contar las líneas visibles
                    val layout = editText.layout

                    if (layout != null) {
                        val lineCount = layout.lineCount
                        if (lineCount > 6) {
                            // Limitar a 6 líneas visibles
                            val endPosition = layout.getLineEnd(5) // Índice basado en cero, así que la sexta línea es el índice 5
                            val truncatedText = it.toString().substring(0, endPosition)

                            editText.setText(truncatedText)
                            editText.setSelection(truncatedText.length) // Mover el cursor al final
                            Toast.makeText(requireContext(), "Máximo 6 líneas permitidas", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.setSugerencia(it.toString())
                        }
                    }
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}