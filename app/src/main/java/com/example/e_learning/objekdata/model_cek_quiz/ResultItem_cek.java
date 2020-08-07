package com.example.e_learning.objekdata.model_cek_quiz;

import com.google.gson.annotations.SerializedName;

public class ResultItem_cek {

	@SerializedName("nilai")
	private String nilai;

	@SerializedName("nis")
	private String nis;

	@SerializedName("id_quiz")
	private String idQuiz;

	@SerializedName("id_guru")
	private String idGuru;

	@SerializedName("mapel")
	private String mapel;

	@SerializedName("status")
	private String status;

	public void setNilai(String nilai){
		this.nilai = nilai;
	}

	public String getNilai(){
		return nilai;
	}

	public void setNis(String nis){
		this.nis = nis;
	}

	public String getNis(){
		return nis;
	}

	public void setIdQuiz(String idQuiz){
		this.idQuiz = idQuiz;
	}

	public String getIdQuiz(){
		return idQuiz;
	}

	public void setIdGuru(String idGuru){
		this.idGuru = idGuru;
	}

	public String getIdGuru(){
		return idGuru;
	}

	public void setMapel(String mapel){
		this.mapel = mapel;
	}

	public String getMapel(){
		return mapel;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"nilai = '" + nilai + '\'' + 
			",nis = '" + nis + '\'' + 
			",id_quiz = '" + idQuiz + '\'' + 
			",id_guru = '" + idGuru + '\'' + 
			",mapel = '" + mapel + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}