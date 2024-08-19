package com.tailspin.liliantestproj.ui.bunker

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.tailspin.liliantestproj.R
import com.tailspin.liliantestproj.data.repository.CheckDtoRepositoryImpl
import com.tailspin.liliantestproj.data.source.remote.api.CheckDtoApiMockImpl
import com.tailspin.liliantestproj.data.source.remote.dto.CheckDTO
import com.tailspin.liliantestproj.data.source.remote.dto.CheckOperation
import com.tailspin.liliantestproj.domain.usecase.GetCheckDtoUseCase
import com.tailspin.liliantestproj.ui.local.LocalDateFormatter
import com.tailspin.liliantestproj.ui.theme.LilianTestProjTheme


@Composable
fun BunkerScreen(bunkerViewModel: BunkerViewModel) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(35.dp, 23.dp, 35.dp, 36.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            WeightBunker(
                weightBunker = bunkerViewModel.weightBunker,
                weightMemory = bunkerViewModel.weightMemory,
                weightLoaded = bunkerViewModel.weightLoaded
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
                    contentColor = Color(0xFF000000)
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
                onClick = {Toast.makeText(context,"Печать Текста",Toast.LENGTH_SHORT).show()},
                modifier = Modifier.fillMaxHeight(),
                contentPadding = PaddingValues(horizontal = 177.dp)
            ) {
                Text(
                    text = "Печать чека", style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.wrapContentHeight()
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        CheckDetail(bunkerViewModel)
    }
}


@Composable
private fun CheckDetail(bunkerViewModel: BunkerViewModel) {
    val checks = bunkerViewModel.getCheckDto().collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val checkCount by bunkerViewModel.checkCount.collectAsState(initial = 0)
    Row {
        Column {
            ChecksStats(checkCount = checkCount)
            Spacer(modifier = Modifier.height(20.dp))
            ChecksDetailed(checks, listState)
        }
        Spacer(modifier = Modifier.width(22.dp))
        ScrollBar(listState = listState, checkCount)

    }
}


@Composable
private fun ScrollBar(listState: LazyListState, totalElement: Int) {
    val firstVisibleItemIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val layoutInfo by remember { derivedStateOf { listState.layoutInfo } }
    val countItem = layoutInfo.visibleItemsInfo.size
    val scrollOffset = remember { Animatable(0f) }
    val halfTouchHeight = with(LocalDensity.current) {214.dp.toPx() / 2}
    if (totalElement > 0 && countItem > 0) {
        Column {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(32.dp)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .border(2.dp, Color.Gray, MaterialTheme.shapes.extraLarge)
            ) {
                LaunchedEffect(totalElement, firstVisibleItemIndex) {
                    val maxScrollOffset = maxHeight.value - halfTouchHeight
                    val newScrollOffset = (firstVisibleItemIndex.toFloat() / totalElement.toFloat() * maxScrollOffset).coerceIn(0f,maxScrollOffset)
                    scrollOffset.animateTo(newScrollOffset)
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(214.dp)
                        .offset(y = scrollOffset.value.dp)
                        .padding(horizontal = 5.dp, vertical = 9.dp)
                        .width(22.dp)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(Color.White, MaterialTheme.shapes.extraSmall)
                        .border(1.dp, Color.Gray, MaterialTheme.shapes.extraLarge)
                ) {
                    Image(imageVector = Icons.Default.Menu, contentDescription = "")
                }
            }
        }
    }
}


@Composable
private fun WeightBunker(weightBunker: Int, weightMemory: Int, weightLoaded: Int) {
    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = Color(0xFFFFFFFF),
        shadowElevation = 8.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(81.dp, 30.dp, 81.dp, 31.dp)
        ) {
            Text(text = "Вес в Бункере:", style = MaterialTheme.typography.titleMedium)
            Divider(
                Modifier
                    .width(432.dp)
                    .padding(vertical = 26.dp)
            )
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "%,d".format(weightBunker),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.alignByBaseline()
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "кг",
                    fontSize = 64.sp,
                    modifier = Modifier.alignByBaseline()
                )
            }
            Divider(
                Modifier
                    .width(432.dp)
                    .padding(vertical = 26.dp)
            )
            Text(text = "Загружено: $weightLoaded", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Вес в памяти: $weightMemory кг",
                color = Color(0xFFC7C7C7),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun ChecksStats(checkCount: Int) {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = Color(0xFFFFFFFF),
        shadowElevation = 8.dp
    ) {
        Row(
            Modifier.width(423.dp)
        ) {
            Text(
                text = "Не распечатано чеков:",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFFC7C7C7),
                modifier = Modifier.padding(start = 23.dp, top = 20.dp, bottom = 19.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                maxLines = 1,
                text = "$checkCount шт.",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 23.dp)
            )
        }
    }
}

@Composable
private fun ChecksDetailed(checks: LazyPagingItems<CheckDTO>, listState: LazyListState) {
    LazyColumn(state = listState) {
        items(
            count = checks.itemCount,
            key = checks.itemKey { it.uuid }
        ) { index ->
            checks[index]?.let { CheckCard(checkDTO = it) }
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            val state = checks.loadState.refresh
            if (state is LoadState.Error) {
                Text(text = state.error.message.toString())
            }
        }
    }
}

@Composable
private fun CheckCard(checkDTO: CheckDTO) {
    val dateFormatter = LocalDateFormatter.current
    val dateTime = remember { dateFormatter.format(checkDTO.operationDataTime) }
    val carPainterResourceId =
        if (checkDTO.vehicleName.startsWith("Комбайн")) R.drawable.harvester else R.drawable.car
    val loadText = when (checkDTO.checkOperationType) {
        CheckOperation.LOAD -> "Загруженный вес:"
        CheckOperation.UNLOAD -> "Выгруженный вес:"
    }
    Surface(shape = MaterialTheme.shapes.large, shadowElevation = 8.dp) {
        Column(modifier = Modifier.width(423.dp).padding(16.dp)) {
            Row {
                Column {
                    Text(text = dateTime, style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = carPainterResourceId),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = checkDTO.vehicleName,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
                Spacer(modifier = Modifier.width(44.dp))
                Image(
                    painter = painterResource(id = R.drawable.printer),
                    contentDescription = "Print Check"
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "$loadText ${checkDTO.operationWeight} кг",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Остаток: 700 кг", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
