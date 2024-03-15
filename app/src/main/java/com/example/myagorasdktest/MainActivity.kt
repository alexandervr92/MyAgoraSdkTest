package com.example.myagorasdktest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.myagorasdktest.fragments.notifications.NotificationsFragment
import com.example.bqchatsdk.conversation.EaseConversationListFragment
import com.example.bqchatsdk.utils.EaseUtils
import com.example.myagorasdktest.databinding.ActivityMainBinding
import com.example.myagorasdktest.fragments.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.agora.chat.Conversation

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mContext: Context

    private lateinit var fragments:List<Fragment>

    private val conversationFragment by lazy { EaseConversationListFragment.Builder()
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
        .build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext = this
        fragments = listOf(
            HomeFragment(),
            conversationFragment,
            NotificationsFragment()
        )
        supportActionBar?.hide()

        setupBottomBar()

    }




    private fun setupBottomBar() {

        val navView: BottomNavigationView = binding.navView
        navView.setOnItemSelectedListener {itemMenu ->
            when(itemMenu.itemId){
                R.id.navigation_home -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.fragment_container_view, fragments[0])
                    }
                }

                R.id.navigation_dashboard -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.fragment_container_view, fragments[1])
                    }
                }

                R.id.navigation_notifications -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.fragment_container_view, fragments[2])
                    }
                }
            }
            true
        }
    }
}