package com.happy.springboot.service.manager.dyndatasource.shardingstage;

public class Mod6000DbConfig {
    
	
	private int totalTable;//总表在数目

	private int numLen;//长度

	private int start;//索引开始位置

	private int totalDb;//总DB数目

	public int getTotalTable() {
		return totalTable;
	}

	public void setTotalTable(int totalTable) {
		this.totalTable = totalTable;
	}

	public int getNumLen() {
		return numLen;
	}

	public void setNumLen(int numLen) {
		this.numLen = numLen;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalDb() {
		return totalDb;
	}

	public void setTotalDb(int totalDb) {
		this.totalDb = totalDb;
	}

}
