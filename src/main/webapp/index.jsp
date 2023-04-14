<%--Author: Karthik Manjunath--%>
<%--Andrew ID: kmanjuna--%>
<%@ page import="org.bson.Document" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        .most-popular {
            font-weight: bold;
            font-size: 1.2em;
            color: #336699;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<%--Get information from the servlet request--%>
<h1>Dashboard</h1>
<h3 class="most-popular">Most Popular Source: <%=request.getAttribute("mostPopularSource")%>
</h3>
<h3 class="most-popular">Most Popular Target: <%=request.getAttribute("mostPopularTarget")%>
</h3>
<h3 class="most-popular">Average Translation Speed (ms): <%=request.getAttribute("avgTranslationSpeed")%>
</h3>
<br>
<h2>Logs</h2>
<table>
    <thead>
    <tr>
        <th>Input Text</th>
        <th>Source</th>
        <th>Target</th>
        <th>Translated Text</th>
        <th>Translation Speed (ms)</th>
        <th>Date & Time</th>
    </tr>
    </thead>
    <tbody>
    <%--Display the information in a table--%>
    <% List<Document> documents = (List<Document>) request.getAttribute("documents");
        for (Document document : documents) { %>
    <tr>
        <td><%=document.get("inputText")%>
        </td>
        <td><%=document.get("source")%>
        </td>
        <td><%=document.get("target")%>
        </td>
        <td><%=document.get("translatedText")%>
        </td>
        <td><%=document.get("translationSpeed")%>
        </td>
        <td><%=document.get("timeOfTranslation")%>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
