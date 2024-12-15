package com.example.myapplication;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountViewModel extends AndroidViewModel {
    private final MutableLiveData<Integer> countLiveData = new MutableLiveData<>();
    private final AppDatabase database;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CountViewModel(@NonNull Application application) {
        super(application);
        // 通过getInstance方法获取数据库实例
        database = AppDatabase.getInstance(application);
        executorService.execute(() -> {
            CountModel countModel = database.countDao().getCountById(1);
            if (countModel!= null) {
                countLiveData.postValue(countModel.count);
            } else {
                long id = database.countDao().insert(new CountModel(0));
                countLiveData.postValue(0);
            }
        });
    }

    public LiveData<Integer> getCountLiveData() {
        return countLiveData;
    }

    public void incrementCount() {
        executorService.execute(() -> {
            CountModel countModel = database.countDao().getCountById(1);
            if (countModel!= null) {
                int newCount = countModel.count + 1;
                database.countDao().updateCount(1, newCount);
                countLiveData.postValue(newCount);
            }
        });
    }
}
