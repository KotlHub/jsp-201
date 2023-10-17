<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String pageBody = (String) request.getAttribute( "page-body" ) ;
    String context = request.getContextPath() ;   //  /JavaWeb201  - Deploy - App context
%>
<!doctype html>
<html>
<head>
    <title>Java web</title>
    <!--Import Google Icon Font-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="<%= context %>/css/site.css?time=<%= new Date().getTime() %>" />
</head>
<body>
<nav>
    <div class="nav-wrapper light-green">
        <!-- Modal Trigger -->
        <a class="right modal-trigger auth-icon" href="#auth-modal"><i
                class="material-icons">exit_to_app</i></a>
        <a href="<%= context %>/" class="right site-logo">Java 201</a>
        <ul id="nav-mobile" >
            <li><a href="#">JSP</a></li>
            <li <%= "filters.jsp".equals(pageBody) ? "class='active'" : "" %>
            ><a href="<%= context %>/filters">Filters</a></li>

            <li <%= "ioc.jsp".equals(pageBody) ? "class='active'" : "" %>
            ><a href="<%= context %>/ioc">IoC</a></li>

            <li <%= "db.jsp".equals(pageBody) ? "class='active'" : "" %>
            ><a href="<%= context %>/db">DB</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <!-- Page Content goes here -->
    <jsp:include page="<%= pageBody %>"/>
</div>
<footer class="page-footer light-green">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Step Learning</h5>
                <p class="grey-text text-lighten-4">Open your mind</p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Good to visit</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="https://materializecss.com/">Materialize CSS</a></li>
                    <li><a class="grey-text text-lighten-3" href="https://fonts.google.com/icons">Materialize Icons</a></li>
                    <li><a class="grey-text text-lighten-3" href="https://planetscale.com/">PlanetScale platform</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2014 IT Step Academy
            <a class="grey-text text-lighten-4 right" href="https://itstep.org/">IT Step Home</a>
        </div>
    </div>
</footer>

<!-- Modal Structure -->
<div id="auth-modal" class="modal">
    <div class="modal-content">
        <h4>Автентифікація на сайті</h4>
        <p>A bunch of text</p>
    </div>
    <div class="modal-footer">
        <a href="<%= context %>/signup"
           class="modal-close waves-effect waves-green btn-flat light-green lighten-3"
        >Реєстрація</a>
        <a href="#!"
           class="waves-effect waves-green btn-flat light-green lighten-4"
        >Вхід</a>
    </div>
</div>

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!-- Site JS -->
<script src="<%= context %>/js/site.js?time=<%= new Date().getTime() %>"></script>
</body>
</html>