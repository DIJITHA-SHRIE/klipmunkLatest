package com.telcco.klipmunk;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConstants {
    @FormUrlEncoded
    @POST("login")
    Call<LogInResponse> loginAPI(@Field("email") String email,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<SignUpResponse> signupAPI(@Field("name") String firstname,
                                 @Field("email") String email,
                                @Field("password") String password,
                                @Field("password2") String password2);

    @GET("list/topic/thumbnail")
    Call<CollectionResponse> collectionApi(@Header("x-userid") String userid);

    @GET("list/topic/{topicName}")
    Call<InsideCollRes> insidecollApi(@Header("x-userid") String userid, @Path("topicName") String foldername);

    @POST("create/newtopic")
    Call<NewFolderResponse> newFolderAPI(@Body HashMap<String,NewFolderRequest> newFolderRequestHashMap,@Header("x-userid") String userid);


  //  @GET("allclips?page=")
   // Call<>

    @FormUrlEncoded
    @POST("forgotpassword")
    Call<ForgotPasswordResponse> forgotAPI(@Field("email") String email);

    // @POST("reset-password")
    // Call<ResetPassResponse> ResetAPI(@Field("email") String email,
    //                                  @Field("token") String token);

    @POST("updateprofile")
    Call<UpdateProfileResponse>updateAPI(@Header("x-userid") String userid,
                                         @Field("username") String username,
                                         @Field("dob") String dob,
                                         @Field("fieldstudy") String fieldstudy,
                                         @Field("hobbies") String hobbies,
                                         @Field("intrest") String intrest,
                                         @Field("bio") String bio,
                                         @Field("mobile") String mobile);




    //@GET("getProfile")
   // Call<>

}
