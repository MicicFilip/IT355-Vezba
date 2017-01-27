<%-- 
    Document   : instrument
    Created on : Jan 26, 2017, 11:32:44 PM
    Author     : Filip
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@include file="partials/header.jsp" %>
<h1>Add Store</h1>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="dodajStore" value="/addStore" ></c:url>
        <form:form method="POST" accept-charset="UTF-8" action="${dodajStore}" modelAttribute="store">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="name">
                    Name
                </form:label>
                <form:input type="name" class="form-control" id="name" placeholder="Name" path="name" />
            </div>
            <div class="form-group">
                <form:label path="address">
                    Address
                </form:label>
                <form:input type="address" class="form-control" id="address" placeholder="Address" path="address" />
            </div>
            
            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">ADD</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty stores}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${stores}" var="sto">

                        <tr>
                            <td>${sto.name}</td>
                            <td>${sto.address}</td>
                            
                            <td><a href="<c:url value='/editStore/${sto.id}' />">edit</a></td>
                            <td><a href="<c:url value='/deleteStore/${sto.id}' />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>      

</div>
<%@include file="partials/footer.jsp" %>