<%@include file="../layout/header.jsp"%>



<div class = "container">
 <button class = "btn btn-secondary" onclick="history.back()">back</button>
    <c:if test="${boad.user.id == principal.user.id}">
        <a href="/boad/${boad.id}/updateForm" class = "btn btn-warning">Modify</a>
        <button id = "btn-delete" class = "btn btn-danger">delete</button>
    </c:if>
<br/><br/>
    <div>
        number : <span id ="id"><i>${boad.id}</i></span>
        user : <span><i>${boad.user.username}</i></span>
    </div>
  <div class="form-group">
   <h3>${boad.title} </h3>
  </div>
  <hr/>
  <div class="form-group">
   <div>
    ${boad.content}
   </div>
  </div>
  <hr/>
</div>
<script src="/js/boad.js"></script>

<%@include file="../layout/footer.jsp"%>