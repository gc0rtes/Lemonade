package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyLemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeJuicySteps(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
) {
    var image = painterResource(R.drawable.lemon_tree)

    var description = stringResource(R.string.tap_the_lemon_tree_to_select_a_lemon)

    var imageDescription = stringResource(R.string.lemon_tree)

    var timesToSqueeze by remember { mutableStateOf((2..4).random()) }

    var count by remember {mutableStateOf(0)}

    if (count > 3 + timesToSqueeze) {
        count = 0
    }

    //show images
    when(count) {
        0 -> image = painterResource(R.drawable.lemon_tree)
        1 -> image = painterResource(R.drawable.lemon_squeeze)
        2 -> image = painterResource(R.drawable.lemon_drink)
        3 -> image = painterResource(R.drawable.lemon_restart)
    }

    //show image description
    when(count){
        0 -> imageDescription = stringResource(R.string.lemon_tree)
        1 -> imageDescription = stringResource(R.string.lemon_squeeze)
        2 -> imageDescription = stringResource(R.string.lemon_drink)
        3 -> imageDescription = stringResource(R.string.lemon_restart)
    }

    //show instructions description
    when(count) {
        0 -> description = stringResource(R.string.tap_the_lemon_tree_to_select_a_lemon)
        1 -> description = stringResource(R.string.keep_tapping_the_lemon_to_squeeze_it)
        2 -> description = stringResource(R.string.tap_the_lemonade_to_drink_it)
        3 -> description = stringResource(R.string.tap_the_empty_glass_to_start_again)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { count++ }) {
            Image(
                painter = image,
                contentDescription = imageDescription,
                )

        }

        Spacer(
            modifier = Modifier
                .height(24.dp)
        )

        Text(
            text = description,
            fontSize = 20.sp
        )
        Text (
            text = "Times to Squeeze: ${timesToSqueeze}"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyLemonadeApp() {
    LemonadeAppTheme {
        LemonadeJuicySteps()
    }
}