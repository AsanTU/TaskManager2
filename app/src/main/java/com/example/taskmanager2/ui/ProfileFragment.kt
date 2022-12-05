package com.dacha.taskmanager1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.dacha.taskmanager1.data.local.PrefOnBoarding
import com.dacha.taskmanager1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var prefOnBoarding: PrefOnBoarding
    private lateinit var binding:FragmentProfileBinding
    var launchForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val image = result.data?.data
            binding.ivImage.setImageURI(image)}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        prefOnBoarding = PrefOnBoarding(requireContext())
        binding.etName.setText(prefOnBoarding.getName())
        binding.etName.addTextChangedListener{
            prefOnBoarding.saveName(binding.etName.text.toString())
        }
        binding.etAge.setText(prefOnBoarding.getAge())
        binding.etAge.addTextChangedListener{
            prefOnBoarding.saveAge(binding.etAge.text.toString())
        }
    }



    private fun initListeners() {
        binding.ivImage.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            launchForResult.launch(intent)
        }
    }

}