/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.bqchatsdk.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bqchatsdk.R;
import com.example.bqchatsdk.base.EaseBaseActivity;
import com.example.bqchatsdk.utils.EaseFileUtils;
import com.example.bqchatsdk.widget.photoview.EasePhotoView;

import io.agora.CallBack;
import io.agora.chat.ChatClient;
import io.agora.chat.ChatMessage;
import io.agora.chat.ImageMessageBody;
import io.agora.util.EMLog;
import io.agora.Error;

/**
 * download and show original image
 * 
 */
public class EaseShowBigImageActivity extends EaseBaseActivity {
	private static final String TAG = "ShowBigImage"; 
	private ProgressDialog pd;
	private EasePhotoView image;
	private int default_res = R.drawable.ease_default_image;
	private String filename;
	private Bitmap bitmap;
	private boolean isDownloaded;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.ease_activity_show_big_image);
		super.onCreate(savedInstanceState);
		setFitSystemForTheme(true, R.color.black, false);
		image = (EasePhotoView) findViewById(R.id.image);
		ProgressBar loadLocalPb = (ProgressBar) findViewById(R.id.pb_load_local);
		default_res = getIntent().getIntExtra("default_image", R.drawable.ease_default_avatar);
		Uri uri = getIntent().getParcelableExtra("uri");
		filename = getIntent().getExtras().getString("filename");
		String msgId = getIntent().getExtras().getString("messageId");
		EMLog.d(TAG, "show big msgId:" + msgId );

		//show the image if it exist in local path
		if (EaseFileUtils.isFileExistByUri(this, uri)) {
            Glide.with(this).load(uri).into(image);
		} else if(msgId != null) {
		    downloadImage(msgId);
		}else {
			image.setImageResource(default_res);
		}

		image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		findViewById(R.id.back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
	
	/**
	 * download image
	 * 
	 * @param msgId
	 */
	@SuppressLint("NewApi")
	private void downloadImage(final String msgId) {
        EMLog.e(TAG, "download with messageId: " + msgId);
		String str1 = getResources().getString(R.string.ease_download_the_pictures);
		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setCanceledOnTouchOutside(false);
		pd.setMessage(str1);
		pd.show();
        final ChatMessage msg = ChatClient.getInstance().chatManager().getMessage(msgId);
        final CallBack callback = new CallBack() {
			public void onSuccess() {
			    EMLog.e(TAG, "onSuccess" );
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (!isFinishing() && !isDestroyed()) {
							if (pd != null) {
								pd.dismiss();
							}
							isDownloaded = true;
							Uri localUrlUri = ((ImageMessageBody) msg.getBody()).getLocalUri();
							Glide.with(EaseShowBigImageActivity.this)
									.load(localUrlUri)
									.apply(new RequestOptions().error(default_res))
									.into(image);
						}
					}
				});
			}

			public void onError(final int error, String message) {
				EMLog.e(TAG, "offline file transfer error:" + message);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (EaseShowBigImageActivity.this.isFinishing() || EaseShowBigImageActivity.this.isDestroyed()) {
						    return;
						}
                        image.setImageResource(default_res);
                        pd.dismiss();
                        if (error == Error.FILE_NOT_FOUND) {
							Toast.makeText(getApplicationContext(), R.string.ease_image_expired, Toast.LENGTH_SHORT).show();
						}
					}
				});
			}

			public void onProgress(final int progress, String status) {
				EMLog.d(TAG, "Progress: " + progress);
				final String str2 = getResources().getString(R.string.ease_download_the_pictures_new);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
                        if (EaseShowBigImageActivity.this.isFinishing() || EaseShowBigImageActivity.this.isDestroyed()) {
                            return;
                        }
						pd.setMessage(str2 + progress + "%");
					}
				});
			}
		};
		

		msg.setMessageStatusCallback(callback);

		ChatClient.getInstance().chatManager().downloadAttachment(msg);
	}

	@Override
	public void onBackPressed() {
		if (isDownloaded)
			setResult(RESULT_OK);
		finish();
	}
}
