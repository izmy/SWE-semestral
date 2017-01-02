<%-- 
    Document   : construct
    Created on : 2.1.2017, 7:04:39
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">
    <jsp:param name="active" value="construct" />
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div class="main">
        <div class="container">
            <h1>Construct dotaz</h1>
            <table>
                 <tbody>
                <c:forEach items="${listConstruct}" var="construct">
                    <tr>
                        <td>${construct}</td>
                    </tr>
                </c:forEach>
                 </tbody>
            </table>
        </div>
    </div>
        
<jsp:include page="footer.jsp"></jsp:include>