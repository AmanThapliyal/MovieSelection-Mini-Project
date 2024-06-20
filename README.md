# Movie Database Search Project

This project demonstrates a Java application that connects to a MySQL database to retrieve movie information based on user input. The application allows users to search for movies by title, IMDb rating range, runtime range, year, or a combination of these criteria.

## Features

- Search for movies by title.
- Search for movies within a specified IMDb rating range.
- Search for movies within a specified runtime range.
- Search for movies released in a specific year.
- Perform a combined search using IMDb rating range, runtime range, and year.
- Display detailed information about the selected movie.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed.
- MySQL database with a `Movies` table.
- MySQL Connector/J (JDBC Driver) for Java.

### Database Setup

1. Create a MySQL database named `movie`.
2. Create a `Movies` table with the following schema:

```sql
CREATE TABLE Movies (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(255),
    Year INT,
    Summary TEXT,
    IMDB_ID VARCHAR(50),
    Runtime INT,
    YouTubeTrailer VARCHAR(255),
    Rating FLOAT,
    MoviePoster VARCHAR(255),
    Director VARCHAR(255),
    Writers TEXT,
    Cast TEXT
);
# MovieSelection-Mini-Project
