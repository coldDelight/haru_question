package com.colddelight.haru_question

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.colddelight.haru_question.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    //onCreateView should only by used for view inflation. Any logic that operates on the Fragment's view should be written in onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToWrite.setOnClickListener {
//            findNavController().navigate(R.id.action_home)
        }

        binding.btnToList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_haruListFragment)
        }
        binding.btnToWorry.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_haruWorryFragment)
        }

        //드로어
        binding.btnDrawer.setOnClickListener {
            binding.dlHome.openDrawer(GravityCompat.START)
//            findNavController().navigate(R.id.action_homeFragment_to_haruWorryFragment)
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}