package com.rizieq.barangku.api;

import com.rizieq.barangku.model.barang.ResponseBarang;
import com.rizieq.barangku.model.barang.ResponseModel;
import com.rizieq.barangku.model.insert.ImageClass;
import com.rizieq.barangku.model.user.ResponseApiModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiRequest {


    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseApiModel> login (@Field("email") String email,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseApiModel> signup (@Field("email") String email,
                                   @Field("password") String password,
                                   @Field("nama") String nama,
                                   @Field("no_handphone") String no_handphone,
                                   @Field("jenis_kelamin") String jenis_kelamin,
                                   @Field("username") String username,
                                   @Field("alamat") String alamat);

    // disini
    @GET("list_barang.php")
    Call<ResponseBarang> getBarang(@Query("id") String id);

    @FormUrlEncoded
    @POST("create_barang.php")
    Call<ResponseModel> createBarang(@Field("nama_barang") String nama_barang,
                                     @Field("kondisi") String kondisi,
                                     @Field("status") String status,
                                     @Field("desc_barang") String desc_barang,
                                     @Field("harapan_barang") String harapan_barang);

    @Multipart
    @POST("insert_barang.php")
    Call<ImageClass> uploadImage(@Part("nama_barang") RequestBody nama_barang,
                                 @Part("kondisi") RequestBody kondisi,
                                 @Part("status") RequestBody status,
                                 @Part("desc_barang") RequestBody desc_barang,
                                 @Part("harapan_barang") RequestBody harapan_barang,
                                 @Part("respon_kurir") RequestBody respon_kurir,
                                 @Part("status_harapan") RequestBody status_harapan,
                                 @Part("alamat") RequestBody alamat,
                                 @Part("noHandphone") RequestBody noHandphone,
                                 @Part MultipartBody.Part image,
                                 @Part("id") Integer id);



}
