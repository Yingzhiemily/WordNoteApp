package com.example.wordnote.feature.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wordnote.R
import com.example.wordnote.components.bottomBar.BottomBarView
import com.example.wordnote.ui.theme.Blue200
import com.example.wordnote.ui.theme.Blue300


@Composable
fun SearchScreen(navHostController: NavHostController, viewModel: SearchViewModel) {
    val wordResults by viewModel.searchResults.observeAsState(initial = emptyList())
    val isSearching by viewModel.viewState.observeAsState(initial = SearchViewModel.ViewState.Initial)

    Scaffold(
        bottomBar = { BottomBarView(navHostController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var text by remember { mutableStateOf(TextFieldValue("")) }
            val focusRequester = remember { FocusRequester() }
            val focusManager = LocalFocusManager.current

            if (text.text.isEmpty()) {
                focusManager.clearFocus()
            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                OutlinedTextField(
                    value = text,
                    maxLines = 1,
                    label = { Text(text = stringResource(R.string.label_text)) },
                    placeholder = { Text(text = stringResource(R.string.placeholder_text)) },
                    modifier = Modifier
                        .focusRequester(focusRequester),
                    onValueChange = {
                        text = it
                    },
                    trailingIcon = {
                        if (text.text.isNotEmpty()) {
                            IconButton(
                                onClick = { text = TextFieldValue("") }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
                        textColor = Color.Gray,
                        cursorColor = MaterialTheme.colors.primary,
                        focusedLabelColor = MaterialTheme.colors.primaryVariant,
                        unfocusedLabelColor = MaterialTheme.colors.primary
                    )
                )
                IconButton(
                    onClick = {
                        if (text.text.isNotBlank()) {
                            viewModel.searchWord(text.text)
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(45.dp)
                        .padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                }
            }

            when {
                isSearching == SearchViewModel.ViewState.Loading -> {
                    // loading status
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
                wordResults.isEmpty() -> {
                    Text(
                        text = stringResource(R.string.page_empty_text),
                        modifier = Modifier
                            .padding(16.dp),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary
                        )

                    )
                }
                else -> {
                    // word result display
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        wordResults.forEach { result ->
                            if (result.word != null && result.meanings.isNotEmpty()) {
                                val meaning = result.meanings.firstOrNull()
                                val definition = meaning?.definitions?.firstOrNull()
                                val definitionText = definition?.definition ?: ""

                                Text(
                                    text = result.word,
                                    modifier = Modifier.padding(8.dp),
                                    textAlign = TextAlign.Start,
                                    style = TextStyle(
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colors.primary
                                    )
                                )
                                Text(
                                    text = definitionText,
                                    modifier = Modifier.padding(8.dp),
                                    textAlign = TextAlign.Start,
                                    style = TextStyle(
                                        fontSize = 24.sp,
                                        color = Color.Gray
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
