<head>
    <script type="text/javascript">
        $(document).ready(function () {
            var geocoder = new google.maps.Geocoder();
            var address = $("#address").text() + " " + $("#city").text() + " " + $("#state").text() + " " + $("#zip").text();
            if($("#address").text()){
                geocoder.geocode({ 'address': address}, function (results, status) {
                    var latitude;
                    var longitude;
                    if (status == google.maps.GeocoderStatus.OK) {
                        latitude = results[0].geometry.location.lat();
                        longitude = results[0].geometry.location.lng();
                    }
                    var myLatLng = new google.maps.LatLng(latitude, longitude);
                    var mapOptions = {
                        zoom: 15,
                        center: myLatLng,
                        mapTypeId: google.maps.MapTypeId.ROADMAP,
                        zoomControl: true
                    }
                    var map = new google.maps.Map($("#map_canvas")[0], mapOptions);
                    var beachMarker = new google.maps.Marker({
                        position: myLatLng,
                        map: map
                    });
                    map.zoomControl = true;
                });
            }

        });
    </script>
    <style type="text/css">
        #map_canvas img{
            max-width: 100%;
        }

        span{
            font-size: small;
            font-weight: normal;
        }
        .personOutput{
            font-size: small;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="personOutput" style="float: left">
   Name: <span id="name"> ${viewPerson.firstName} ${viewPerson.lastName} </span><br/>
    Address: <span id="address">${viewPerson.street1}</span><br/>
   City: <span id="city">${viewPerson.city}</span> State: <span id="state">${viewPerson.state}</span>     <br/>
   Zip: <span id="zip">${viewPerson.zipCode}</span><br/>
   Phone: <span id="phone"> ${viewPerson.phoneNumber}</span> <br/>
   Sex: <span id="sex"> ${viewPerson.sex}</span>
</div>
<div id="map_canvas" style="float: left; width: 250px; height: 250px;">
</div>
</body>
