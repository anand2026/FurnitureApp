package com.example.furniture_app.furniture.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.furniture_app.R
import com.example.furniture_app.furniture.data.Category
import com.example.furniture_app.furniture.data.PopularProducts
import com.example.furniture_app.furniture.data.Rooms
import com.example.furniture_app.furniture.data.categoryList
import com.example.furniture_app.furniture.data.popularProductList
import com.example.furniture_app.furniture.data.roomList
import com.example.furniture_app.furniture.navigation.productDetail
import com.example.furniture_app.ui.theme.DarkOrange
import com.example.furniture_app.ui.theme.LightGray_1
import com.example.furniture_app.ui.theme.TextColor_1

@Composable
fun homeScreen(
    navHostController: NavHostController
) {
   var searches = remember {
       mutableStateOf("")
   }
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.White)
    ){
        item {
            Header()
            customTextField(Text = searches.value, onValueChange = {
                searches.value = it
            })
            CategoryRow()
            PopularRow{
                navHostController.navigate(productDetail)
            }
            bannerRow()
            Rooms()
        }

    }
}

@Composable
fun Rooms() {
    Column {
        Text(text = "Rooms", style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color.Black
        ))
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Furniture for every corners of your rooms", style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            color = LightGray_1
        ),modifier = Modifier
            .padding(12.dp)
            .width(100.dp)
            )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(modifier = Modifier.padding(top = 12.dp)){
            items(roomList.size){
                RoomSection(room = roomList[it])
            }
        }
    }
}

@Composable
fun RoomSection(
    room: Rooms,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.padding(end = 15.dp)){
        Image(painter = painterResource(id = room.image), contentDescription = "",
            modifier = Modifier
                .height(150.dp)
                .width(200.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(8.dp)),
        )
        Text(text = room.title, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            color = TextColor_1
        ))
    }
}
@Composable
fun bannerRow() {
    Image(painter = painterResource(id = R.drawable.banner), contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(113.dp)
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.FillWidth
        )
}
@Composable
fun Header(
    onClick: () -> Unit={}
) {
    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        Column(modifier = Modifier.padding(top = 12.dp)) {
            Text(text = "Explore What", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            ))
            Text(text = "Your Home Needs", style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            ))
        }
        IconButton(onClick = { onClick() }, modifier = Modifier
            .size(32.dp)
            .align(Alignment.CenterVertically)) {
            Icon(imageVector = Icons.Default.Notifications, contentDescription ="", tint = DarkOrange, )
        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularRow(
    onClick: () -> Unit
) {
    CommonTitle(title = "Popular")
    Spacer(modifier = Modifier.height(12.dp))

    FlowColumn(
        maxItemsInEachColumn = 2
    ) {
        popularProductList.forEach {
            PopularEachRow(popular = it){
                onClick()
            }
        }
    }

    /*LazyVerticalGrid(columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2)){
        items(popularProductList.size) {
            PopularEachRow(popular = popularProductList[it]){
                onClick()
            }
        }
    }*/
}

@Composable
fun PopularEachRow(
    popular: PopularProducts,
    modifier: Modifier = Modifier,
    onClick: () -> Unit={}
) {
    Column(modifier = modifier.clickable { onClick}){
        Box{
            Image(painter = painterResource(id = popular.image), contentDescription = "",
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .align(Alignment.Center)
            )
            Icon(painter = painterResource(id = R.drawable.heartss), contentDescription ="" ,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp, end = 12.dp)
                    .size(32.dp),tint=Color.Unspecified
                )
        }
        Spacer(modifier = Modifier.height(12.dp))
        ElevatedCard(
            modifier=modifier.align(Alignment.CenterHorizontally),
        ) {
            Column (modifier=modifier.padding(horizontal = 10.dp,vertical = 15.dp)){
                Text(text = popular.title, style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = LightGray_1
                ))
                Text(text = popular.price, style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = DarkOrange
                ))
            }
        }
    }
}
@Composable
fun CategoryRow() {
    Column {
        CommonTitle(title = "Category")
        LazyRow(modifier = Modifier.padding(top = 12.dp)){
            items(categoryList.size){
                CategoryEachRow(category = categoryList[it])
            }
        }
    }
}

@Composable
fun CategoryEachRow(
    category: Category
) {
    Box(modifier = Modifier
        .padding(end = 12.dp)
        .background(category.Color, shape = RoundedCornerShape(8.dp))
        .width(140.dp)
        .height(100.dp)){
        Text(text = category.title, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            color = Color.Black
        ),
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp)
                .align(Alignment.CenterStart)
        )
        Image(painter = painterResource(id = category.image), contentDescription = "",
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp)
                .size(40.dp)
                .align(Alignment.BottomEnd)
            )
    }
}
@Composable
fun CommonTitle(
    title: String,
    onClick: () -> Unit={}
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = title, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            ),
            modifier = Modifier.padding(top = 12.dp)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = { onClick() }) {
                Text(
                    text = "See All", style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                        color = DarkOrange
                    )
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "",
                    tint = DarkOrange,
                    modifier = Modifier.size(27.dp)
                )
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun customTextField(
    Text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit={}
) {
    TextField(value = Text, onValueChange =onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        placeholder = { Text(text = "Chair,desk,lamp,etc", fontSize = 15.sp,
            color = LightGray_1,
            fontWeight = FontWeight.W500) },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription ="", tint = LightGray_1,)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .border(2.dp, LightGray_1)
        )
}