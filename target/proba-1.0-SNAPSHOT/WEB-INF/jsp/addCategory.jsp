<%-- 
    Document   : addCategory
    Created on : Jan 26, 2017, 11:01:12 PM
    Author     : Filip
--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%@include file="partials/header.jsp" %>
<h1>Dodaj vrstu zivotinje</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="addCategory" value="/addCategory" ></c:url>
        <form:form method="POST" action="${addCategory}" modelAttribute="category">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="categoryName">
                    Name
                </form:label>
                <form:input type="categoryName" class="form-control" id="categoryName" placeholder="Enter category name here" path="categoryName" />
            </div>

            <form:input type="hidden" id="categoryId" class="form-control" placeholder="categoryId" path="categoryId" />
            <button type="submit" class="btn btn-primary">Add</button>
        </form:form>
    </div>
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty categories}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Category Name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${categories}" var="cats">

                        <tr>
                            <td>${cats.categoryName}</td>
                            <td><a href="<c:url value='/editCategory/${cats.categoryId}' />">edit</a></td>
                            <td><a href="<c:url value='/deleteCategory/${cats.categoryId}' />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>    
</div>

<%@include file="partials/footer.jsp" %>
