<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Route Map</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
    <style>
        #map { height: 600px; }
    </style>
</head>
<body>
<div id="map"></div>
<script>
    // Initialize the map and set its view to a relevant region
    var map = L.map('map').setView([45.715, 19.63], 10); // Adjust the coordinates and zoom level to fit your data

    // Set up the OpenStreetMap layer
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
    }).addTo(map);

    // Route data from the server, ensure it's serialized correctly to JSON
    var routeData = ${routeData};
    console.log("Route Data: ", routeData);

    // Check if routeData is properly passed and is an object
    if (typeof routeData === "string") {
        try {
            routeData = JSON.parse(routeData);
        } catch (e) {
            console.error("Invalid routeData format:", e);
        }
    }

    // Check if routeData contains the expected properties
    if (routeData && routeData.routes && routeData.routes[0] && routeData.routes[0].geometry) {
        // Decode the polyline geometry
        var routeCoordinates = decodePolyline(routeData.routes[0].geometry);

        // Create a Leaflet polyline using the decoded coordinates
        var polyline = L.polyline(routeCoordinates, { color: 'blue' }).addTo(map);

        // Fit the map view to the polyline
        map.fitBounds(polyline.getBounds());
    } else {
        console.error("Route data is not in the expected format.");
    }

    // Function to decode the polyline geometry
    function decodePolyline(encoded) {
        var points = [];
        for (var i = 0, len = encoded.length, lat = 0, lng = 0; i < len;) {
            var b, shift = 0, result = 0;
            do {
                b = encoded.charCodeAt(i++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            var deltaLat = ((result & 1) ? ~(result >> 1) : (result >> 1));
            lat += deltaLat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charCodeAt(i++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            var deltaLng = ((result & 1) ? ~(result >> 1) : (result >> 1));
            lng += deltaLng;

            points.push([lat * 1e-5, lng * 1e-5]);
        }
        return points;
    }
</script>
</body>
</html>
