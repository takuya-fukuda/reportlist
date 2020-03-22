<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>従業員一覧</h2>
        <table id="employee_list">
            <tbody>
                <tr>
                    <th>社員番号</th>
                    <th>氏名</th>
                    <th>操作</th>
                    <th>フォロー</th>

                </tr>
                <c:forEach var="employee" items="${employees}" varStatus="status">

                    <tr class="row${status.count % 2}">
                        <td><c:out value="${employee.code}" /></td>
                        <td><c:out value="${employee.name}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${employee.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/employees/show?id=${employee.id}' />">詳細を表示</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${employee.follow_flag > 0}">
                            <form method="POST" action="<c:url value='/unfollow' />">
                                <input type="hidden" name="_token" value="${_token}" />
                                <input type="hidden" name="follower" value="${employee.follow_flag}" />
                                <input type="submit" value = "フォロー中">
                                <c:out value="${employee.follow_flag}" />
                            </form>
                                </c:when>
                                <c:otherwise>
                            <form method="POST" action="<c:url value='/Follower/FollowerServlet' />">
                                <input type="hidden" name="_token" value="${_token}" />
                                <input type="hidden" name="follower" value="${employee.id}" />
                                <input type="submit" value = "フォロー">
                            </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>

        <div id="pagination">
            （全 ${employees_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((employees_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/employees/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/employees/new' />">新規従業員の登録</a></p>
        <p><a href="<c:url value='/SearchNewServlet' />">従業員の検索</a></p>

    </c:param>
</c:import>