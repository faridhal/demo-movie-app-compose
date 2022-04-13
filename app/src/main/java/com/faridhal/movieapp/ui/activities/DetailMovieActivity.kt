package com.faridhal.movieapp.ui.activities

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.faridhal.movieapp.models.Movie
import com.faridhal.movieapp.ui.theme.MovieAppDemoTheme
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.skydoves.landscapist.glide.GlideImage

class DetailMovieActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppDemoTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    MovieDetail()
                }
            }
        }
    }
}

@Composable
fun MovieDetail() {
    val activity = LocalContext.current as Activity
    val descDummy =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."

    activity.intent.extras?.let {
        it.getParcelable<Movie>("movie")?.let { movie ->
            GlideImage(
                imageModel = movie.imageURL,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colors.background,
                            MaterialTheme.colors.background
                        )
                    ),
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = descDummy,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    RatingBar(
                        value = movie.rating.toFloat() / 2,
                        onValueChange = {},
                        onRatingChanged = {},
                        config = RatingBarConfig().numStars(5).size(24.dp),
                        modifier = Modifier.padding(bottom = 32.dp)
                    )
                    Button(
                        contentPadding = PaddingValues(vertical = 8.dp),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = "Buy Ticket",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            }
        }
    }
}