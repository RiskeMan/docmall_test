<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    
  <header class="main-header">

    <!-- Logo -->
    <a href="/admin/admin_menu" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>DM</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Admin</b>DocMall</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
      
		<ul class="nav navbar-nav">

			<li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <a href="/">[DocMall]</a>
          </li>

        </ul>

        <ul class="nav navbar-nav">

          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <!-- <a>[이전 로그인 시간 : <fmt:formatDate value="${sessionScope.adminStatus.admin_visit_date }" pattern="yyyy-MM-dd hh:mm:ss"/>]</a> -->
            <!-- <b>[이전 로그인 시간 : <fmt:formatDate value="${sessionScope.loginStatus.mbsp_lastlogin }" pattern="yyyy-MM-dd hh:mm:ss"/>]</b> -->
            <a>[로그인 시간 : <fmt:formatDate value="${admin_visit_date}" pattern="yyyy-MM-dd hh:mm:ss"/>]</a>
          </li>

        </ul>

        <c:if test="${sessionScope.adminStatus != null}">
        <ul class="nav navbar-nav">

          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <a href="/admin/logout">로그아웃</a>
          </li>

        </ul>
        </c:if>
      </div>
    </nav>
    
  </header>