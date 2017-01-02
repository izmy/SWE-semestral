<%-- 
    Document   : spolecnosti
    Created on : 2.1.2017, 6:38:48
    Author     : admin
--%>

<%@page import="swe.models.Company"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">
    <jsp:param name="active" value="spolecnosti" />
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div class="main">
        <div class="container">
            <h1>Počet společností podle zemí</h1>

                    <%
        Gson gsonObj = new Gson();
        Map<Object,Object> map = null;
        List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

        List<Company> items = (List) request.getAttribute("listCompany");
        Integer i = 0;
        for(Company listCompany : items) {
            map = new HashMap<Object,Object>(); map.put("y", Float.parseFloat(listCompany.getCountCompany()));  map.put("legendText", listCompany.getCountryName());  map.put("label", listCompany.getCountryName()); list.add(map);
            i++;
            if(i == 20) break;
        }
        String dataPoints = gsonObj.toJson(list);
        %>
        
        <div id="chartContainer" style="height: 600px; width: 100%; margin-bottom: 50px;"></div>
        
            <table>
                 <thead>
                     <tr>
                         <th>Název země</th>
                         <th>Počet společností</th>
                     </tr>
                 </thead>
                 <tbody>
                <c:forEach items="${listCompany}" var="company">
                    <tr>
                        <td>${company.countryName}</td>
                        <td>${company.countCompany}</td>
                    </tr>
                </c:forEach>
                 </tbody>
            </table>
        </div>
    </div>
        
<jsp:include page="footer.jsp"></jsp:include>

<script type="text/javascript">
   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           title: {
               text: "Počet společností podle zemí (TOP 20)"
           },
           animationEnabled: true,
           legend: {
               verticalAlign: "center",
               horizontalAlign: "left",
               fontSize: 20,
               fontFamily: "Helvetica"
           },
           theme: "theme2",
           data: [
           {
               type: "pie",
               indexLabelFontFamily: "Garamond",
               indexLabelFontSize: 20,
               indexLabel: "{label} {y}%",
               startAngle: -20,
               showInLegend: true,
               toolTipContent: "{legendText} {y}%",
               dataPoints: <%out.print(dataPoints);%>              
           }
           ]
       });
       chart.render();
   });
</script>