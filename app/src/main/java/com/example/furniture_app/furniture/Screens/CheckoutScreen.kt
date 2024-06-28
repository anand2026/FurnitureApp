package com.example.furniture_app.furniture.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.furniture_app.R
import com.example.furniture_app.furniture.data.ShoppingBag
import com.example.furniture_app.furniture.data.shoppingList
import com.example.furniture_app.ui.theme.DarkOrange
import com.example.furniture_app.ui.theme.LightGray_1
import com.example.furniture_app.ui.theme.LineColor
import com.example.furniture_app.ui.theme.TextColor_1

@Composable
fun CheckOutScreen(
    navHostController: NavHostController
) {
    Box (modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            IconButton(onClick = {
                navHostController.navigateUp()
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "",)
            }
            Text(text = "my Shopping bag", style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            ),
                modifier = Modifier.padding(vertical = 15.dp)
                )
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 80.dp,top = 10.dp)
            ) {
                items(shoppingList.size) {
                    ShoppingEachRow(data = shoppingList[it])
                }
                item {
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp), color = LineColor)
                    Spacer(modifier = Modifier.height(10.dp))
                    RecommendedProduct()
                }
            }
        }
        CheckoutRow(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun CheckoutRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit={}
) {
    Column(modifier=modifier.fillMaxWidth()) {
        Divider(Modifier.fillMaxWidth(), color = LineColor, thickness = 1.dp)
        Row (modifier= modifier
            .padding(20.dp)
            .fillMaxWidth()){
            Column {
                Text(text = "Total", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = LightGray_1
                ))
                Text(text = "$600", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = DarkOrange
                ))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TextColor_1,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Proceed to checkout", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.White
                ))
            }
        }
    }
    
}
@Composable
fun ShoppingEachRow(
    data:ShoppingBag
) {
    val count= remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Row (modifier = Modifier.fillMaxWidth()){
            Image(painter = painterResource(id = data.icon), contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(80.dp)
                    .align(Alignment.CenterVertically)
                )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = data.title, style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = TextColor_1
                    ),modifier = Modifier.weight(1f))
                    Box(modifier = Modifier
                        .background(Color.White, shape = CircleShape)
                        .border(1.dp, DarkOrange, shape = CircleShape)
                        .size(30.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(imageVector = Icons.Default.Close, contentDescription = "",
                            modifier = Modifier.size(10.dp)
                            )
                    }
                }
                Text(text = "${data.qty} Qty", style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600,
                    color = LightGray_1
                ))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),) {
                Row(modifier =Modifier.weight(1f)){
                    Button(
                        onClick = { count.value++}) {
                        Icon(painter = painterResource(id = R.drawable.plus1), contentDescription ="",
                            modifier = Modifier.size(20.dp),tint = DarkOrange
                        )
                    }
                    Text(text = count.value.toString(), style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = TextColor_1
                    ))
                    Button(
                        onClick = {
                            if (count.value>0) count.value--
                        } ) {
                        Icon(painter = painterResource(id = R.drawable.minus1), contentDescription ="",
                            modifier = Modifier.size(20.dp),tint = DarkOrange
                        )
                    }
                }
                    Text(text = "$599", style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = DarkOrange
                    ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
            }
        }
    }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(modifier = Modifier.fillMaxWidth(), color = LineColor)
}}