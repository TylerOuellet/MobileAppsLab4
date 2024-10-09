package com.example.grid_build

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grid_build.ui.theme.Grid_BuildTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                TopicApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Grid_BuildTheme {
        Greeting("Android")
    }
}
// The Link for the count icon did not work(404)
// https://raw.githubusercontent.com/android/compose-samples/main/Owl/app/src/main/res/drawable/ic_grain.xml
// So I skipped adding it
@Composable
fun SubjectCard(topic : Topic,modifier: Modifier = Modifier){
    Card(modifier = modifier.padding(4.dp).fillMaxWidth().height(150.dp)){
        Row(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center).padding(16.dp)) {
            Image(
                painter = painterResource(id = topic.imageResourceId),
                contentDescription = stringResource(id = topic.stringResourceId),
                modifier = Modifier
                    .height(68.dp)
                    .width(68.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(id = topic.stringResourceId))
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = topic.count.toString())
            }
        }
    }
}
@Preview
@Composable
fun TestCard(){
    SubjectCard(topic = Topic(R.string.architecture, 58, R.drawable.architecture))
}

@Composable
fun Card_Grid(topicList: List<Topic>, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(8.dp)
    ) {
        items(topicList) { topic ->
            SubjectCard(
                topic = topic,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun TopicApp(){
    Card_Grid(
        topicList = DataSource.getData(),
    )
}

