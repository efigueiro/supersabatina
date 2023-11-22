package br.com.supersabatina.util;

public class PaginatorUtil {
	
	int totalRecords = 0;
	final int limit = 10;
	int offset = 0;
	int totalPages = 0;
	int currentPage = 0;
	
	public PaginatorUtil(int totalRecords, int currentPage) {
		this.totalRecords = totalRecords;
		this.currentPage = currentPage;
	}

	public int getOffset() {
		
 		this.offset = (limit * currentPage) - limit;
		
		return offset;
	}

	public int getTotalPages() {
		if (totalRecords <= 10) {
			this.totalPages = 1;
		} else {
			this.totalPages = totalRecords / limit;
			int remainder = totalRecords % limit;
			if (remainder > 0) {
				this.totalPages++;
			}
		}
		return totalPages;
	}
}
