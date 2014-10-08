document.addEventListener("deviceready", function() {
  setTimeout(function() {
    var map = L.map("maprender", {}).setView([51.3399417, -0.483258], 19);
    var ostile = new L.TileLayer.OSmapTile({
      minZoom: 19,
      maxZoom: 21,
      detectRetina: true
    });
    ostile.addTo(map);
    map.on("click", function(e) {
      var popStr = prompt("Enter text for marker");
      if (popStr.length > 0) {
        var l = e.latlng;
        var marker = L.marker(e.latlng, {
          draggable: true
        }).bindPopup(popStr + "<br/> Lat:" + l.lat + " Lng:" + l.lng).addTo(map);
        marker.on("move", function(e) {
          var l = e.latlng;
          marker.getPopup().setContent(popStr + "<br/> Lat:" + l.lat + " Lng:" + l.lng).update();
        });
      }
    });

    document.getElementById("curLocationBtn").onclick= function() {
      navigator.geolocation.getCurrentPosition(function(geo) {
        var coords = geo.coords;
        document.getElementById("goto_lat").value = coords.latitude;
        document.getElementById("goto_lng").value = coords.longitude;
      },function(err){
        alert("error");
        alert(err.message);
      });
    };
    document.getElementById("goto_btn").onclick= function() {
      var coords={};
      coords.lat=document.getElementById("goto_lat").value ;
      coords.lng=document.getElementById("goto_lng").value ;
      map.setView(coords);
    };
  }, 500);
});
