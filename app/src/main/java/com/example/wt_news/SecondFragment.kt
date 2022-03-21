package com.example.wt_news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wt_news.databinding.FragmentSecondBinding
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val client = OkHttpClient()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("Hi")

        binding.cumButton.setOnClickListener {
            println("Clicked")
            val request = Request.Builder().url("ws://172.23.16.1:6969/listen").build()
            println("Clicked 2")
            val listener = KeckSocket()
            println("Clicked 3")
            val socke = client.newWebSocket(request, listener)
            println("Clicked 4")
//            client.dispatcher().executorService().shutdown()

//            findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment)
        }

        binding.buttonSecond.setOnClickListener {
            println("Navigating back to the first fragment")
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}