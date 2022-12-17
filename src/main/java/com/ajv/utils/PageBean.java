package com.ajv.utils;

import lombok.ToString;

import java.util.List;

/**
 * @author ajv
 * @Title:PageBean
 * @ProjectName JavaWebProject
 * @Description:TODO
 * @data 2022/11/1315:17
 */
@ToString
public class PageBean {

	private int currPage;

	private int prePage;

	private int nextPage;

	private int limitSize;

	private int countSize;

	private int startIndex;

	private List data;

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPrePage() {
		return this.currPage>1?this.currPage-1:this.currPage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return this.currPage<this.countSize?this.currPage+1:this.currPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLimitSize() {
		return limitSize;
	}

	public void setLimitSize(int limitSize) {
		this.limitSize = limitSize;
	}

	public int getCountSize() {
		return countSize;
	}

	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}

	public int getStartIndex() {
		return (this.currPage-1)*this.limitSize;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public PageBean(String currPage, String limitSize) {
		if (currPage == null){
			this.currPage = 1;
		}else{
			this.currPage = Integer.parseInt(currPage);
		}
		if (limitSize == null){
			this.limitSize = 10;
		}else{
			this.limitSize = Integer.parseInt(limitSize);
		}
	}
}
