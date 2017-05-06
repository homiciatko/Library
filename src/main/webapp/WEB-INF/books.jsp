<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/include/header.jsp" %>
<%@ include file="/WEB-INF/include/navbar.jsp" %>

<c:url value="/books/delete" var="deleteBookURL" />
<c:url value="/books/edit" var="editBookURL" />
<c:url value="/rent/book" var="rentURL" />


<c:if test="${cantRent}">
<div class="alert alert-danger">
    tej ksiazki nie mozna wypozyczyc ŹÓŁW
</div>
</c:if>

<div class="container">

    <h1>List of books</h1>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th class="text-center col-md-1">Id</th>
                    <th class="text-center">Title</th>
                    <th class="text-center">Author</th>
                    <th class="text-center">Available</th>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th class="text-center">Edit</th>
                        <th class="text-center">Delete</th>
                    </sec:authorize>
                    <th class="text-center">Rent</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${bookList}" var="book">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.available}</td>
                        <sec:authorize access="hasRole('ADMIN')">
                          <td> <a href="${editBookURL}/${book.id}" class="btn btn-primary edit-btn">Edit</a> </td>
                          <td> <a href="${deleteBookURL}/${book.id}" class="btn btn-danger delete-btn">Delete</a> </td>
                        </sec:authorize>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${book.available > 0}">
                                    <a href="${rentURL}/${book.id}" class="btn btn-info btn-sm">Rent</a>
                                </c:when>
                                <c:otherwise>
                                    Brak
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

<%@ include file="/WEB-INF/include/footer.jsp" %>