package com.example.bqchatsdk.widget.chatrow;

import android.content.Context;
import android.widget.TextView;

import com.example.bqchatsdk.R;

import io.agora.chat.ChatMessage;


public class EaseChatRowUnknown extends EaseChatRow {
	private TextView contentView;

    public EaseChatRowUnknown(Context context, boolean isSender) {
		super(context, isSender);
	}

    public EaseChatRowUnknown(Context context, ChatMessage message, int position, Object adapter) {
		super(context, message, position, adapter);
	}

	@Override
	protected void onInflateView() {
		inflater.inflate(!showSenderType ? R.layout.ease_row_received_message
                : R.layout.ease_row_sent_message, this);
	}

	@Override
	protected void onFindViewById() {
		contentView = (TextView) findViewById(R.id.tv_chatcontent);
	}

    @Override
    public void onSetUpView() {
        contentView.setText(getContext().getString(R.string.ease_chat_unknown_type));
    }
}
