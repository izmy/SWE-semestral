<%-- 
    Document   : index
    Created on : 2.1.2017, 3:25:51
    Author     : admin
--%>

<%@page import="swe.models.GDP"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">
    <jsp:param name="active" value="prehled-hdp" />
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div class="main">
        <div class="container">
            <h1>Přehled HDP</h1>
                    <%
        Gson gsonObj = new Gson();
        Map<Object,Object> map = null;
        List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

        List<GDP> items = (List) request.getAttribute("listGDP");
        Integer i = 0;
        for(GDP listGDP : items) {
            map = new HashMap<Object,Object>(); map.put("y", Float.parseFloat(listGDP.getGDPValue()));  map.put("label", listGDP.getCountryName()); list.add(map);
            i++;
            if(i == 10) break;
        }
        String dataPoints1 = gsonObj.toJson(list);
        %>
        
        <div id="chartContainer" style="height: 600px; width: 100%; margin-bottom: 50px;"></div>
        <br><br>
        
            <table>
                 <thead>
                     <tr>
                         <th>Název země</th>
                         <th>HDP</th>
                     </tr>
                 </thead>
                 <tbody>
                <c:forEach items="${listGDP}" var="gdp">
                    <tr>
                        <td>${gdp.countryName}</td>
                        <td>${gdp.GDPValue}</td>
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
               text: "Přehled HDP (TOP 10)"
           },
           animationEnabled: true,
           axisY2: {
               valueFormatString: "$ 0",
           },
           toolTip: {
               shared: true
           },
           legend: {
               verticalAlign: "top",
               horizontalAlign: "center"
           },

           data: [
           {
               type: "stackedBar",
               showInLegend: true,
               name: "HDP",
               axisYType: "secondary",
               color: "#7E8F74",
               dataPoints: <%out.print(dataPoints1);%>
           }
           ]
       });
       chart.render();
   });
</script>