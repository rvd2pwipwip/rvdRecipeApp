package com.hdesrosiers.rvdrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
// import androidx.fragment.app.activityViewModels //if we want to share the ViewModel between activities
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hdesrosiers.rvdrecipeapp.presentation.BaseApplication
import com.hdesrosiers.rvdrecipeapp.presentation.components.*
import com.hdesrosiers.rvdrecipeapp.presentation.components.util.SnackbarController
import com.hdesrosiers.rvdrecipeapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val snackbarController = SnackbarController(lifecycleScope)

    // instantiate ViewModel inside fragment
    val viewModel: RecipeListViewModel by viewModels()

    @ExperimentalMaterialApi //SnackbarHostState is experimental
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                // Wrap content to apply selected theme
                AppTheme(
                    darkTheme = application.isDark.value
                ) {
                    // mutable state values
                    val recipes = viewModel.recipes.value // observable data

                    // Mutable data structure that will be passed to the TextField
                    val query = viewModel.query.value // from viewModel to persist configuration change
                    // another way of persisting data with savedInstanceState
//                val _query = savedInstanceState{ "Beef" }

                    val selectedCategory = viewModel.selectedCategory.value

                    val loading = viewModel.loading.value

                    val page = viewModel.page.value

                    val scaffoldState = rememberScaffoldState()

                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged, // method reference to delegate
                                onExecuteSearch = {
                                    if (viewModel.selectedCategory.value?.value == "Milk") {
                                        snackbarController.getScope().launch {
                                            snackbarController.showSnackbar(
                                                scaffoldState = scaffoldState,
                                                message = "Invalid category: MILK!",
                                                actionLabel = "Hide",
                                            )
                                        }
                                    } else run {
                                        viewModel.newSearch()
                                    }
                                },
                                scrollPosition = viewModel.categoryScrollPosition,
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onChangedCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                                onToggleTheme = {
                                    application.toggleTheme()
                                }
                            )
                        },
                        bottomBar = {
                            MyBottomBar()
                        },
                        drawerContent = {
                            MyDrawer()
                        },
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        },
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colors.surface
                                )
                                .padding(bottom = 50.dp)
                        ) {
                            if (loading && recipes.isEmpty()) {
                                LoadingRecipeListShimmer(imageHeight = 250.dp)
                            } else {
                                LazyColumn(content = {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        viewModel.onChangeRecipeScrollPosition(index)
                                        if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                                            viewModel.nextPage()
                                        }
                                        RecipeCard(recipe = recipe, onClick = { /*TODO*/ })
                                    }
                                })
                            }
                            CircularIndeterminateProgressBar(isDisplayed = loading)
                            DefaultSnackbar(
                                snackbarHostState = scaffoldState.snackbarHostState,
                                onDismiss = {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )
                        }
                    }
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun DecoupledSnackbarDemo(
    snackbarHostState: SnackbarHostState // manages snackbar queue and animation
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val snackbar = createRef()
        SnackbarHost(
            modifier = Modifier
                .constrainAs(snackbar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            hostState = snackbarHostState,
            snackbar = {
                Snackbar(
                    action = {
                        TextButton(
                            onClick = {
                                snackbarHostState.currentSnackbarData?.dismiss()
                            }
                        ) {
                            Text(
                                text = snackbarHostState.currentSnackbarData?.actionLabel?: "Hide",
                                style = TextStyle(color = Color.White)
                            )
                        }
                    }
                ) {
                    Text(
                        text = snackbarHostState.currentSnackbarData?.message?: ""
                    )
                }
            }
        )
    }
}

@Composable
fun SnackbarDemo(
    isShowing: Boolean,
    onHideSnackbar: () -> Unit,
) {
    if (isShowing) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val snackbar = createRef()
            Snackbar(
                modifier = Modifier
                    .constrainAs(snackbar) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                action = {
                    Text(
                        text = "Hide",
                        modifier = Modifier
                            .clickable(
                                onClick = onHideSnackbar,
                            ),
                        style = MaterialTheme.typography.h5
                    )
                }
            ) {
                Text(
                    text = "Hey, look, a snackbar"
                )
            }
        }
    }
}

@Composable
fun MyBottomBar() {
    BottomNavigation(
        elevation = 12.dp
    ) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Home) },
            selected = true,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Search) },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Person) },
            selected = false,
            onClick = { /*TODO*/ }
        )
    }
}

@Composable
fun MyDrawer() {
    Column() {
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
        Text(text = "Item 4")
        Text(text = "Item 5")
    }
}


@Composable
fun GradientDemo() {
    val colors = listOf(
        Color.Blue,
        Color.Green,
        Color.Blue
    )
    val brush = linearGradient(
        colors = colors,
        start = Offset(200f,100f),
        end = Offset(400f, 300f)
    )
    Surface(
        shape = MaterialTheme.shapes.small
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = brush
                )
        )
    }
}
