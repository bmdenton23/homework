<?xml version="1.0"?>
<!-- Yellow Hammer Jack Henry & Associates, Inc. Copyright ©2015-2016 Jack Henry Software. All Rights Reserved. -->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns="http://jackhenry.com/jxchange/TPG/2008"
	xmlns:java="http://xml.apache.org/xslt/java">


	<xsl:template match="AddrSrchResponse">
		<ns:AddrSrchResponse>
			<ns:SrchMsgRsHdr>
				<xsl:copy-of select="//inboundMessage//ns:SrchMsgRqHdr/ns:jXchangeHdr" />
			</ns:SrchMsgRsHdr>

			<xsl:if test="addressRecords">
				<ns:AddrSrchRecArray>
					<xsl:apply-templates select="addressRecords" />
				</ns:AddrSrchRecArray>
			</xsl:if>
		</ns:AddrSrchResponse>
	</xsl:template>
	
	<xsl:template match="addressRecord">
		<ns:AddrSrchRec>
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
			
			<xsl:if test="address">
				<ns:Address>
					<xsl:value-of select="address" />
				</ns:Address>
			</xsl:if>
		</ns:AddrSrchRec>
	</xsl:template>
</xsl:stylesheet>