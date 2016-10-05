<?xml version="1.0"?>
<!-- Yellow Hammer Jack Henry & Associates, Inc. Copyright ©2015-2016 Jack Henry Software. All Rights Reserved. -->
	
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:ns="http://jackhenry.com/jxchange/TPG/2008"
	xmlns:java="http://xml.apache.org/xslt/java">

	<xsl:variable name="location">AddrSrch</xsl:variable>


	<xsl:template match="ns:AddrSrch">
		<BSAAddrSrchRequest>
			<custId>
				<xsl:value-of select="ns:CustId" />
			</custId>
		</BSAAddrSrchRequest>
	</xsl:template>

</xsl:stylesheet>