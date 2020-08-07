package com.example.e_learning.objekdata.model_cek_quiz;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response_cek {

	@SerializedName("result")
	private List<ResultItem_cek> result;

	@SerializedName("kode")
	private int kode;

	@SerializedName("search_count")
	private int searchCount;

	public void setResult(List<ResultItem_cek> result){
		this.result = result;
	}

	public List<ResultItem_cek> getResult(){
		return result;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setSearchCount(int searchCount){
		this.searchCount = searchCount;
	}

	public int getSearchCount(){
		return searchCount;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"result = '" + result + '\'' + 
			",kode = '" + kode + '\'' + 
			",search_count = '" + searchCount + '\'' + 
			"}";
		}
}