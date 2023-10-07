<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Вступ</h1>
<p>
    Новый проект - архетип webapp.
    Для запуска проекта необходим веб сервер. Варианты:
    Tomcat
</p>
<%
    int x = 10;
%>

<p>x = 5 = <%= x + 5%></p>

<%
    for(int i = 0; i <= 5; i++)
    {
%>
<li>item # <%= i %></li>

<% } %>
<jsp:include page="fragment.jsp"/>

