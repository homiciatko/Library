<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/WEB-INF/include/header.jsp" %>
<%@ include file="/WEB-INF/include/navbar.jsp" %>

<c:url value="/book-create" var="createBookURL" />
<c:url value="/books" var="booksURL" />

<div class="container">

    <c:if test="${book.id == null}" >
        <h1>Create book</h1>
    </c:if>
    <c:if test="${book.id != null}" >
        <h1>Edit book</h1>
    </c:if>

    <div class="row">
        <form:form commandName="book" action="${createBookURL}" method="post" role="form" class="form-horizontal">
            <form:hidden  path="id"/>

            <div class="form-group">
                <label class="control-label col-sm-2" for="author">Author:</label>
                <div class="col-sm-6">
                    <form:input path="author" type="text" id="author" class="form-control" placeholder="Enter author" aufocus="autofocus" />
                    <form:errors path="author" cssStyle="color: red"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="title">Title:</label>
                <div class="col-sm-6">
                    <form:input path="title" type="text" id="title" class="form-control" placeholder="Enter title" />
                    <form:errors path="title" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="available">Availabilty:</label>
                <div class="col-sm-6">
                    <form:input path="available" type="number" id="available" class="form-control" placeholder="Enter available" />
                    <form:errors path="available" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="${booksURL}" class="btn btn-danger">Cancel</a>
                </div>
            </div>
        </form:form>
    </div>

</div>

<%@ include file="/WEB-INF/include/footer.jsp" %>