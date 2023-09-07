package com.example.composetutorial

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity2 : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message = intent.getStringExtra("value1") ?: ""
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppPortrait()

                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppPortrait() {
    ComposeTutorialTheme {
        Scaffold(
            bottomBar = { BottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}
@Composable
private fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text("Home")

            },
            selected = true,
            onClick = {

            }

        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle, contentDescription = null
                )
            },
            label = {
                Text("Profile")
            },
            selected = false,
            onClick = {
            }
        )
    }
}
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(  modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(16.dp))
        searchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            BodyElementsRow(BodyElementList = BodyElementList.elementSample)
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionCardsRow(favoriteList = FavoriteCollectionList.elementSample)
        }
        Spacer(Modifier.height(16.dp))
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    ComposeTutorialTheme { HomeScreen() }
}
@Composable
fun HomeSection( // set the title and exact composable
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Log.v("check_in", title.toString())
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name!",
        modifier = modifier
    )
}
@Preview
@Composable
fun SearchBarPreview() {
    ComposeTutorialTheme {
        searchBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar(modifier: Modifier = Modifier){
    val text = remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ){
        TextField(
            value = text.value.text,
            onValueChange = {text.value = TextFieldValue(text = it)
                            Log.v("check",text.value.text)},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null )
            },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary
            ),
            placeholder = {
                Text("Search")
            },
            textStyle = TextStyle(
                color = Color.Red, // Set your custom text color here
                fontSize = 16.sp // Optional: Set the font size
            ),
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp, max = 100.dp)
                .alpha(0.5f)
                .align(Alignment.Top)
            ,

            )
    }

}
@Composable
fun BodyElement(
    @DrawableRes drawable: Int,// image resource
    @StringRes text:Int, // String resource
    modifier: Modifier = Modifier
){
    Column (modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Composable
fun BodyElementsRow(BodyElementList: List<BodyElementData>,
    modifier: Modifier = Modifier
){
    LazyRow( horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp,vertical = 8.dp),
        modifier = modifier){
        items(BodyElementList){
            item ->
            BodyElement(drawable = item.drawable, text = item.text )
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun previewBodyElement(){
    ComposeTutorialTheme {
    BodyElementsRow(BodyElementList = BodyElementList.elementSample)
    }
}
@Composable
fun FavoriteCollectionCards(
    @DrawableRes drawable :Int,
    @StringRes text : Int,
    modifier: Modifier = Modifier
){
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ){
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)

            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
@Composable
fun FavoriteCollectionCardsRow(
    favoriteList : List<FavoriteCollectionData>,
    modifier: Modifier = Modifier){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp) ){
        items(favoriteList){item->
                FavoriteCollectionCards(drawable = item.drawable, text = item.text,Modifier.height(70.dp))
        }
    }
}
@Preview
@Composable
fun previewCardsRow(){
    ComposeTutorialTheme {
        FavoriteCollectionCardsRow(favoriteList = FavoriteCollectionList.elementSample)
    }

}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun previewFavoriteCard(){
    ComposeTutorialTheme {
        FavoriteCollectionCards(drawable = R.drawable.flower, text = R.string.favoritecard_title1 , modifier = Modifier.padding(8.dp))

    }
}
data class BodyElementData(val drawable: Int,val text: Int)
data class FavoriteCollectionData(val drawable: Int,val text: Int)

object BodyElementList{
    val elementSample = listOf(
        BodyElementData(R.drawable.vegetable,R.string.body_title1) ,
        BodyElementData(R.drawable.gym,R.string.body_title2) ,
        BodyElementData(R.drawable.yoga,R.string.body_title3) ,
        BodyElementData(R.drawable.vegetable,R.string.body_title1) ,
        BodyElementData(R.drawable.gym,R.string.body_title2) ,
        BodyElementData(R.drawable.yoga,R.string.body_title3) ,
        )
}
object FavoriteCollectionList{
    val elementSample = listOf(
        FavoriteCollectionData(R.drawable.flower,R.string.favoritecard_title1) ,
        FavoriteCollectionData(R.drawable.cookies,R.string.favoritecard_title2),
        FavoriteCollectionData(R.drawable.guitar,R.string.favoritecard_title3),
        FavoriteCollectionData(R.drawable.flower,R.string.favoritecard_title1) ,
        FavoriteCollectionData(R.drawable.cookies,R.string.favoritecard_title2),
        FavoriteCollectionData(R.drawable.guitar,R.string.favoritecard_title3),
    )
}