<script type="text/javascript">
    $(document).ready(function () {
         grabData();
    });
    function grabData(){
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/Test/beech/dashboard/numVisitsThisMonth",
            data: {},
            success: function (msg) {
                var json = $.parseJSON(msg);
                $.each(json, function (index, element) {
                    $("#"+index).append(element);
                });
            }
        });
    }
</script>
<div id="my_stats">
    <div>Number of visits this month: <span id="visitsThisMonth"></span> </div>
    <div>Number of member visits this month: <span id="activeMembersVisitedThisMonth"></span> </div>
    <div>Number of inactive visitors visited this month: <span id="inactiveMembersVisitedThisMonth"></span> </div>
    <div>Number of visitors visited this month: <span id="visitorsVisitedThisMonth"></span> </div>
</div>