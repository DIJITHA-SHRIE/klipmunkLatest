package com.telcco.klipmunk;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionViewModel extends AndroidViewModel {


    private MutableLiveData<CollectionResponse> collectionResponseMutableLiveData;
    private MutableLiveData<NewFolderResponse> newFolderResponseMutableLiveData;



    public CollectionViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<CollectionResponse> getCollection(){
        if(collectionResponseMutableLiveData == null){
            collectionResponseMutableLiveData = new MutableLiveData<>();
        }
        return  collectionResponseMutableLiveData;
    }

    public void getCollectionRes(String userid){
        ApiConstants  apiCall =RetrofitService.createService(ApiConstants.class);

        apiCall.collectionApi(userid).enqueue(new Callback<CollectionResponse>() {
            @Override
            public void onResponse(Call<CollectionResponse> call,
                                   Response<CollectionResponse> response) {
                if (response.isSuccessful()){
                    collectionResponseMutableLiveData.postValue(response.body());
                }
                else{
                    Log.i("collectionRetroFailure","Failure");
                }
            }

            @Override
            public void onFailure(Call<CollectionResponse> call, Throwable t) {
                collectionResponseMutableLiveData.postValue(null);
                Log.i("MVVMThrow_colletion",t.getLocalizedMessage());

            }
        });


    }
    public MutableLiveData<NewFolderResponse> getNewFolder(){
        if(newFolderResponseMutableLiveData == null){
            newFolderResponseMutableLiveData = new MutableLiveData<>();
        }
        return  newFolderResponseMutableLiveData;
    }

    public void getNewFolderRes(HashMap<String,NewFolderRequest> newFolderhash,String userId){
        ApiConstants  apiCall =RetrofitService.createService(ApiConstants.class);

        apiCall.newFolderAPI(newFolderhash,userId).enqueue(new Callback<NewFolderResponse>() {
            @Override
            public void onResponse(Call<NewFolderResponse> call,
                                   Response<NewFolderResponse> response) {
                if (response.isSuccessful()){
                    newFolderResponseMutableLiveData.postValue(response.body());
                }
                else{
                    Log.i("collectionFolderFailure","signUpFailure");
                }
            }

            @Override
            public void onFailure(Call<NewFolderResponse> call, Throwable t) {
                newFolderResponseMutableLiveData.postValue(null);
                Log.i("MVVMfoldercolletion",t.getLocalizedMessage());

            }
        });


    }


}
