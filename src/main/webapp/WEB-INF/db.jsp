
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>DataBase</h1>
<% String connected = request.getAttribute("connectionStatus").toString();%>
<p>connection status: <%=connected%></p>
<h2>Управління даними</h2>
<p>
    <button id="db-create-button"
            class="waves-effect waves-light btn light-green">
        <i class="material-icons right">cloud</i>
        create</button>

    <input name="user-name" placeholder="Ім'я">
    <input name="user-phone" placeholder="Телефон">
    <button id="db-insert-button"
            class="waves-effect waves-light btn light-green">
        <i class="material-icons right">phone_iphone</i>
        Замовити дзвінок</button>
    <br/>
    <u id="out"></u>
</p>