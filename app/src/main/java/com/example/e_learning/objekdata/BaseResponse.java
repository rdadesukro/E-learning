package com.example.e_learning.objekdata;

public class BaseResponse {



    public String getvalue() {
        return value;
    }

    public void setvalue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String value,message;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    String kode,pesan;
    String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId_registrasi() {
        return id_registrasi;
    }

    public void setId_registrasi(String id_registrasi) {
        this.id_registrasi = id_registrasi;
    }

    String id_registrasi;


}
