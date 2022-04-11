package com.faridhal.movieapp.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faridhal.movieapp.models.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val tempMovies = ArrayList<Movie>().apply {
        add(Movie(1, "Morbius", imageURL = "https://image.tmdb.org/t/p/w342/6nhwr1LCozBiIN47b8oBEomOADm.jpg", rating = 6.4))
        add(Movie(2, "The Exorcism of God", imageURL = "https://image.tmdb.org/t/p/w342/pcqo9D8Bdppt6t9fBliJYPROZkv.jpg", rating = 5.6))
        add(Movie(3, "Sonic the Hedgehog 2", imageURL = "https://image.tmdb.org/t/p/w342/1j6JtMRAhdO3RaXRtiWdPL5D3SW.jpg", rating = 7.7))
        add(Movie(4, "The Bad Guys", imageURL = "https://image.tmdb.org/t/p/w342/1s0em1CVrM1e6fsafiNePXqh6Hv.jpg", rating = 7.0))
        add(Movie(5, "American Siege", imageURL = "https://image.tmdb.org/t/p/w342/daeVrgyj0ue8qb3AHyU3UeCwoZz.jpg", rating = 3.4))
        add(Movie(6, "The Novice", imageURL = "https://image.tmdb.org/t/p/w342/zIkGGiQBNITG9vVxgmf6MXQ1gT1.jpg", rating = 6.5))
        add(Movie(7, "The Lost City", imageURL = "https://image.tmdb.org/t/p/w342/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", rating = 6.8))
        add(Movie(8, "Spider-Man: No Way Home", imageURL = "https://image.tmdb.org/t/p/w342/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg", rating = 8.6))
        add(Movie(9, "The Adam Project", imageURL = "https://image.tmdb.org/t/p/w342/wFjboE0aFZNbVOF05fzrka9Fqyx.jpg", rating = 7.0))
        add(Movie(10, "Blacklight", imageURL = "https://image.tmdb.org/t/p/w342/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg", rating = 4.2))
        add(Movie(11, "Pil's Adventures", imageURL = "https://image.tmdb.org/t/p/w342/xy6wQ09rMIN2FfWPHJXCWyRZ3P9.jpg", rating = 6.8))
        add(Movie(12, "My Hero Academia: World Heroes' Mission", imageURL = "https://image.tmdb.org/t/p/w342/4NUzcKtYPKkfTwKsLjwNt8nRIXV.jpg", rating = 7.3))
        add(Movie(13, "The King's Man", imageURL = "https://image.tmdb.org/t/p/w342/aq4Pwv5Xeuvj6HZKtxyd23e6bE9.jpg", rating = 5.1))
        add(Movie(14, "Hotel Transylvania: Transformania", imageURL = "https://image.tmdb.org/t/p/w342/teCy1egGQa0y8ULJvlrDHQKnxBL.jpg", rating = 7.1))
        add(Movie(15, "Nightmare Alley", imageURL = "https://image.tmdb.org/t/p/w342/vfn1feL0V9HNSXuLLpaxAW8O6LO.jpg", rating = 7.1))
        add(Movie(16, "Jujutsu Kaisen 0", imageURL = "https://image.tmdb.org/t/p/w342//3pTwMUEavTzVOh6yLN0aEwR7uSy.jpg", rating = 7.8))
    }

    private val _movieList: MutableState<List<Movie>> = mutableStateOf(emptyList())
    val movieList: State<List<Movie>> get() = _movieList

    fun getMovieList() = viewModelScope.launch {
        delay(1000)
        _movieList.value = tempMovies
    }

    fun searchMovie(text: String) = viewModelScope.launch {
        if (text.isBlank()) {
            _movieList.value = tempMovies
            return@launch
        }

        val filtered = tempMovies.filter { it.title.lowercase().contains(text.lowercase()) }
        _movieList.value = filtered
    }
}