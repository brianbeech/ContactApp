<%@ taglib prefix="sitemesh-page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%--
  Created by IntelliJ IDEA.
  User: bbeech
  Date: 2/27/13
  Time: 11:32 AM
--%>

    <script type="text/javascript">
        function findPerson() {
            var input = $("#searchName").val();
            $.ajax({
                type: "GET",
                contentType: "application/json; charset=utf-8",
                url: "/Test/beech/search/persons/all",
                data: {name: input},
                success: function (msg) {
                    $("#peopleResults").empty();
                    if (msg != "{}") {
                        var json = $.parseJSON(msg);
                        $.each(json, function (index, element) {
                            $("#peopleResults").append(element + " <a href=\"/Test/beech/search/person/?id="+index+"\">Get this</a><br/>");
                        });
                        $("#peopleResults").dialog({
                            title: "Search Results",
                            modal: true
                        });

                    } else {
                        $("#peopleResults").append("No one found matching that name.")
                    }
                }
            });
        }
    </script>


Name: <input type="text" id="searchName" size="25" />
<input type="button" id="searchButton" value="Submit" onclick="findPerson();"/>
<div id="peopleResults" style="display: none"></div>
