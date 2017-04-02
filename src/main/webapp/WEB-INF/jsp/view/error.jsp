<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title><spring:message code='error.error'/></title>
<body>
	<template:main>

		<jsp:body>
<h4><spring:message code='error.error'/></h4>
<h5>${errorMessage}</h5>

</jsp:body>
	</template:main>
</body>
</html>