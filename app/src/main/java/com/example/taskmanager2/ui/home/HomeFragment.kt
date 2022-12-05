package com.dacha.taskmanager1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dacha.taskmanager1.R
import com.dacha.taskmanager1.data.Tasks
import com.dacha.taskmanager1.databinding.FragmentHomeBinding
import com.dacha.taskmanager1.ui.home.HomeFragment.Companion.TASK
import com.dacha.taskmanager1.ui.task.TaskAdapter
import com.dacha.taskmanager1.ui.task.TaskFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFab.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
        setFragmentResultListener(
            TASK
        ){
                _, result ->
            val task = result.getSerializable("key_task")as Tasks
            adapter.addTask(task)
            binding.taskRecycler.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        const val TASK = "key.list"
        const val NOTE = "key.live"
    }
}