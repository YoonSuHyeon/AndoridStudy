package com.example.databindingexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.databindingexample.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    val fragmentBlankBinding=FragmentBlankBinding.inflate(inflater,container,false)
        fragmentBlankBinding.user=User("수현","윤","0101010505")
        return fragmentBlankBinding.root
    }
}