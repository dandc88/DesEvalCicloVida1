package com.desafiolatam.surveydonkey.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.desafiolatam.surveydonkey.databinding.FragmentEndBinding
import com.desafiolatam.surveydonkey.viewmodel.MainViewModel

class EndFragment : Fragment() {

    private var _binding: FragmentEndBinding? = null
    private val binding get() = _binding!!
    companion object {
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                isGranted ->
            if (isGranted) {
                permissionGranted()
            } else {
                permissionDenied()
            }
        }


    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEndBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        requestPermission()

        binding.tvFirstAnswer.text = viewModel.getFirstResult()
        binding.tvSecondAnswer.text = viewModel.getSecondResult()
        binding.tvThiedAnswer.text = viewModel.getThirdResult()
        binding.tvUserEmail.text = viewModel.getEmail()
        binding.tvUserComment.text = viewModel.getUserComment()

        // Log para depuración
        Log.d("EndFragment", "Datos mostrados en EndFragment")
    }

//    private fun checkStoragePermission() {
//        // Verificar si el permiso ya está concedido
//        if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            // Permiso ya concedido, informar al usuario con un Toast
//            Toast.makeText(requireContext(), "Permiso ya concedido", Toast.LENGTH_SHORT).show()
//        } else {
//            // Permiso no concedido, solicitar permiso al usuario
//            requestPermission()
//        }
//    }

    private fun requestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                permissionGranted()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                explainPermission()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun permissionGranted() {
        Toast.makeText(requireContext(), "Permiso concedido",
            Toast.LENGTH_LONG).show()
    }
    private fun permissionDenied() {
        Toast.makeText(requireContext(), "Permiso denegado",
            Toast.LENGTH_LONG).show()
    }


private fun explainPermission() {
        // Explicar al usuario la importancia del permiso
        AlertDialog.Builder(requireContext())
            .setTitle("Permiso necesario")
            .setMessage("El permiso para almacenamiento externo es necesario para guardar archivos.")
            .setPositiveButton("Decidi aceptar") { dialog, which ->
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            .setNegativeButton("Denegar") { dialog, which ->
                Toast.makeText(requireContext(), "Permiso denegado", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}