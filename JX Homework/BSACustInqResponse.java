package com.jha.bsa.server.model.jx.duediligence.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CustInqResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class BSACustInqResponse {

	private String custId;
	private String accountId;
	private String personName;
	private String addr;
	private String naicsCode;
	private String birthDate;
	private String lastMaintDate;

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getNaicsCode() {
		return naicsCode;
	}

	public void setNaicsCode(String naicsCode) {
		this.naicsCode = naicsCode;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getLastMaintDate() {
		return lastMaintDate;
	}

	public void setLastMaintDate(String lastMaintDate) {
		this.lastMaintDate = lastMaintDate;
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
