<%-- 
    Document   : index
    Created on : 2.1.2017, 3:25:51
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">
    <jsp:param name="active" value="index" />
</jsp:include>

    <div class="main">
        <div class="container">
            <h1>Semestrální práce MI-SWE - hrachjar</h1>
            <p class="claim">Tato semestrální práce obsahuje tři datasety - seznam nejlepších společností za rok 2014 podle Forbesu, seznam všech států a ekonomická data (HDP).</p>
            
            <h2>Přehled HDP</h2>
            <p>V této části je seznam všech zemí seřazených podle HDP dolněný grafem TOP 10 zemí.</p>
            <a class="btn btn-primary" href="prehledHDP">Přehled HDP</a>
            
            <h2>Počet společností podle zemí</h2>
            <p>V této části je přehled počtu společností podle zemí.</p>
            <a class="btn btn-primary" href="spolecnosti">Společnosti</a>
            
            <h2>Construct dotaz</h2>
            <p>V této části je construct dotaz, který obsahuje vypočítanou položku potenciálních zaměstnanců podle počtu obyvatel a nezaměstnanosti.</p>
            <a class="btn btn-primary" href="construct">Construct dotaz</a>
        </div>
    </div>
        
<jsp:include page="footer.jsp"></jsp:include>