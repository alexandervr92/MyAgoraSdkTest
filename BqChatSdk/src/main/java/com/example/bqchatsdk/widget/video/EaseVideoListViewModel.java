package com.example.bqchatsdk.widget.video;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bqchatsdk.models.EaseVideoEntity;

import java.util.List;


public class EaseVideoListViewModel extends AndroidViewModel {
    private EaseSingleSourceLiveData<List<EaseVideoEntity>> videoListObservable;
    private EaseMediaManagerRepository repository;

    public EaseVideoListViewModel(@NonNull Application application) {
        super(application);
        repository = new EaseMediaManagerRepository();
        videoListObservable = new EaseSingleSourceLiveData<>();
    }

    public LiveData<List<EaseVideoEntity>> getVideoListObservable() {
        return videoListObservable;
    }

    public void getVideoList(Context context) {
        videoListObservable.setSource(repository.getVideoListFromMediaAndSelfFolder(context));
    }

}

