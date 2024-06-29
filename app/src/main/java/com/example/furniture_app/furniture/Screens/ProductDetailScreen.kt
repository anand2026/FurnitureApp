package com.example.furniture_app.furniture.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.furniture_app.R
import com.example.furniture_app.furniture.data.popularProductList
import com.example.furniture_app.furniture.navigation.checkOut
import com.example.furniture_app.ui.theme.Background_1
import com.example.furniture_app.ui.theme.DarkOrange
import com.example.furniture_app.ui.theme.GrayColor
import com.example.furniture_app.ui.theme.LightGray_1
import com.example.furniture_app.ui.theme.LightOrange
import com.example.furniture_app.ui.theme.LineColor
import com.example.furniture_app.ui.theme.TextColor_1


@Composable
fun ProductDetailScreen(
    navHostController: NavHostController
) {
    val chips= listOf("Description","Material","Reviews")
    val selectedChip= remember {
        mutableStateOf(0)
    }
    Box(
        modifier=Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.product_four), contentDescription ="",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp), contentScale = ContentScale.FillWidth
            )
        headerSection(
            onBack = {
                navHostController.navigateUp()
            }
        )
        Box(modifier = Modifier
            .padding(top = 230.dp)
            .fillMaxSize()
            .background(Color.White)){
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 70.dp)
            ) {
                item { ProductHeaderSection()
                    ratingRow()
                    Spacer(modifier = Modifier.height(15.dp))
                    Row{
                        chips.forEachIndexed { index, s ->
                            CustomChips(title = s, selected = selectedChip.value == index, index = index) {
                                selectedChip.value = index
                            }}
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                        style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = LightGray_1),modifier = Modifier.padding(20.dp))
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(Modifier.fillMaxWidth(), color = GrayColor, thickness = 5.dp)
                    RecommendedProduct()
                }
            }
        }
        BottomBarItem{
            navHostController.navigate(checkOut)
        }
    }
}

@Composable
fun BoxScope.BottomBarItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit={}
) {
   Column(modifier= modifier
       .fillMaxWidth()
       .align(Alignment.BottomCenter)) {
       Divider(Modifier.fillMaxWidth(), color = LineColor, thickness = 1.dp)
       Row (modifier= modifier
           .padding(20.dp)
           .fillMaxWidth()){


       TextButton(onClick = { },
           shape = RoundedCornerShape(8.dp),
           modifier = Modifier
               .size(40.dp)
               .weight(1f),
           border = BorderStroke(1.dp, LightGray_1),
           elevation = ButtonDefaults.buttonElevation(0.dp),
           colors = ButtonDefaults.buttonColors(
               containerColor = Color.White,
           )
           ) {
           Icon(
               imageVector = Icons.Default.FavoriteBorder, contentDescription = "",
               modifier = modifier.size(16.dp), tint = LightGray_1
           )
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
               Text(text = "Add to Cart", style = TextStyle(
                   fontSize = 16.sp,
                   fontWeight = FontWeight.W600,
                   color = Color.White
               ))
           }
       }
   }
}
@Composable
fun RecommendedProduct() {
    Column (modifier = Modifier.padding(20.dp)){
        Text(text = "Sofa you might like", style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = TextColor_1
        ))
        LazyRow {
            items(popularProductList.size){
                popularProductList.forEach{
                    PopularEachRow(popular = it, modifier = Modifier.padding(end = 15.dp))
                }
            }
        }
    }
}
@Composable
fun CustomChips(
    title: String,
    selected: Boolean,
    index: Int,
    modifier: Modifier = Modifier,
    onValueChange: (Int) -> Unit
    ) {
    TextButton(onClick = { onValueChange(index) }, shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) LightOrange else Color.Transparent,
            contentColor = if (selected) DarkOrange else LightGray_1
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        modifier = modifier.padding(start = 20.dp)
        ) {
        Text(text = title, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            color = TextColor_1
        ))
    }
}
@Composable
fun ratingRow() {
    val personIcons=listOf(
       R.drawable.person_1
        ,R.drawable.person_2
        ,R.drawable.person_3
        ,R.drawable.person_4
    )
    Box(
        modifier = Modifier
            .padding(20.dp)
            .background(Background_1, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Row (modifier = Modifier.padding(15.dp)){
            Column (modifier = Modifier.weight(1f)){
                Row {


           repeat(5){
               Image(
                   painter = painterResource(id = R.drawable.star), contentDescription = "",
                   modifier = Modifier
                       .size(20.dp),
                   )

           }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "4.5", style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = TextColor_1),
                        modifier = Modifier.align(Alignment.CenterVertically)
                        )
                }

        Spacer(modifier = Modifier.height(12.dp))
        Row (){
            Text(text = "98 Reviews",style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = LightGray_1
            ))
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterVertically),
                tint = LightGray_1
                )
        }
    }
            Row (modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End){
                personIcons.forEach {
                    Image(painter = painterResource(id = it), contentDescription = "",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically))
                }
            }
}
    }}

@Composable
fun ProductHeaderSection() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ){
        Text(text = "Jan Sflanaganvik Sofa", style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = TextColor_1
        ))
        Row(modifier=Modifier.fillMaxWidth()) {
            Text(text = "$599", style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = DarkOrange
            )

            )
            ProductCountButton()
        }
    }
}

@Composable
fun ProductCountButton(
    onClink: () -> Unit={}
) {
    val count= remember {
        mutableStateOf(0)
    }
    Row{
        Button(
            onClick = {
                if (count.value>0) count.value--
            } ) {
            Icon(painter = painterResource(id = R.drawable.minus1), contentDescription ="",
                modifier = Modifier.size(30.dp),tint = DarkOrange
            )
        }

            Spacer(modifier = Modifier.width(10.dp))
        Text(text = count.value.toString(), style = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.W600,
            color = TextColor_1
        ))
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            onClick = { count.value++}) {
            Icon(painter = painterResource(id = R.drawable.plus1), contentDescription ="",
                modifier = Modifier.size(30.dp),tint = DarkOrange
            )
        }
}
}
@Composable
fun headerSection(
    onBack: () -> Unit={}
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween
    ){
        IconButton(onClick = { onBack() }) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription ="",
                modifier = Modifier
                    .size(52.dp)
                    .padding(top = 12.dp),tint = Color.Black
            )
        }
        Icon(painter = painterResource(id = R.drawable.shareicon), contentDescription = "",
            modifier = Modifier
                .size(42.dp)
                .padding(top = 12.dp),tint = Color.White
            )
    }
}