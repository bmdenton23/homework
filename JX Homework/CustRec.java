package com.jha.bsa.server.model.jx.duediligence.response;

public class CustRec {

	private CustDetail custDetail;
	private String custId;
	private String accountId;

	public CustDetail getCustDetail() {
		return custDetail;
	}

	public void setCustDetail(CustDetail custDetail) {
		this.custDetail = custDetail;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
