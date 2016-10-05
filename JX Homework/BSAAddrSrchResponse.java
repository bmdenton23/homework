package com.jha.bsa.server.model.jx.duediligence.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AddrSrchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class BSAAddrSrchResponse {

	@XmlElementWrapper(name="addressRecords")
	@XmlElement(name="addressRecord")
	private List<AddrSrchRec> addressRecords;

	public List<AddrSrchRec> getAddressRecords() {
		return addressRecords;
	}

	public void setAddressRecords(List<AddrSrchRec> addressRecords) {
		this.addressRecords = addressRecords;
	}

}
