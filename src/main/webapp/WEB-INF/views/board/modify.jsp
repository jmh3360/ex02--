<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file = "../includes/header.jsp" %>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form action="/board/modify" role="form" method="post">
					<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }" />' />
					<input type="hidden" name="amount" value='<c:out value="${cri.amount }" />' />
					<input type="hidden" id="keyword" name="keyword" value='<c:out value="${cri.keyword }" />' />
					<input type="hidden" id="type" name="type" value='<c:out value="${cri.type }" />' />
					<div class="form-group">
						<label>Bno</label>
						<input class="form-control" name="bno" value='<c:out value="${board.bno }" />' readonly="readonly"/>
					</div>
					<div class="form-group">
						<label>Title</label>
						<input class="form-control" name="title" value='<c:out value="${board.title}" />' />
					</div>
					<div class="form-group">
						<label>Title</label>
						<textarea name="content" class="form-control" rows="3"  >
							<c:out value="${board.content}" />
						</textarea>
					</div>
					<div class="form-group">
						<label>Writer</label>
						<input class="form-control" name="writer" value='<c:out value="${board.writer }" />' readonly="readonly" />
					</div>
					<div class="form-group">
						<label for="">Regdate</label>
						<input type="text" class="form-control" name="regDate" 
						value='<fmt:formatDate pattern ="yyyy/MM/dd" value="${board.regdate }" />' readonly="readonly" />
					</div>
					<div class="form-group">
						<label for="">Update date</label>
						<input type="text" class="form-control" name="updatedate" 
						value='<fmt:formatDate pattern ="yyyy/MM/dd" value="${board.updatedate}" />' readonly="readonly" />
					</div>
					<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
					<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
					<button type="submit" data-oper='list' class="btn btn-info">List</button>
					</form>
				</div>
			</div>
		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
<!-- /.row -->
<script>
$(document).ready(function(){
	
	var formObj = $('form');
	$('button').click(function(e){
		/* 기본 폼 action 이벤트를 막는 거겠지 아마? */
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		console.log(operation);
		if (operation === 'remove') {
			formObj.attr("action","/board/remove");
		}else if(operation === 'list'){
			//move to list
			formObj.attr({"action":"/board/list","method":"get"});
			var pageNumTag = $('input[name=pageNum]').clone();
			var amountTag = $('input[name=amount]').clone();
			var keywordTag = $('input[name=keyword]').clone();
			var typeTag = $('input[name=type]').clone();
			
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);
		}
		formObj.submit();
	});
	
});


</script>
<%@ include file="../includes/footer.jsp" %>