package com.offcn.page;

import java.util.List;

public class Page<T> {

	private List<T> list;
	private int totalRecord;//总个数
	private int pageSize;//每页个数
	private int index;//索引
	private int totalNum;//总页数
	private int pageNo;//当前页
	private String path;
	private String queryString;
	
	public int getIndex() {
		return (getPageNo()-1)*getPageSize();
	}

	public int getTotalNum() {
		if(getTotalRecord() % getPageSize()==0 ) {
			return getTotalRecord()/getPageSize();
		}else {
			return getTotalRecord()/getPageSize()+1;
		}
		
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		if(pageNo<1) {
			pageNo=1;
		}else if(pageNo>getTotalNum()) {
			pageNo=getTotalNum();
		}
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Page(int totalRecord, int pageSize, int pageNo) {
		super();
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}
	
	
}
