<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <c:url value="/rest/users" var="getRestUsers" />
        <c:url value="/rest/posts" var="getRestPosts" />
        <c:url value="/rest/posts/1" var="getPostsById" />
        <c:url value="/rest/delPosts/5" var="deletePostById"/>
        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">JDBC в Spring
                        <small>JDBCTemplate</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Rest services</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Content Row -->
            <div class="row">
                <!-- Sidebar Column -->
                <div class="col-md-3">
                    <div class="list-group">

                        <a href="index.html" class="list-group-item">Home</a>
                        <a href="${getRestUsers}" class="list-group-item">Get Rest Users </a>
                        <a href="${getRestPosts}" class="list-group-item">Get Rest Posts </a>
                        <a href="${getPostsById}" class="list-group-item">Get Rest Posts By Id </a>
                        <a href="${deletePostById}" class="list-group-item">Delete Post By Id </a>
                    </div>
                </div>
            </div>
            <!-- /.row -->

            <hr>

        </div>
        <!-- /.container -->

    </jsp:body>
</page:template>

