package com.example.myagorasdktest.fragments.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bqchatsdk.conversation.EaseConversationListFragment
import com.example.bqchatsdk.utils.EaseUtils
import com.example.myagorasdktest.ChatActivity
import com.example.myagorasdktest.R
import com.example.myagorasdktest.databinding.FragmentDashboardBinding
import io.agora.chat.Conversation


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fl_fragment,
                EaseConversationListFragment.Builder()
                    .useHeader(true)
                    .setHeaderTitle("Conversaciones")
                    .enableHeaderPressBack(false)
                    .setItemClickListener { _, conversation, _ ->
                        val info = conversation.info
                        if (info is Conversation) {
                            ChatActivity.actionStart(
                                mContext,
                                info.conversationId(),
                                EaseUtils.getChatType(info)
                            )
                        }
                    }
                    .build())
            .commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}