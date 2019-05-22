package com.panda.util;
/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/26.
 * TIME:11:49.
 */
public class PagerUtil {
	private int pageNow;//当前序号
	
	private int pageSize;//每页数量
	
	private int totalPage;//总页数
	
	private int totalSize;//总数量
	
	@SuppressWarnings("unused")
	private boolean hasFirst;//是否有首页
	
	@SuppressWarnings("unused")
	private boolean hasPre;//是否有前一页
	
	@SuppressWarnings("unused")
	private boolean hasNext;//是否有下一页
	
	@SuppressWarnings("unused")
	private boolean hasLast;//是否有最后一页
	
	//构造方法设置当前序号以及总数
	public PagerUtil(int pageNow, int totalSize){
		
	this.pageNow=pageNow;
	
	this.totalSize=totalSize;
	
	}
	
	public int getPageNow() {
		
		return pageNow;
		
	}
	
	public void setPageNow(int pageNow) {
		
		this.pageNow = pageNow;
		
	}
	
	public int getPageSize() {
		
		return pageSize;
		
	}
	
	public void setPageSize(int pageSize) {
		
		this.pageSize = pageSize;
		
	}
	
	public int getTotalPage() {
		
		this.totalPage = getTotalSize()/getPageSize();
		 
		 if(totalSize%pageSize != 0){
			 totalPage++;
		 }
		 
		 return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		
		this.totalPage = totalPage;
	}
	

	public int getTotalSize() {
		
		return totalSize;
		
	}
	
	public void setTotalSize(int totalSize) {
		
		this.totalSize = totalSize;
	}
	
	public boolean isHasFirst() {
		
		if(pageNow==1)
			
			return false;
		
		else
			
			return true;
	}
	
	public void setHasFirst(boolean hasFirst) {
		
		this.hasFirst = hasFirst;
		
	}
	
	
	public boolean isHasPre() {
		
		if(this.isHasFirst())
			
			return true;
		
		else 
			
			return false;
		
	}
	public void setHasPre(boolean hasPre) {
		
		this.hasPre = hasPre;
		
	}
	
	public boolean isHasNext() {
		
		if(this.isHasLast())
			
			return true;
		
		else
			
			return false;
		
	}
	
	public void setHasNext(boolean hasNext) {
		
		this.hasNext = hasNext;
		
	}
	
	public boolean isHasLast() {
		
		 if(pageNow==this.getTotalPage())
			 
			 return false;
		 
		 else 
			 
			 return true;
	}
	
	public void setHasLast(boolean hasLast) {
		
		this.hasLast = hasLast;
	}

	
	
	 
}
