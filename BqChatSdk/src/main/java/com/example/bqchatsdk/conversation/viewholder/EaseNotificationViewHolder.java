package com.example.bqchatsdk.conversation.viewholder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.bqchatsdk.EaseUIKit;
import com.example.bqchatsdk.R;
import com.example.bqchatsdk.conversation.model.EaseConversationInfo;
import com.example.bqchatsdk.conversation.model.EaseConversationSetStyle;
import com.example.bqchatsdk.models.EaseGroupInfo;
import com.example.bqchatsdk.provider.EaseGroupInfoProvider;
import com.example.bqchatsdk.utils.EaseDateUtils;
import com.example.bqchatsdk.utils.EaseSmileUtils;
import com.example.bqchatsdk.utils.EaseUtils;

import java.util.Date;

import io.agora.chat.ChatMessage;
import io.agora.chat.Conversation;


public class EaseNotificationViewHolder extends EaseBaseConversationViewHolder{
    public EaseNotificationViewHolder(@NonNull View itemView, EaseConversationSetStyle style) {
        super(itemView, style);
    }

    @Override
    public void setData(EaseConversationInfo bean, int position) {
        super.setData(bean, position);
        Conversation item = (Conversation) bean.getInfo();
        Context context = itemView.getContext();
        String username = item.conversationId();
        mentioned.setVisibility(View.GONE);
        EaseGroupInfoProvider infoProvider = EaseUIKit.getInstance().getGroupInfoProvider();
        //avatar.setImageResource(R.drawable.em_system_nofinication);
        //name.setText(mContext.getString(R.string.ease_conversation_system_message));
        if(infoProvider != null) {
            EaseGroupInfo info = infoProvider.getGroupInfo(username, 3);
            if(info != null) {
                String name = info.getName();
                if(!TextUtils.isEmpty(name)) {
                    this.name.setText(name);
                }
                Glide.with(mContext).load(info.getIconUrl()).error(R.drawable.ease_system_nofinication).into(avatar);
                Drawable background = info.getBackground();
                if(background != null) {
                    itemView.setBackground(background);
                }
            }
        }
        if(!setModel.isHideUnreadDot()) {
            showUnreadNum(item.getUnreadMsgCount());
        }

        if(item.getAllMsgCount() != 0) {
            ChatMessage lastMessage = item.getLastMessage();
            message.setText(EaseSmileUtils.getSmiledText(context, EaseUtils.getMessageDigest(lastMessage, context)));
            time.setText(EaseDateUtils.getTimestampString(itemView.getContext(), new Date(lastMessage.getMsgTime())));
        }
    }
}
