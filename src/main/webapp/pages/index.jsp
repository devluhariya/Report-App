<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Report Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3 class="pb-3 pt-3">Report Application</h3>

		<form:form action="search" modelAttribute="search" method="POST">
			<table>
				<tr>
					<td>Plan Name :</td>
					<td><form:select path="planName">
							<form:option value="">-select-</form:option>
							<form:options items="${names}" />
						</form:select></td>
					<td>Plan Status :</td>
					<td><form:select path="planStatus">
							<form:option value="">-select-</form:option>
							<form:options items="${status}" />
						</form:select></td>
					<td>Gender :</td>
					<td><form:select path="gender">
							<form:option value="">-select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Fe-Male">Fe-Male</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Start Date :</td>
					<td><form:input path="startDate" type="date" /></td>

					<td>End Date :</td>
					<td><form:input path="endDate" type="date" /></td>
				</tr>
				<tr>
					<td><a href="/" class="btn btn-danger bi bi-bootstrap-reboot">Reset</a></td>
					<td><input type="submit" value="search"
						class="btn btn-primary" /></td>
				</tr>

			</table>
			<p class="text-success">${msg}</p>
		</form:form>


		<hr />
		<table class="table table-striped table table-hover">
			<thead>
				<tr>
					<th>S.No.</th>
					<th>Holder First Name</th>
					<th>Holder Last Name</th>
					<th>Plan Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Benefit Amount</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plans}" var="plan" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${plan.citizenName}</td>
						<td>${plan.citizenLastName}</td>
						<td>${plan.planName}</td>
						<td>${plan.planStartDate}</td>
						<td>${plan.planEndDate}</td>
						<td>${plan.benefitAmt}</td>
						<td>${plan.planStatus}</td>
					</tr>
				</c:forEach>
				<tr>
					<c:if test="${empty plans}">
						<td colspan="8" style="text-align: center">No Records found</td>
					</c:if>
				</tr>
			</tbody>
		</table>

		<hr />
		Export : <a href="excel"><button type="button"
				class="btn btn-lg btn-outline-danger">
				<i class="fa fa-bug"></i> Excel
			</button></a> <a href="pdf"><button type="button"
				class="btn btn-lg btn-outline-warning">
				<i class="fa fa-file"></i>Pdf
			</button></a>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>