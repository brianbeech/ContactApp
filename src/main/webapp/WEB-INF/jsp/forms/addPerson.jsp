<%@ include file="../includes/init.jsp" %>

<body>
<form:form modelAttribute="person" method="post">
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName" /></td>
            <td>Last Name:</td>
            <td><form:input path="lastName" /></td>
        </tr>
        <tr>
            <td>Street:</td>
            <td><form:input path="street1"/></td>
            <td>Street 2:</td>
            <td><form:input path="street2"/></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><form:input path="city"/></td>
            <td>State:</td>
            <td><form:input path="state"/></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><form:input path="zipCode"/></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Sex:</td>
            <td><form:input path="sex"/></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td><form:input path="dob"/></td>
            <td>Member:</td>
            <%--<td><form:input path="member"/></td>--%>
        </tr>
    </table>
    <input type="button" value="Add Person" onclick="brianbeech(); return false;"/>
</form:form>


<script type="text/javascript">
    function brianbeech() {
        $(function() {
            $.post("/Test/beech/person/add",
                    {  firstName:  $("#firstName").val(),
                        lastName:  $("#lastName").val(),
                        sex:  $("#sex").val() },
                    function(data){
                        alert(data);
                    });
        });
    }
</script>
</body>