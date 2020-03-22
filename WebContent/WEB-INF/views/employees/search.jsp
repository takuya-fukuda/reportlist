<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <table id="employee_list">
            <tbody>
                <tr>

                    <th>氏名</th>

                </tr>


                    <tr class="row${status.count % 2}">

                        <td><c:out value="${employee.name}" /></td>

                    </tr>


            </tbody>
        </table>

        <p><a href="<c:url value='/employees/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>