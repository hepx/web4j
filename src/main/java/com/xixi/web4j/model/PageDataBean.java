package com.xixi.web4j.model;

import java.io.Serializable;
import java.util.List;

/**
 * 分页包装类
 * @author hepx
 * @param <T>
 *
 */
public class PageDataBean<T> implements Serializable {

	private static final long serialVersionUID = 1272708975275723521L;
	
	private Integer start;// 分页起点
	
	private Integer limit;// 每页记录
	
	private Integer maxPage;// 最大页数
	
	private List<T> rows;//记录
	
	public PageDataBean(){}
	
	public PageDataBean(Integer start,Integer limit,Integer maxPage){
		this.start=start;
		this.limit=limit;
		this.maxPage=maxPage;
	}

	public void countMaxPage(Integer total){
		this.maxPage=(total+limit-1)/limit;
	}
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
