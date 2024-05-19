<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Liste des CV</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f8f9fa;
                        color: #333;
                        margin: 0;
                        padding: 0;
                        display: flex;
                        flex-direction: column;
                        align-items: center;
                    }
                    h1 {
                        color: #007bff;
                        margin: 20px 0;
                    }
                    table {
                        border-collapse: collapse;
                        width: 80%;
                        margin: 20px 0;
                        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    }
                    th, td {
                        padding: 12px 15px;
                        text-align: left;
                    }
                    th {
                        background-color: #007bff;
                        color: white;
                        font-weight: bold;
                    }
                    tr:nth-child(even) {
                        background-color: #f2f2f2;
                    }
                    tr:hover {
                        background-color: #e0e0e0;
                    }
                </style>
            </head>
            <body>
                <h1>Liste des CV</h1>
                <table border="1">
                    <tr>
                        <th>Id</th>
                        <th>Genre</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Objectif</th>
                        <th>Status</th>
                        <th>Diplôme</th>
                    </tr>
                    <xsl:apply-templates/>
                </table>
            </body>
        </html>
    </xsl:template>
  
    <xsl:template match="cv">
        <tr>
            <td><xsl:value-of select="id"/></td>
            <td><xsl:value-of select="identité/genre"/></td>
            <td><xsl:value-of select="identité/nom"/></td>
            <td><xsl:value-of select="identité/prenom"/></td>
            <td><xsl:value-of select="objectif"/></td>
            <td><xsl:value-of select="objectif/@status"/></td>
            <td><xsl:value-of select="diplome"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
