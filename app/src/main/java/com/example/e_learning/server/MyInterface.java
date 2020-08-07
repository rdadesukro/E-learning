package com.example.e_learning.server;



import com.example.e_learning.objekdata.BaseResponse;
import com.example.e_learning.objekdata.model_cek_quiz.Response_cek;

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

public interface MyInterface {

    @FormUrlEncoded
    @POST("baca.php")
    Call<BaseResponse> baca(
            @Field("id_permohonan") String id_permohonan);



    @GET("cek_quiz.php")
    Call<Response_cek> cek_quiz(@Query("nis") String nis,
                                @Query("quiz") String quiz);

    @FormUrlEncoded
    @POST("matikan_notif.php")
    Call<BaseResponse> matikan_notif(
            @Field("id_user") String id_user);

    @FormUrlEncoded
    @POST("hapus_data.php")
    Call<BaseResponse> hapus_data(
            @Field("id_list_info_publik") String id_list_info_publik);

    @FormUrlEncoded
    @POST("hidup_notif.php")
    Call<BaseResponse> hidupkan_notif(
            @Field("id_user") String id_user,
            @Field("token") String token);


    @Multipart
    @POST("tambah_materi.php")
    Call<BaseResponse> simpan_berkas(
            @Part("bab") RequestBody id_opd,
            @Part("id_mapel") RequestBody kategori,
            @Part("id_guru") RequestBody id_guru,
            @Part MultipartBody.Part file);

    @Multipart
    @POST("edit_berkas.php")
    Call<BaseResponse> edit_file(
            @Part("bab") RequestBody bab,
            @Part("id_mapel") RequestBody id_mapel,
            @Part MultipartBody.Part file);


    @Multipart
    @POST("edit_data.php")
    Call<BaseResponse> edit_data(
            @Part("id_list_info_publik") RequestBody id_list_info_publik,
            @Part("kategori") RequestBody kategori,
            @Part("keterangan") RequestBody keterangan,
            @Part("nama_file") RequestBody nama_file);



    @FormUrlEncoded
    @POST("update_permohonan.php")
    Call<BaseResponse> update(
            @Field("status") String status,
            @Field("id_permohonan") String id_permohonan,
            @Field("file") String file,
            @Field("catatan") String catatan);

    @FormUrlEncoded
    @POST("update_permohonan2.php")
    Call<BaseResponse> update2(
            @Field("status") String status,
            @Field("id_permohonan") String id_permohonan,
            @Field("catatan") String catatan);

    @FormUrlEncoded
    @POST("update_permohonan3.php")
    Call<BaseResponse> update3(
            @Field("status") String status,
            @Field("id_permohonan") String id_permohonan,
            @Field("catatan") String catatan);


}
