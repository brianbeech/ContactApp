<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="sitemesh-page" uri="http://www.opensymphony.com/sitemesh/page" %>
<script type="text/javascript">
        $(function() {
            $( "#tabs" ).tabs();
        });
    </script>
<%--<sitemesh-page:applyDecorator name="findPerson"/>--%>

<div id="tabs" style="width: 90%; position:absolute; ">
    <ul>
        <li><a href="#dashboard">Dashboard</a></li>
        <li><a href="#members">Members</a></li>
        <li><a href="#contacts">Contacts</a></li>
        <li><a href="#admin">Administration</a></li>
    </ul>
    <div id="dashboard">
        <p>Dashboard</p>
        <jsp:include page="dashboard.jsp"/>
    </div>
    <div id="members">
        <jsp:include page="members.jsp"/>
    </div>
    <div id="contacts">
        <p>Log Contact Entry Here</p>
        <jsp:include page="contact.jsp"/>
    </div>
    <div id="admin">
        <p>Administrative functions here</p>
        <jsp:include page="admin.jsp"/>
    </div>
</div>