package com.winter.app.util;

import lombok.Data;

@Data
public class Pager {
	
	private Long perPage=10L;
	private Long startRow;
	private Long page=1L;
	
	private String kind;
	private String search;
	
	public void makeRow() {
		this.startRow=(page-1)*perPage;
	}
	
	//getter
	public String getKind() {
		if(this.kind==null) {
			this.kind="k1";
		}
		return this.kind;
	}
	
	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return this.search;
	}
	


}
