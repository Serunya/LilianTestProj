package com.tailspin.liliantestproj

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tailspin.liliantestproj.data.repository.CheckDtoRepositoryImpl
import com.tailspin.liliantestproj.data.source.remote.api.CheckDtoApiMockImpl
import com.tailspin.liliantestproj.domain.usecase.GetCheckDtoUseCase
import com.tailspin.liliantestproj.ui.Screen
import com.tailspin.liliantestproj.ui.bunker.BunkerScreen
import com.tailspin.liliantestproj.ui.bunker.BunkerViewModel
import com.tailspin.liliantestproj.ui.local.DateFormatter
import com.tailspin.liliantestproj.ui.local.LocalDateFormatter
import com.tailspin.liliantestproj.ui.theme.LilianTestProjTheme

class MainActivity : ComponentActivity() {
    private val bunkerViewModel by viewModels<BunkerViewModel> { BunkerViewModel.Factory }

    private val dateFormatter = DateFormatter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContent {
            LilianTestProjTheme {
                CompositionLocalProvider(LocalDateFormatter provides dateFormatter) {
                    App()
                }
            }
        }
    }

    @Composable
    fun App() {
        var currentScreen by rememberSaveable { mutableStateOf(Screen.BUNKER) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(showMenuButton = currentScreen != Screen.MENU) {
                    currentScreen = Screen.MENU
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentScreen) {
                    Screen.MENU -> MenuScreen { currentScreen = it }
                    Screen.BUNKER -> BunkerScreen(bunkerViewModel = bunkerViewModel)
                }
            }
        }
    }


}


@Composable
fun TopBar(showMenuButton: Boolean, onMenuButtonClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Image(
                painter = painterResource(id = R.drawable.lilianilogo),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(130.dp, 31.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.help_phone_number),
                modifier = Modifier.size(221.dp, 23.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.help_link),
            )
            Spacer(modifier = Modifier.weight(1f))
            if (showMenuButton) {
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFFD9D9D9),
                            shape = MaterialTheme.shapes.extraSmall
                        )
                        .size(140.dp, 30.dp)
                        .clickable(onClick = onMenuButtonClick),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Меню",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF000000),
                        modifier = Modifier.wrapContentHeight()
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(140.dp))
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}


@Composable
fun MenuScreen(navigate: (Screen) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Это Меню")
        Button(onClick = { navigate(Screen.BUNKER) }) {
            Text(text = "В Бункер")
        }
    }
}
