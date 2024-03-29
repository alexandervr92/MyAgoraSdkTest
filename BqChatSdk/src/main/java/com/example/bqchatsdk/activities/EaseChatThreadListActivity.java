package com.example.bqchatsdk.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.bqchatsdk.R;
import com.example.bqchatsdk.base.EaseBaseActivity;
import com.example.bqchatsdk.chatthread.EaseChatThreadListFragment;
import com.example.bqchatsdk.chatthread.interfaces.OnItemChatThreadClickListener;
import com.example.bqchatsdk.databinding.EaseActivityThreadListBinding;
import com.example.bqchatsdk.interfaces.OnTitleBarFinishInflateListener;
import com.example.bqchatsdk.manager.EaseActivityProviderHelper;
import com.example.bqchatsdk.widget.EaseTitleBar;

import io.agora.chat.ChatThread;


public class EaseChatThreadListActivity extends EaseBaseActivity {
    private EaseActivityThreadListBinding binding;
    private String parentId;
    private EaseTitleBar titleBar;

    public static void actionStart(Context context, String parentId) {
        Intent intent = new Intent(context, EaseChatThreadListActivity.class);
        intent.putExtra("parentId", parentId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        binding = EaseActivityThreadListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initIntent(getIntent());
        initView();
        initListener();
        initData();
    }

    public void initIntent(Intent intent) {
        parentId = intent.getStringExtra("parentId");
    }

    public void initView() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("thread_list");
        if(fragment == null) {
            fragment = new EaseChatThreadListFragment.Builder(parentId)
                    .useHeader(true)
                    .enableHeaderPressBack(true)
                    .setHeaderBackPressListener(new EaseTitleBar.OnBackPressListener() {
                        @Override
                        public void onBackPress(View view) {
                            onBackPressed();
                        }
                    })
                    .setLoadResultListener(new EaseChatThreadListFragment.OnLoadResultListener() {
                        @Override
                        public void onLoadFinish() {

                        }

                        @Override
                        public void onLoadFail(int errorCode, String message) {

                        }
                    })
                    .setOnItemClickListener(new OnItemChatThreadClickListener() {
                        @Override
                        public void onItemClick(View view, ChatThread thread, String messageId) {
                            EaseActivityProviderHelper.startToChatThreadActivity(EaseChatThreadListActivity.this,
                                    thread.getChatThreadId(), thread.getMessageId(), thread.getParentId());
                        }
                    })
                    .setOnTitleBarFinishInflateListener(new OnTitleBarFinishInflateListener() {
                        @Override
                        public void onTitleBarFinishInflate(EaseTitleBar titleBar) {
                            EaseChatThreadListActivity.this.titleBar = titleBar;
                        }
                    })
                    .build();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, fragment, "thread_list").commit();
    }

    public void initListener() {

    }

    public void initData() {

    }
}
