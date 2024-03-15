package com.example.bqchatsdk.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.bqchatsdk.R;
import com.example.bqchatsdk.base.EaseBaseActivity;
import com.example.bqchatsdk.chat.EaseChatFragment;
import com.example.bqchatsdk.chat.interfaces.OnChatInputChangeListener;
import com.example.bqchatsdk.chatthread.EaseChatThreadFragment;
import com.example.bqchatsdk.chatthread.EaseChatThreadRole;
import com.example.bqchatsdk.chatthread.interfaces.OnChatThreadRoleResultCallback;
import com.example.bqchatsdk.databinding.EaseActivityThreadChatBinding;
import com.example.bqchatsdk.interfaces.OnJoinChatThreadResultListener;
import com.example.bqchatsdk.widget.EaseTitleBar;

import io.agora.chat.ChatThread;
import io.agora.util.EMLog;


public class EaseChatThreadActivity extends EaseBaseActivity {
    protected String parentMsgId;
    protected String conversationId;
    protected ChatThread thread;
    protected EaseBaseActivity mContext;
    protected EaseActivityThreadChatBinding binding;
    protected EaseChatThreadRole threadRole = EaseChatThreadRole.UNKNOWN;
    // Usually is group id
    private String parentId;

    public static void actionStart(Context context, String conversationId, String parentMsgId) {
        Intent intent = new Intent(context, EaseChatThreadActivity.class);
        intent.putExtra("parentMsgId", parentMsgId);
        intent.putExtra("conversationId", conversationId);
        context.startActivity(intent);
    }

    public static void actionStart(Context context, String conversationId, String parentMsgId, String parentId) {
        Intent intent = new Intent(context, EaseChatThreadActivity.class);
        intent.putExtra("parentMsgId", parentMsgId);
        intent.putExtra("conversationId", conversationId);
        intent.putExtra("parentId", parentId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        binding = EaseActivityThreadChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext= this;
        initIntent(getIntent());
        initView();
        initListener();
        initData();
    }

    public void initIntent(Intent intent) {
        parentMsgId = intent.getStringExtra("parentMsgId");
        conversationId = intent.getStringExtra("conversationId");
        parentId = intent.getStringExtra("parentId");
    }

    public void initView() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("thread_chat");
        if(fragment == null) {
            EaseChatFragment.Builder builder = new EaseChatThreadFragment.Builder(parentMsgId, conversationId, parentId)
                    .setOnJoinThreadResultListener(new OnJoinChatThreadResultListener() {
                        @Override
                        public void joinSuccess(String threadId) {
                            joinChatThreadSuccess(threadId);
                        }

                        @Override
                        public void joinFailed(int errorCode, String message) {
                            joinChatThreadFailed(errorCode, message);
                        }
                    })
                    .setOnThreadRoleResultCallback(new OnChatThreadRoleResultCallback() {
                        @Override
                        public void onThreadRole(EaseChatThreadRole role) {
                            threadRole = role;
                        }
                    })
                    .useHeader(true)
                    .enableHeaderPressBack(true)
                    .setHeaderBackPressListener(new EaseTitleBar.OnBackPressListener() {
                        @Override
                        public void onBackPress(View view) {
                            onBackPressed();
                        }
                    })
                    .setEmptyLayout(R.layout.ease_layout_no_data_show_nothing)
                    .setOnChatInputChangeListener(new OnChatInputChangeListener() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            EMLog.e("TAG", "onTextChanged: s: " + s.toString());
                        }
                    })
                    .hideSenderAvatar(true);
            setChildFragmentBuilder(builder);
            fragment = builder.build();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, fragment, "thread_chat").commit();
    }

    protected void joinChatThreadSuccess(String threadId) {

    }

    protected void joinChatThreadFailed(int errorCode, String message) {
        finish();
    }

    public void initListener() {

    }

    public void initData() {

    }

    public void setChildFragmentBuilder(EaseChatFragment.Builder builder) {

    }
}
