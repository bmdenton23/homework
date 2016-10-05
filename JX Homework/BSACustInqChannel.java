package com.jha.bsa.server.web.jxservices;

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
import com.jha.bsa.server.model.jx.duediligence.request.BSACustInqSrchRequest;
import com.jha.bsa.server.model.jx.duediligence.response.BSACustInqResponse;
import com.jha.bsa.server.sessions.ClientSession;
import com.jha.yh.jxf.provider.JxServiceException;
import com.jha.yh.jxf.provider.annotations.SoapActionChannel;
import com.jha.yh.jxf.provider.channels.XslTransformingChannel;
import com.jha.yh.jxf.provider.channels.model.MessageRecord;

@Configuration("BSACustInqChannel")
@SoapActionChannel(soapAction = BSACustInqChannel.SOAP_ACTION, channelName = BSACustInqChannel.INPUT_CHANNEL)
public class BSACustInqChannel extends XslTransformingChannel<BSACustInqSrchRequest, BSACustInqResponse> {
	public static final String SOAP_ACTION = "\"http://jackhenry.com/ws/CustInq\"";
	public static final String INPUT_CHANNEL = "CustInqChannel";

	@Inject
	@Named("jxJaxb2Marshaller")
	private Jaxb2Marshaller marshaller;

	@Bean(name = INPUT_CHANNEL + "Configuration")
	public IntegrationFlow configureChannel() {
		return configure(INPUT_CHANNEL,
				"classpath:jx/xslt/BSACustInq-to-BSACustInqRequest.xslt",
				"classpath:jx/xslt/BSACustInqResponse-to-BSACustInqResponse.xslt", marshaller);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Message<BSACustInqResponse> handleMessage(Message<BSACustInqSrchRequest> message) {
		return super.handleMessage(message);
	}

	@Override
	public BSACustInqResponse handlePayload(final BSACustInqSrchRequest payload) {

		List<MessageRecord> errorMessages = Lists.newArrayList();
		
		BSACustInqResponse response = new BSACustInqResponse();

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
		
		response.setCustId(c.getId().toString());
		response.setAccountId("Account:123");
		response.setPersonName(c.getName());
		response.setAddr(c.getAddress().toString());
		response.setNaicsCode(String.valueOf(c.getNAICS()));
		response.setBirthDate(c.getBirthDate().toString());
		response.setLastMaintDate(c.getMaintDate().toString());

		return response;
	}
}
