<%-- 
    Document   : addInventory
    Created on : Jan 27, 2017, 7:24:38 PM
    Author     : Micic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%@include file="partials/header.jsp" %>

<h1>Add Inventory</h1>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="dodajInventory" value="/addInventory" ></c:url>
        <form:form method="POST" accept-charset="UTF-8" action="${dodajInventory}" modelAttribute="inventory">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <div class="form-group">
                <form:label path="quantity">
                    Quantity
                </form:label>
                <form:input type="quantity" class="form-control" id="quantity" placeholder="Quantity" path="quantity" />
            </div>
             <div class="form-group">
                <form:label path="datum">
                    Date
                </form:label>
                <form:input type="datum" class="form-control" id="datum" placeholder="Datum" path="datum" />
            </div>
              <div class="form-group">
                <form:label for="categoryId" path="categoryId">Category</form:label>

                <form:select id="slcRole" class="form-control" path="categoryId">
                      <form:option value="">SELECT</form:option>
                    <form:options items="${categories}" itemValue="id" itemLabel="categoryName" />
                </form:select>
            </div>
            <div class="form-group">
                <form:label for="instrumentId" path="instrumentId">Instrument</form:label>

                <form:select id="slcRole" class="form-control" path="instrumentId">
                      <form:option value="">SELECT</form:option>
                    <form:options items="${instruments}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>
            <div class="form-group">
                <form:label for="storeId" path="storeId">Store</form:label>

                <form:select id="slcRole" class="form-control" path="storeId">
                      <form:option value="">SELECT</form:option>
                    <form:options items="${stores}" itemValue="id" itemLabel="name" />
                </form:select>
            </div>     
           
            <form:input type="hidden" id="id" class="form-control" placeholder="id" path="id" />
            <button type="submit" class="btn btn-primary">ADD</button>
        </form:form>
    </div>
            <div class="col-md-8 col-md-offset-2">
            <c:if test="${!empty inventories}">

                <table class="table table-striped mojatabela">
                    <thead>
                        <tr>
                            <th>Quantity</th>
                            <th>Date</th>
                            <th>Category</th>
                            <th>instrument</th>
                            <th>Store Name</th>
                            <th>Store Address</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <c:forEach items="${inventories}" var="inv">

                            <tr>
                                <td>${inv.quantity}</td>
                                <td>${inv.datum}</td>
                                <td>${inv.categoryId}</td>
                                <td>${inv.instrumentId}</td>
                                <td>${inv.storeId.name}</td>
                                <td>${inv.storeId.address}</td>
                                <td><a href="<c:url value='/editInventory/${inv.id}' />">edit</a></td>
                                <td><a href="<c:url value='/deleteInventory/${inv.id}' />">delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>      
        
  </div>


<%@include file="partials/footer.jsp" %>