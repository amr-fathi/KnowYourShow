## Kotlin Reactive Micro Services Example

This is an example of doing reactive MicroServices using spring 5 functional web framework and spring boot 2 using Kotlin.

This service will call `OMDB` api and fetch the details of given TV show name

It's a work in progress and following tasks are pending
```Gherkin
Test cases
Fix wrong output produced by the service
```

This example cover several topics:

- Functional programing.
- Reactive types.
- Router Functions.
- Error handling in routes and services.
- Reactive Web Client to consume external REST Services.
- Gradle
## Request

```html
http://localhost:8080/api/show/friends
```

Sample output of the api
```json
{
	"Title": "Friends",
	"Year": "1994–2004",
	"Rated": "TV-14",
	"Released": "22 Sep 1994",
	"Runtime": "22 min",
	"Genre": "Comedy, Romance",
	"Director": "N/A",
	"Writer": "David Crane, Marta Kauffman",
	"Actors": "Jennifer Aniston, Courteney Cox, Lisa Kudrow, Matt LeBlanc",
	"Plot": "Follows the personal and professional lives of six 20 to 30-something-year-old friends living in Manhattan.",
	"Language": "English, Dutch, Italian, French",
	"Country": "USA",
	"Awards": "Won 1 Golden Globe. Another 68 wins & 211 nominations.",
	"Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BMTg4NzEyNzQ5OF5BMl5BanBnXkFtZTYwNTY3NDg4._V1._CR24,0,293,443_SX89_AL_.jpg_V1_SX300.jpg",
	"Ratings": [
		{
			"Source": "Internet Movie Database",
			"Value": "8.9/10"
		}
	],
	"Metascore": "N/A",
	"imdbRating": "8.9",
	"imdbVotes": "581,484",
	"imdbID": "tt0108778",
	"Type": "series",
	"totalSeasons": "10",
	"Response": "True"
}
```

## Steps to run the application
```Gherkin
gradle build
gradle bootrun
```