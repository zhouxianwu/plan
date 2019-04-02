package com.chn.energy.util;

import java.util.List;

public class Page {
	  // 分页查询开始记录位置  
    public int begin;  
    // 分页查看下结束位置  
    public int end;  
    // 每页显示记录数  
    public int length=10;  
    // 查询结果总记录数  
    public int count;  
    // 当前页码  
    public int current;  
    // 总共页数  
    public int total;  
    
    public String pageHtml="";
    
    public List<?> result;
  
    public Page() {  
    }  
  
    /** 
     * 构造函数 
     *  
     * @param begin 
     * @param length 
     */  
    public Page(int begin, int length) {  
        this.begin = begin;  
        this.length = length;  
        this.end = this.begin + this.length;  
        this.current = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;  
    }  
  
    /** 
     * @param begin 
     * @param length 
     * @param count 
     */  
    public Page(int begin, int length, int count) {  
        this(begin, length);  
        this.count = count;  
    }  
    
    public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(Page page) {
		StringBuffer sb = new StringBuffer(""); 
		int totalPage=(int)Math.ceil(((double)page.count/(double)page.length));
		sb.append("<th colspan='100'>当前第"+page.current+"页/共"+totalPage+"页&nbsp;&nbsp;共"+page.count+"条记录&nbsp;&nbsp;<a  href='javascript:jump("+1+")'>首页</a>&nbsp;<a  href='javascript:jump("+(page.current-1==0? 1 :page.current-1 )+")'>上一页</a>&nbsp;");
		boolean isShow=false;
		int firstNum=page.current -4<=0?1 : page.current -4; 
		for(int i =  firstNum ;i<=totalPage ; i++){
			if(i==page.current){
				sb.append("<a class='current' href='javascript:jump("+i+")'>"+i+"</a>&nbsp;");
				continue;
			}
			sb.append("<a href='javascript:jump("+i+")'>"+i+"</a>&nbsp;");
			if(i==firstNum+7 && i<totalPage){
				sb.append("<a class='navpage'>...</a>&nbsp;");
				isShow=true;
				break;
			}
		}
		if(isShow){
			sb.append("<a href='javascript:jump("+totalPage+")'>"+totalPage+"</a>&nbsp;");
		}
		sb.append("<a href='javascript:jump("+(page.current+1>totalPage? page.current :page.current+1 )+")'>下一页</a>&nbsp;<a href='javascript:jump("+totalPage+")'>尾页</a></th>");
		this.pageHtml =sb.toString();
	}
  
    /** 
     * @return the begin 
     */  
    public int getBegin() {  
        return begin;  
    }  
  
    /** 
     * @return the end 
     */  
    public int getEnd() {  
        return end;  
    }  
  
    /** 
     * @param end 
     *            the end to set 
     */  
    public void setEnd(int end) {  
        this.end = end;  
    }  
  
    /** 
     * @param begin 
     *            the begin to set 
     */  
    public void setBegin(int begin) {  
        this.begin = (begin*this.getLength())-this.getLength();  
        if (this.length != 0) {  
            this.current = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;  
        }  
    }  
  
    /** 
     * @return the length 
     */  
    public int getLength() {  
        return length;  
    }  
  
    /** 
     * @param length 
     *            the length to set 
     */  
    public void setLength(int length) {  
        this.length = length;  
        if (this.begin != 0) {  
            this.current = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;  
        }  
    }  
  
    /** 
     * @return the count 
     */  
    public int getCount() {  
        return count;  
    }  
  
    /** 
     * @param count 
     *            the count to set 
     */  
    public void setCount(int count) {  
        this.count = count;  
        this.total = (int) Math.floor((this.count * 1.0d) / this.length);  
        if (this.count % this.length != 0) {  
            this.total++;  
        }  
    }  
  
    /** 
     * @return the current 
     */  
    public int getCurrent() {  
        return current;  
    }  
  
    /** 
     * @param current 
     *            the current to set 
     */  
    public void setCurrent(int current) {  
        this.current = current;  
    }  
  
    /** 
     * @return the total 
     */  
    public int getTotal() {  
        if (total == 0) {  
            return 1;  
        }  
        return total;  
    }  
  
    /** 
     * @param total 
     *            the total to set 
     */  
    public void setTotal(int total) {  
        this.total = total;  
    }  
    
    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }

}
