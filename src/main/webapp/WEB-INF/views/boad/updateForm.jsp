<%@include file="../layout/header.jsp"%>



<div class = "container">
 <form>
  <input type="hidden" id="id" value="${boad.id}"/>
  <div class="form-group">
   <label for="title">Content:</label>
   <input value="${boad.title}" class="form-control" placeholder="Enter title" id="title">
  </div>

  <div class="form-group">
   <label for="content">Content:</label>
   <textarea class="form-control summernote" rows="5" id="content">${boad.content}</textarea>
  </div>


 </form>
 <button id="btn-update" class="btn btn-primary">update</button>
</div>
<script src="/js/boad.js"></script>



<script>
 $('.summernote').summernote({
  placeholder: 'content',
  tabsize: 2,
  height: 300
 });
</script>

<%@include file="../layout/footer.jsp"%>