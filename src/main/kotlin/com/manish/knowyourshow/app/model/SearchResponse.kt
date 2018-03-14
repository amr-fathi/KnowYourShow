package com.manish.knowyourshow.app.model

data class SearchResponse(private val Title: String,
                          private val Year: String,
                          private val Rated: String,
                          private val Released: String,
                          private val Runtime: String,
                          private val Genre: String,
                          private val Director: String,
                          private val Writer: String,
                          private val Actors: String,
                          private val Plot: String,
                          private val Language: String,
                          private val Country: String,
                          private val Awards: String,
                          private val Poster: String,
                          private val Ratings: ArrayList<RatingSource>,
                          private val Metascore: String,
                          private val imdbRating: String,
                          private val imdbVotes: String,
                          private val imdbID: String,
                          private val Type: String,
                          private val totalSeasons: String,
                          private val Response: String)

data class RatingSource(private val Source: String,
                        private val Value: String)