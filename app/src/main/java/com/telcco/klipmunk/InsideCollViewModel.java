package com.telcco.klipmunk;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsideCollViewModel extends ViewModel {

    private MutableLiveData<InsideCollRes> insidecollectionMutableLiveData;

    public MutableLiveData<InsideCollRes> getInsideCollection(){
        if(insidecollectionMutableLiveData == null){
            insidecollectionMutableLiveData = new MutableLiveData<>();
        }
        return  insidecollectionMutableLiveData;
    }

    public void getInsideCollectionRes(String userid, String foldername){
        ApiConstants  apiCall =RetrofitService.createService(ApiConstants.class);

        apiCall.insidecollApi(userid,foldername).enqueue(new Callback<InsideCollRes>() {
            @Override
            public void onResponse(Call<InsideCollRes> call,
                                   Response<InsideCollRes> response) {
                if (response.isSuccessful()){
                    insidecollectionMutableLiveData.postValue(response.body());
                }
                else{
                    Log.i("InscollectionRetro","signUpFailure");
                }
            }

            @Override
            public void onFailure(Call<InsideCollRes> call, Throwable t) {
                insidecollectionMutableLiveData.postValue(null);
                Log.i("MVVMThrow_Inscolletion",t.getLocalizedMessage());

            }
        });


    }
}
