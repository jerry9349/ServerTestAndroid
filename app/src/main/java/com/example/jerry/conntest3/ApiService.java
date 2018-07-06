package com.example.jerry.conntest3;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    public static final String API_URL = "http://172.22.224.242:8080/joo/member/";
    //public static final String API_URL = "http://172.22.225.36:8080/spring_mybatis/member/";

    //int version

    @GET("allmember")//api 주소
    Call<ResponseBody> getComment();

    @FormUrlEncoded
    @POST("addcolum")
    Call<ResponseBody> sendPost(@Field("mysqlId") String id,@Field("mysqlPw") String pw,@Field("mysqlEmail") String email,@Field("mysqlName") String name,@Field("mysqlIntroduce") String introduce);
    //Call<ResponseBody> 함수이름(@Query("변수 이름"),안드로이드에서 보낼 변수);


    //String version
/*
    @GET("boardTest")
    Call<ResponseBody> getCommentStr();*/

    /*@FormUrlEncoded
    @POST("boardTest")
    Call<ResponseBody> getPostCommentStr();*/
}
