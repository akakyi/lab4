<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Error message:</h2>
                <xsl:value-of select="error/message"/>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>