<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Schools:</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Id</th>
                        <th>Name</th>
                    </tr>
                    <xsl:for-each select="schools/schools_array/school">
                        <tr>
                            <td>
                                <xsl:value-of select="id"/>
                            </td>
                            <td>
                                <a>
                                    <xsl:attribute name="href">
                                        <xsl:value-of select="concat('/lab3/profiles_of_school/', id)"/>
                                    </xsl:attribute>
                                    <xsl:value-of select="name"/>
                                </a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>