<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Tax Return</title>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body class="body-wrapper">

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <nav class="navbar navbar-expand-lg navbar-light navigation">
                    <a class="navbar-brand fa fa-home fa-3x" href="${pageContext.request.contextPath}/taxreturn">
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto main-nav ">
                            <li class="nav-item ">
                                <a class="nav-link" href="${pageContext.request.contextPath}/taxreturn/new-tax-return">New
                                    tax return</a>
                            </li>

                            <li class="nav-item ">
                                <a class="nav-link"
                                   href="${pageContext.request.contextPath}/taxreturn/action-report-list">Action report
                                    list</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="${pageContext.request.contextPath}/taxreturn/history">History</a>
                            </li>

                        </ul>
                        <ul class="navbar-nav ml-auto mt-10">
                            <li class="nav-item">
                                <label class="nav-link " style="font-size: 30px">Hello, ${fullname}</label>
                            </li>
                            <li class="nav-item logoutButton">
                                <a class="nav-link login-button"
                                   href="${pageContext.request.contextPath}/taxreturn/logout">Logout</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</section>

<section class="overly  section-sm">
    <!-- Container Start -->
    <div class="container">
        <div class="row justify-content-md-center text-center">
            <div class="col-md-8">
                <div style="size: auto; color: #07ad76">
                </div>
            </div>
        </div>
    </div>
    <!-- Container End -->
</section>
<%--<c:if test="${ not empty userActionReportList}">--%>
<section class="dashboard section">
    <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-0 w-100">
        <!-- Recently Favorited -->
        <div class="widget dashboard-container my-adslist" style="background: #F5F5F5">
            <h3 class="widget-header">User's tax returns</h3>

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Action</th>
                    <th scope="col">Message</th>
                    <th scope="col">Date</th>
                </tr>
                </thead>
                <tbody>
                <%--//TODO сделать сортировку по кнопкам--%>
                <c:forEach items="${historyList}" var="history">
                    <tr>
                        <td class="w-25"><span class="categories ol1"><c:out
                                value="${history.getAction()}"/></span></td>
                        <td class="w-25"><span class="categories col1"><c:out
                                value="${history.getMessage()}"/></span>
                        </td>
                        <td class="w-25"><span class="categories col1"><c:out
                                value="${history.getDate()}"/></span></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>
<%--</c:if>--%>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>



