<%@ taglib prefix="sitemesh-page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="http://mottie.github.io/tablesorter/js/jquery.tablesorter.js"></script>
<link rel="stylesheet" href="http://mottie.github.io/tablesorter/css/theme.default.css"/>
<a href="${pageContext.request.contextPath}/beech/person/add">Add Person</a>

<table id="myTable">
    <thead>
    <tr>
        <td>Name</td>
        <td>Visited</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${persons}" var="x">
        <tr>
            <td><c:out value="${x.firstName} ${x.lastName}"/></td>
            <td><input type="checkbox" id="${x.personId}" onclick="activateVisited();"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${empty persons}">
    There are currently no persons in the list.
</c:if>

<script type="text/javascript">
    $(document).ready(function () {
                $('#myTable').tablesorter();
            }
    );

    function activateVisited() {
        $("#50").after('<img src="/Test/resources/images/ajax-loader.gif" />')
        $("#50").attr({"disabled": true});
        $("#50").attr('readonly', 'readonly');
        $("#50").after().remove();
    }
</script>