<%@include file="layout/header.jsp"%>
<div class="container">

    <c:forEach var = "boad" items="${boads.content}">
        <div class="card m-2">
            <div class="card-body">
                <h4 class="card-title">${boad.title}</h4>
                <a href="/boad/${boad.id}" class="btn btn-primary">Detail</a>
            </div>
        </div>
    </c:forEach>
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test='${boad.first}'>
                <li class="page-item disabled"><a class="page-link" href="?page=${boad.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boad.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${boad.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boad.number+1}">next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boad.number+1}">next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<%@include file="layout/footer.jsp"%>