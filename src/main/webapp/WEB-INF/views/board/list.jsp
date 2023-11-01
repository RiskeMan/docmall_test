<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


			<!doctype html>
			<html lang="en" class="h-100">

			<head>
				<meta charset="utf-8">
				<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
				<meta name="description" content="">
				<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
				<meta name="generator" content="Hugo 0.101.0">
				<title>Sticky Footer Navbar Template · Bootstrap v4.6</title>

				<link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/sticky-footer-navbar/">



				<!-- Bootstrap core CSS -->
				<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
					integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
					crossorigin="anonymous">


				<style>
					.bd-placeholder-img {
						font-size: 1.125rem;
						text-anchor: middle;
						-webkit-user-select: none;
						-moz-user-select: none;
						-ms-user-select: none;
						user-select: none;
					}

					@media (min-width : 768px) {
						.bd-placeholder-img-lg {
							font-size: 3.5rem;
						}
					}

					.search-container {
					    text-align: right;
					}
				</style>


				<!-- Custom styles for this template -->
				<link href="/resources/sticky-footer-navbar.css" rel="stylesheet">
			</head>

			<body class="d-flex flex-column h-100">

				<header>
					<!-- Fixed navbar -->
					<%@include file="/WEB-INF/views/comm/header.jsp" %>
				</header>

				<%-- <c: --%>

					<!-- Begin page content -->
					<main role="main" class="flex-shrink-0">
						<div class="container">
							<section>
								<div class="row">
									<!-- row는 하위 div를 12개를 사용 할 수 있다. -->
									<!-- <div class="col-해상도-숫자(하위 div에서 사용하는 사이즈. 총합이 12가 되어야 한다.)"></div> -->
									<div class="col-md-12">

										<div class="box">
											<div class="box-header with-border">
												<h3 class="box-title">List</h3>
											</div>

											<div class="box-body">

												<table class="table table-bordered">
													<tbody>
														<tr>
															<th style="width: 10%">번호</th>
															<th style="width: 50%">제목</th>
															<th style="width: 20%">작성자</th>
															<th style="width: 200px">등록일</th>
															<th style="width: 10%">조회수</th>
														</tr>

														<c:forEach items="${ list }" var="board">
															<tr>
																<td>${ board.bno }</td>
																<td><a class="move" href="#" data-bno="${ board.bno }">${ board.title }</a></td>
																<td>${ board.writer }</td>
																<td>
																	<fmt:formatDate value="${ board.regdate }"
																		pattern="yyyy/MM/dd hh:mm:ss" />
																</td>
																<td>${ board.viewcount }</td>
															</tr>
														</c:forEach>

													</tbody>
												</table>
											</div>


											<div class="box-footer clearfix">
												<div class="row">
													<div class="col-12">
														<nav aria-label="Page navigation example">
															<ul class="pagination">

																<!-- 이전 표시 여부 -->
																<c:if test="${pageMaker.prev }">
																	<li class="page-item"><a class="page-link"
																			href="/board/list?pageNum=${pageMaker.startPage -1 }">Previous</a>
																	</li>
																</c:if>

																<!-- 페이지 번호 출력 -->
																<c:forEach begin="${pageMaker.startPage }"
																	end="${pageMaker.endPage }" var="num">
																	<li class='page-item ${pageMaker.cri.pageNum == num ? "active":"" }'aria-current="page">
																		<a class="page-link movepage" href="#" data-page="${num }" >${num }</a></li>
																</c:forEach>

																<!-- 다음 표시 여부 -->
																<c:if test="${pageMaker.next }">
																	<li class="page-item"><a class="page-link"
																			href="/board/list?pageNum=${pageMaker.endPage +1 }">Next</a>
																	</li>
																</c:if>

															</ul>
														</nav>
													</div>
												</div>
												<div class="search-container">
													<form action="/board/list" method="get">
														<select name="type">
															<option selected>검색 종류</option>
															<option value="T" ${ pageMaker.cri.type == 'T'? 'selected': '' }>제목</option>
															<option value="C" ${ pageMaker.cri.type == 'C'? 'selected': '' }>내용</option>
															<option value="W" ${ pageMaker.cri.type == 'W'? 'selected': '' }>작성자</option>
															<option value="TC" ${ pageMaker.cri.type == 'TC'? 'selected': '' }>제목 or 내용</option>
															<option value="TW" ${ pageMaker.cri.type == 'TW'? 'selected': '' }>제목 or 작성자</option>
															<option value="TWC" ${ pageMaker.cri.type == 'TWC'? 'selected': '' }>제목 or 작성자 or 내용</option>
														</select>
														<input type="text" name="keyword" value="${ pageMaker.cri.keyword }" />
														<input type="hidden" name="pageNum" value="${1}" />
														<input type="hidden" name="amount" value="${pageMaker.cri.amount}" />
														<button type="submit" class="btn btn-primary">검색</button>
													</form>
													
													<!-- 1) [이전] 1 2 3 4 ... 9 10 [다음] 페이지 번호를 클릭할 때. action="/board/list" -->
													<!-- 2) 목록에서 제목 클릭할 때 사용. action="/board/get" -->
													<form	id="actionForm" action="/board/list" method="get">
														<input type="hidden" name="pageNum" id="pageNum" value="${pageMaker.cri.pageNum}" />
														<input type="hidden" name="amount" id="amount" value="${pageMaker.cri.amount}" />
														<input type="hidden" name="type" id="type" value="${pageMaker.cri.type}" />
														<input type="hidden" name="keyword" id="keyword" value="${pageMaker.cri.keyword}" />
														<input type="hidden" name="bno" id="bno" />
													</form>
												</div>
												<a class="btn btn-primary" href="/board/register" role="button">글쓰기</a>

											</div>
										</div>

									</div>
								</div>
							</section>
						</div>
					</main>

					<footer class="footer mt-auto py-3">
						<%@include file="/WEB-INF/views/comm/footer.jsp" %>
					</footer>

					<%@include file="/WEB-INF/views/comm/plugln.jsp" %>

					<script>

						// 폼태그 참조.
						let actionForm = document.getElementById("actionForm");

						// <form id="actionForm" 태그를 참조하여, 필요한 정보를 변경 및 사용.
						// 1) 페이지 번호 클릭시 동작되는 이벤트 설정
						// <a class="movepage">1</a><a class="movepage">2</a><a class="movepage">3</a>...
						const movePages = document.getElementsByClassName("movepage");

						Array.from(movePages).forEach(function(mv_Page) {

							

							// actionForm 전송 작업
							mv_Page.addEventListener("click", function(event) {
								event.preventDefault();
								// console.log("페이지 번호" , event.target.dataset.page);
								// data-page="1"
								document.getElementById("pageNum").value = event.target.dataset.page;
								actionForm.setAttribute("action", "/board/list");
								actionForm.submit(); // /board/list
							});
						});

						// 2) 제목 클릭시 이벤트 설정 : 게시물 읽기
						// 10개의 <a class="move">제목</a> 태그를 참조해 forEach로 반복하여 입력해 Array형태로 저장하는 함수.
						const moves = document.getElementsByClassName("move");

						Array.from(moves).forEach(function(move) {
							// actionForm 전송 작업
							move.addEventListener("click", function(event) {
								event.preventDefault();

								// <a href-"#" data-bno="게시물번호" data-name="값" data-title="값"> 
								// 로 데이터를 받아 변수값에 저장한다.(dataset)
								let bno = event.target.dataset.bno;

								// bno제거작업
								// 목록에서 제목 클릭 후 게시물 읽기에서 뒤로버튼에 의하여 목록으로 돌아와
								// 다시 제목을 클릭하면, bno피라미터가 추가되기 떄문에,
								// 기존 bno피라미터를 삭제하여야 한다.
								document.getElementById("bno").remove();

								// <input type='hidden' name='bno' value='게시물번호'>
								// HTML DOM문법.
								const newInput = document.createElement("input");
								newInput.setAttribute("type", "hidden")
								newInput.setAttribute("name", "bno")
								newInput.setAttribute("id", "bno")
								newInput.setAttribute("value", bno)
								actionForm.appendChild(newInput);

								// actionForm.append("<input type='hidden' name='bno' value='" + bno + "'>");
								actionForm.setAttribute("action", "/board/get");

								actionForm.submit();
							});
						});

					</script>

			</body>

			</html>