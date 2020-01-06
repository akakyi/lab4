<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Profiles:</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Id</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Class level</th>
                        <th>Profile type</th>
                    </tr>
                    <xsl:for-each select="profiles/profiles_array/profile">
                        <tr>
                            <td>
                                <xsl:value-of select="id"/>
                            </td>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="age"/>
                            </td>
                            <td>
                                <xsl:value-of select="class_level"/>
                            </td>
                            <td>
                                <xsl:value-of select="profile_type"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>