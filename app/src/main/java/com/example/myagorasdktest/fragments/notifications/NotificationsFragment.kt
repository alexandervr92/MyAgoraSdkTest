package com.example.myagorasdktest.fragments.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bqchatagora.bqchatapp.notifications.NotificationsViewModel
import com.example.myagorasdktest.ChatConversationActivity
import com.example.myagorasdktest.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private val watsei = "6422c9748c95a0154637e48d"
    private val laraChic = "603cf3420f53540011c88c0f"

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtLara.setOnClickListener {
            val intent = Intent(requireContext(), ChatConversationActivity::class.java)
            intent.putExtra("chatType","")
            intent.putExtra("conversationID", laraChic)
            startActivity(intent)

        }

        binding.txtWatsei.setOnClickListener {
            val intent = Intent(requireContext(),ChatConversationActivity::class.java)
            intent.putExtra("chatType","")
            intent.putExtra("conversationID", watsei)
            startActivity(intent)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}