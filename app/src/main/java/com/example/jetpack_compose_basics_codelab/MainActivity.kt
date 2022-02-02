package com.example.jetpack_compose_basics_codelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_basics_codelab.ui.theme.JetpackcomposebasicscodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposebasicscodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
                Greeting(name)
            }
        }
    }
}

@Composable
private fun Greeting(name: String) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val surfaceColor by animateColorAsState(
        targetValue = if (isExpanded) MaterialTheme.colors.surface else MaterialTheme.colors.primary
    )
    Card(
        backgroundColor = surfaceColor,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .animateContentSize()
    ) {
        val changeIsExpanded = {
            isExpanded = !isExpanded
        }
        if (isExpanded) {
            ExpandedItem(
                name = name,
                onClick = changeIsExpanded
            )
        } else {
            ContractedItem(
                name = name,
                onClick = changeIsExpanded
            )
        }
    }
}

@Composable
private fun ContractedItem(
    name: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Hello,")
            Text(
                text = "$name!",
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Filled.ExpandMore,
                contentDescription = stringResource(R.string.show_more)
            )
        }
    }
}

@Composable
private fun ExpandedItem(
    name: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .animateContentSize()
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Hello,")
            Text(
                text = "$name!",
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(text = "Lorem ipsum dolor sit amet. Et architecto possimus et vitae debitis At saepe odit et nesciunt officiis ea asperiores iure qui odio officiis eum eaque dolorum. Id unde voluptas in voluptas autem eos voluptas quae qui tempore eveniet et consequatur sunt. Cum quia magnam ut quos provident At fugiat dolor est excepturi rerum et voluptatem consequatur.")
        }
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Filled.ExpandLess,
                contentDescription = stringResource(R.string.show_less)
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetpackcomposebasicscodelabTheme {
        Greetings(
            listOf("Test", "Luciano", "Virgínia", "Porto Alegre")
        )
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetpackcomposebasicscodelabTheme {
        OnboardingScreen { }
    }
}