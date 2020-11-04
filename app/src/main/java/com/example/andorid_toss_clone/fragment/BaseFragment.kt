package com.example.andorid_toss_clone.fragment

import android.R.attr.data
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.andorid_toss_clone.R


open class BaseFragment : Fragment() {


//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val binding: FragmentDictBinding  = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_home,
//            container,
//            false
//        )
//        val view: View = binding.getRoot()
//        //here data must be an instance of the class MarsDataProvider
//        binding.setMarsdata(data)
//        return view
//    }

}