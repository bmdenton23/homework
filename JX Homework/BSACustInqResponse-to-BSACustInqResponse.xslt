<?xml version="1.0"?>
<!-- Yellow Hammer Jack Henry & Associates, Inc. Copyright ©2015-2016 Jack 
	Henry Software. All Rights Reserved. -->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns="http://jackhenry.com/jxchange/TPG/2008"
	xmlns:java="http://xml.apache.org/xslt/java">


	<xsl:template match="CustInqResponse">
		<ns:CustInqResponse>

			<ns:SrchMsgRsHdr>
				<xsl:copy-of select="//inboundMessage//ns:MsgRqHdr/ns:jXchangeHdr" />
			</ns:SrchMsgRsHdr>

			<ns:CustRec>
				<ns:CustDetail>
					<xsl:if test="personName">
						<ns:PersonName>
							<xsl:value-of select="personName" />
						</ns:PersonName>
					</xsl:if>
					<xsl:if test="addr">
						<ns:Addr>
							<xsl:value-of select="addr" />
						</ns:Addr>
					</xsl:if>
					<xsl:if test="naicsCode">
						<ns:NAICSCode>
							<xsl:value-of select="naicsCode" />
						</ns:NAICSCode>
					</xsl:if>
					<xsl:if test="birthDate">
						<ns:BirthDt>
							<xsl:value-of select="birthDate" />
						</ns:BirthDt>
					</xsl:if>
					<xsl:if test="lastMaintDate">
						<ns:LastMainDt>
							<xsl:value-of select="lastMaintDate" />
						</ns:LastMainDt>
					</xsl:if>

				</ns:CustDetail>
				<xsl:if test="custId">
					<ns:CustId>
						<xsl:value-of select="custId" />
					</ns:CustId>
				</xsl:if>
				<xsl:if test="accountId">
					<ns:AccountId>
						<xsl:value-of select="accountId" />
					</ns:AccountId>
				</xsl:if>
			</ns:CustRec>

		</ns:CustInqResponse>
	</xsl:template>
</xsl:stylesheet>