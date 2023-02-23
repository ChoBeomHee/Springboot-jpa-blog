<%@include file="../layout/header.jsp"%>



<div class = "container">
 <form>
  <div class="form-group">
   <label for="title">Title:</label>
   <input type="text" class="form-control" placeholder="Enter title" id="title">
  </div>

  <div class="form-group">
   <label for="content">Content:</label>
   <textarea class="form-control summernote" rows="5" id="content"></textarea>
  </div>


 </form>
 <button id="btn-save" class="btn btn-primary">Finish</button>
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