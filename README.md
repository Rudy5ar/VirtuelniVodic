# VirtuelniVodic

VirtuelniVodic is a web application designed to help newcomers to Novi Sad navigate the city and discover its attractions. The app provides a user-friendly way for visitors to plan and create personalized routes, making it easier for them to explore the city.

## Features
- **Route Planning**: Create custom routes based on your interests and preferences.
- **Map Integration**: Get turn-by-turn directions and a visual representation of your route using the OpenRouteService API.
- **Attraction Database**: Browse and select from a database of attractions, landmarks, and points of interest in Novi Sad.
- **User-Friendly Interface**: Easy to use and navigate, perfect for visitors new to the city.

## Technical Overview
The VirtuelniVodic project consists of two main components:

1. **Web Application**: Built using Spring Boot, HTML/CSS/JavaScript, JSP pages (located in the `webapp` folder), and the OpenRouteService API. This component handles the frontend and backend logic for interacting with the user and providing route planning functionalities.
2. **Data Access Layer**: Built using Java Persistence API (JPA), Spring Data JPA, and Hibernate, this component is located in the `VirtuelniVodicJPA` folder.

The two components are tightly integrated, with the web application using the data access layer to interact with the database and perform CRUD (Create, Read, Update, Delete) operations.

## Data Model
The data access layer defines several entities, including:
- `Clanak` (Article)
- `Epoha` (Epoch)
- `Korisnik` (User)
- `Lokacija` (Location)
- `Ruta` (Route)
- `Tema` (Theme)

These entities are used to store and retrieve data from the database and are accessed through repositories and services.

## Getting Started
To get started with VirtuelniVodic:

1. Clone the repository.
2. Run the application using Spring Boot.
3. Access the app through your web browser at http://localhost:8080/Muzej/ and start creating your own routes.

**Note**: Make sure to configure the database connection properties in the `application.properties` file before running the application.

## Technologies Used
- Spring Boot
- OpenRouteService API
- HTML/CSS/JavaScript
- JSP (located in the `webapp` folder)
- Java Persistence API (JPA)
- Spring Data JPA
- Hibernate
