package com.jha.bsa.server.web.jxservices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.Message;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.jha.bsa.server.model.Client;
import com.jha.bsa.server.model.jx.duediligence.request.BSAAddrSrchRequest;
import com.jha.bsa.server.model.jx.duediligence.response.AddrSrchRec;
import com.jha.bsa.server.model.jx.duediligence.response.BSAAddrSrchResponse;
import com.jha.bsa.server.sessions.ClientSession;
import com.jha.yh.jxf.provider.JxServiceException;
import com.jha.yh.jxf.provider.annotations.SoapActionChannel;
import com.jha.yh.jxf.provider.channels.XslTransformingChannel;
import com.jha.yh.jxf.provider.channels.model.MessageRecord;

@Configuration("BSAAddrSearchChannel")
@SoapActionChannel(soapAction = BSAAddrSearchChannel.SOAP_ACTION, channelName = BSAAddrSearchChannel.INPUT_CHANNEL)
public class BSAAddrSearchChannel extends XslTransformingChannel<BSAAddrSrchRequest, BSAAddrSrchResponse> {
	public static final String SOAP_ACTION = "\"http://jackhenry.com/ws/AddrSrch\"";
	public static final String INPUT_CHANNEL = "AddrSrchChannel";

	@Inject
	@Named("jxJaxb2Marshaller")
	private Jaxb2Marshaller marshaller;

	@Bean(name = INPUT_CHANNEL + "Configuration")
	public IntegrationFlow configureChannel() {
		return configure(INPUT_CHANNEL,
				"classpath:jx/xslt/BSAAddrSrch-to-BSAAddrlSearchRequest.xslt",
				"classpath:jx/xslt/BSAAddrSearchResponse-to-BSAAddrSrchResponse.xslt", marshaller);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Message<BSAAddrSrchResponse> handleMessage(Message<BSAAddrSrchRequest> message) {
		return super.handleMessage(message);
	}

	@Override
	public BSAAddrSrchResponse handlePayload(final BSAAddrSrchRequest payload) {

		List<MessageRecord> errorMessages = Lists.newArrayList();
		
		BSAAddrSrchResponse response = new BSAAddrSrchResponse();

		String externalId = payload.getCustId();
		Client c = null;
		try {
			c = ClientSession.getInstance().getClient("SLSIL", externalId);
		} catch (Exception e) {
			errorMessages.add(new MessageRecord()
					.category("Error")
					.code(JxBSAErrorCodes.REQUIRED_ELEMENT.getCode())
					.description("Customer Id not Found")
					.elementValue(payload.getCustId())
					.element("CustId")
					.location("AddrSrch/CustId"));
		}
		
		if (!errorMessages.isEmpty()) {
			throw new JxServiceException("Invalid request", errorMessages);
		}

		ArrayList<AddrSrchRec> addressRecords = new ArrayList<AddrSrchRec>();
		AddrSrchRec rec1 = new AddrSrchRec();
		rec1.setCustId(c.getName());
		rec1.setAccountId(c.getAddress().toString() + "Acct Id 1");
		rec1.setAddress(c.getAddress().toString());
		addressRecords.add(rec1);
		
		AddrSrchRec rec2 = new AddrSrchRec();
		rec2.setCustId("CustId 2");
		rec2.setAccountId("Acct Id 2");
		rec2.setAddress("Street City State Country 2");
		addressRecords.add(rec2);
		
		AddrSrchRec rec3 = new AddrSrchRec();
		rec3.setCustId("CustId 3");
		rec3.setAccountId("Acct Id 3");
		rec3.setAddress("Street City State Country 3");
		addressRecords.add(rec3);
		
		response.setAddressRecords(addressRecords);
		

		return response;
	}
}
