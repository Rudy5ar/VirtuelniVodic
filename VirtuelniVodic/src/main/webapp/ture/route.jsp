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
    // Initialize the map and set its view
    var map = L.map('map').setView([49.41461, 8.681495], 13);

    // Set up the OpenStreetMap layer
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
    }).addTo(map);

    // Route data should be parsed to JSON if it's rendered as a string
    var routeData = ${routeData};
    if (typeof routeData === "string") {
        routeData = JSON.parse(routeData);
    }
    console.log("Parsed Route Data: ", routeData);

    // Extract coordinates from the routeData
    var routeCoordinates = routeData.features[0].geometry.coordinates.map(coord => [coord[1], coord[0]]);

    // Create a Leaflet polyline using the coordinates
    var polyline = L.polyline(routeCoordinates, { color: 'blue' }).addTo(map);

    // Fit the map view to the polyline
    map.fitBounds(polyline.getBounds());
</script>
<a class="back-link" href="http://localhost:8080/Muzej/home.jsp">Back to Home</a>
</body>
</html>
