<%-- 
    Document   : instrument
    Created on : Jan 26, 2017, 11:32:44 PM
    Author     : Filip
--%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@include file="partials/header.jsp" %>
<h1>Add Instrument</h1>
<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <c:url var="addInstrument" value="/addInstrument" ></c:url>
        <form:form method="POST" accept-charset="UTF-8" action="${addInstrument}" modelAttribute="instrument">
            <% String success = (String) request.getAttribute("successMsg");%>
            <%= (success != null) ? "<div class=\"alert alert-success\">" + success + "</div>" : ""%>
            <form:input type="hidden" id="instrumentId" class="form-control" placeholder="instrumentId" path="instrumentId" />
            
            <div class="form-group">
                <form:label path="instrumentName">
                    Instrument Name
                </form:label>
                <form:input type="instrumentName" class="form-control" id="instrumentName" placeholder="Name" path="instrumentName" />
            </div>
            
            <div class="form-group">
                <form:label path="instrumentPrice">
                    Instrument Price
                </form:label>
                <form:input type="instrumentPrice" class="form-control" id="instrumentPrice" placeholder="instrumentPrice" path="instrumentPrice" />
            </div>
            
            <div class="form-group">
                <form:label for="categoryId" path="categoryId">Category</form:label>

                <form:select id="slcRole" class="form-control" path="categoryId">
                    <form:option value="">SELECT</form:option>
                    <form:options items="${categories}" itemValue="id" itemLabel="categoryName" />
                </form:select>
            </div>
                    
              
            
            <button type="submit" class="btn btn-primary">Add</button>
        </form:form>
    </div>
        
    <div class="col-md-8 col-md-offset-2">
        <c:if test="${!empty instruments}">

            <table class="table table-striped mojatabela">
                <thead>
                    <tr>
                        <th>Instrument Name</th>
                        <th>Instrument Price</th>
                        <th>Category</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach items="${instruments}" var="inst">

                        <tr>
                            <td>${inst.instrumentName}</td>
                            <td>${inst.instrumentPrice}$</td>
                            <td>${inst.categoryId}</td>
                            <td><a href="<c:url value='/edit_rasa/${rasa.id}' />">edit</a></td>
                            <td><a href="<c:url value='/delete_rasa/${rasa.id}' />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>      

</div>
<%@include file="partials/footer.jsp" %>