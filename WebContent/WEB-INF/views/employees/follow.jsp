<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>フォローした従業員一覧</h2>
        <table id="employee_list">
            <tbody>
                <tr>
                    <th>フォローした従業員id</th>
                </tr>
                <c:forEach var="follower" items="${employees}" varStatus="status">


                    <tr class="row${status.count % 2}">
                        <td><c:out value="${follower.id}" /></td>
                        <td><c:out value="${follower.name}" /></td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
        <p><a href="<c:url value='/employees/index' />">一覧に戻る</a></p>

    </c:param>
</c:import>