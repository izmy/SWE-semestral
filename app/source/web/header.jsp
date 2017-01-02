<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://v4-alpha.getbootstrap.com/favicon.ico">

    <title>Semestrální práce MI-SWE - hrachjar</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/style.css" rel="stylesheet">
    <script src="http://canvasjs.com/assets/script/canvasjs.min.js" type="text/javascript"></script>
  </head>

  <body>
      
    <nav class="navbar navbar-static-top navbar-dark bg-inverse">
      <div class="container">
        <a class="navbar-brand" href="index.jsp">MI-SWE</a>
        <ul class="nav navbar-nav">
          <li class="nav-item">
            <a class="nav-link ${ param.active == "index" ? "active" : ""}" href="index.jsp">Hlavní stránka</a>
          </li>
          <li class="nav-item">
            <a class="nav-link ${ param.active == "prehled-hdp" ? "active" : ""}" href="prehledHDP">Přehled HDP</a>
          </li>
          <li class="nav-item">
            <a class="nav-link ${ param.active == "spolecnosti" ? "active" : ""}" href="spolecnosti">Společnosti</a>
          </li>
          <li class="nav-item">
            <a class="nav-link ${ param.active == "construct" ? "active" : ""}" href="construct">Construct dotaz</a>
          </li>
        </ul>
      </div>
    </nav>