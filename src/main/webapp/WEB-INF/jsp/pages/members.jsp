<%@ taglib prefix="sitemesh-page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    #member_list_div{
        height: 460px;
        float: left;
        position: relative;
        overflow-y: scroll;
        overflow-style: marquee;
        padding: 5px;
    }
    #member_content_div{
        float: left;
        position: relative;
        margin-left: 10px;
    }
    ul{
        padding: 5px;
        margin: 0;
        list-style-type: none;

    }
    ul li{
        font-size: small;
        padding: 2px;
    }
    li {
        text-decoration: none;
        cursor: pointer;
    }
    #member_content_div{
        padding: 5px;
        height: 90%;

    }
    #selectable .ui-selected {
        background: #acdd4a ;
        color: white;
    }
    input[type="text"]{
        font-size: small;
        margin-left: 0;
        padding: 3px;
        width: 125px;
        height: 12px;
    }

    span.deleteicon {
        position: relative;
    }
    span.deleteicon span {
        position: absolute;
        display: block;
        top: 5px;
        right: 0px;
        width: 16px;
        height: 16px;
            background: url('http://cdn.sstatic.net/stackoverflow/img/sprites.png?v=4') 0 -690px;
        cursor: pointer;
    }
    span.deleteicon input {
        padding-right: 16px;
    }
</style>
<script type="text/javascript">

    $(document).ready(function() {

        $('input.deletable').wrap('<span class="deleteicon" />').after($('<span/>').click(function() {
            $(this).prev('input').val('').focus();
            $("#selectable li").each(function(){
                $(this).show();
            })
        }));
    });

    $(function(){
        $("#selectable li").click(function () {
            $(this).addClass("#selectable ui-selected").siblings().removeClass("#selectable ui-selected");
            //$("#member_content_div").text($(this).text());
            getPersonDetails($(this).attr("id"),"member_content_div");
        });
    });

    function filter(element) {
        var value = $(element).val();

        $("#selectable > li").each(function() {
            if ($(this).text().toLowerCase().search(value.toLowerCase()) > -1) {
                $(this).show();
            }
            else {
                $(this).hide();
            }
        });
    }

    function getPersonDetails(id,target){
        var targetDiv = "#" + target;
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/Test/beech/ajax/person",
            data: {id: id},
            success: function (msg) {
                $(targetDiv).empty();
                if (msg != "{}") {
                    $(targetDiv).append(msg);
                } else {
                    $("#peopleResults").append("Error getting ")
                }
            }
        });
    }
</script>
        <div id="member_container">
            <p>Member Dashboard</p>

            <div id="member_list_div">
                <input type="text" class="deletable" onkeyup="filter(this)"/>
                <ul id="selectable" style="list-style: none;">
                    <c:forEach items="${persons}" var="x">
                    <li id="<c:out value="${x.personId}"/>"> <c:out value="${x.firstName} ${x.lastName}"/></li>
                    </c:forEach>
                </ul>
            </div>
            <div id="member_content_div">
                Bunch of content here
            </div>
        </div>