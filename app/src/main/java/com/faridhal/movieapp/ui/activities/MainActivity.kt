package com.faridhal.movieapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.faridhal.movieapp.models.Movie
import com.faridhal.movieapp.ui.theme.MovieAppDemoTheme
import com.faridhal.movieapp.ui.viewModels.MainViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.skydoves.landscapist.glide.GlideImage

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieList()
        setContent {
            MovieAppDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Column {
                        Text(
                            text = "Movie App",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.subtitle1
                        )
                        SearchMovie(viewModel)
                        MovieList(movieList = viewModel.movieList.value)
                    }

                }
            }
        }
    }
}

@Composable
fun SearchMovie(viewModel: MainViewModel) {
    var filter by remember { mutableStateOf("") }
    TextField(
        value = filter,
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .fillMaxWidth(),
        placeholder = { Text(text = "Search Movie..")},
        onValueChange = {
            filter = it
            viewModel.searchMovie(it)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(movieList: List<Movie>) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        content = {
            items(movieList) { movie ->
                MovieItem(movie = movie)
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieItem(movie: Movie) {
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(8),
        onClick = {
            Intent(context, DetailMovieActivity::class.java).apply {
                putExtra("movie", movie)
            }.let {
                context.startActivity(it)
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 4.dp))
        {
            GlideImage(
                imageModel = movie.imageURL,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(200.dp)
            )
            Text(
                text = movie.title,
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            RatingBar(
                value = movie.rating.toFloat() / 2,
                onValueChange = {},
                onRatingChanged = {},
                config = RatingBarConfig().numStars(5).size(16.dp)
            )
        }
    }
}