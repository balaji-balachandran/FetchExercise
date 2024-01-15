package com.example.fetchexercise.View

import android.graphics.BlurMaskFilter
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchexercise.Model.Entry
import com.example.fetchexercise.R
import com.example.fetchexercise.ViewModel.EntryViewModel

@Composable
fun EntryList(viewModel: EntryViewModel = viewModel()) {

    val entries by viewModel.entries.collectAsState(listOf(Entry(id= 1, name = "Balaji", listId = 2)))

    // Fetch data when the List Object is created
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()

    }

    // Utilize Lazy to efficiently handle rendering of elements
    LazyColumn(modifier = Modifier.padding(12.dp)) {
        items(entries) { entry ->
            EntryItem(entry)
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}

@Composable
fun EntryIte(entry: Entry) {
    // Display individual entry details here
    Row(modifier = Modifier
//        .border(1.dp,
//            Color.Black,
//            shape= RoundedCornerShape(10.dp)
//        )
        .clickable {  }
        .clip(shape = RoundedCornerShape(12.dp))
        .shadow(
            color = Color.LightGray,
            offsetX = (4).dp,
            offsetY = (4).dp,
            blurRadius = 8.dp,
        )
        .padding(
            start = 16.dp,
            end = 16.dp,
            top = 12.dp,
            bottom = 16.dp,
        )){
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .height(96.dp)){

            Text(text = entry.name ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                id = R.color.black
                ), maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "ID: ${entry.id}",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.purple_500)
                )

                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    text = "List ID: ${entry.listId}",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.purple_500)
                )
            }

        }
    }
}

fun Modifier.shadow(
    color: Color = Color.Black,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            frameworkPaint.color = color.toArgb()

            val leftPixel = offsetX.toPx()
            val topPixel = offsetY.toPx()
            val rightPixel = size.width + topPixel
            val bottomPixel = size.height + leftPixel

            canvas.drawRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                paint = paint,
            )
        }
    }
)


@Composable
fun EntryItem(entry: Entry){
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        ),
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .padding(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Image(
//                painter = painterResource(id = imageResourceId),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(56.dp)
//                    .clip(MaterialTheme.shapes.extraSmall)
//                    .padding(start = 0.dp, end = 8.dp)
//            )
//            Spacer(Modifier.width(8.dp))

            Text(
                entry.name ?: "",
                Modifier.weight(2f),
                style = MaterialTheme.typography.bodyLarge
            )
//            Box(Modifier.padding(8.dp)) {
//                RadioButton(selected, onClick = null)
//            }
        }
    }
}