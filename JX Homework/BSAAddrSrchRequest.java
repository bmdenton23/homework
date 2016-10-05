package com.jha.bsa.server.model.jx.duediligence.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "BSAAddrSrchRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class BSAAddrSrchRequest {

	private String custId;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
}
