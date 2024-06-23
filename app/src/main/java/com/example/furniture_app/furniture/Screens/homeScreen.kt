package com.example.furniture_app.furniture.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.furniture_app.ui.theme.DarkOrange
import com.example.furniture_app.ui.theme.LightGray_1

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

        }
    }
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
        modifier = modifier.fillMaxWidth().padding(top = 12.dp).border(2.dp, LightGray_1)
        )
}