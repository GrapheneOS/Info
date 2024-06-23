package app.grapheneos.info

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.VolunteerActivism
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import app.grapheneos.info.preferences.PreferencesViewModel
import app.grapheneos.info.ui.community.CommunityScreen
import app.grapheneos.info.ui.donate.DonateStartScreen
import app.grapheneos.info.ui.donate.GithubSponsorsScreen
import app.grapheneos.info.ui.donate.PaypalScreen
import app.grapheneos.info.ui.donate.banktransfers.BankTransfersScreen
import app.grapheneos.info.ui.donate.cryptocurrency.BitcoinScreen
import app.grapheneos.info.ui.donate.cryptocurrency.CardanoScreen
import app.grapheneos.info.ui.donate.cryptocurrency.DonateCryptoCurrencyStartScreen
import app.grapheneos.info.ui.donate.cryptocurrency.EthereumScreen
import app.grapheneos.info.ui.donate.cryptocurrency.LitecoinScreen
import app.grapheneos.info.ui.donate.cryptocurrency.MoneroScreen
import app.grapheneos.info.ui.donate.cryptocurrency.ZcashScreen
import app.grapheneos.info.ui.releasenotes.ReleaseNotesScreen
import app.grapheneos.info.ui.releasenotes.ReleaseNotesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class InfoAppScreens(@StringRes val title: Int) {
    ReleaseNotes(title = R.string.release_notes),
    Community(title = R.string.community),
    Donate(title = R.string.donate),
    DonateStart(title = R.string.donate),
    DonateGithubSponsors(title = R.string.github_sponsors),
    DonateCryptocurrencies(title = R.string.cryptocurrencies),
    DonateCryptocurrenciesStart(title = R.string.cryptocurrencies),
    DonateCryptocurrenciesBitcoin(title = R.string.bitcoin),
    DonateCryptocurrenciesMonero(title = R.string.monero),
    DonateCryptocurrenciesZcash(title = R.string.zcash),
    DonateCryptocurrenciesEthereum(title = R.string.ethereum),
    DonateCryptocurrenciesCardano(title = R.string.cardano),
    DonateCryptocurrenciesLitecoin(title = R.string.litecoin),
    DonatePaypal(title = R.string.paypal),
    DonateBankTransfers(title = R.string.bank_transfers)
}

val navBarScreens = listOf(
    InfoAppScreens.ReleaseNotes,
    InfoAppScreens.Community,
    InfoAppScreens.Donate
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoApp(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val preferencesViewModel: PreferencesViewModel = viewModel()

    val preferencesUiState by preferencesViewModel.uiState.collectAsState()

    /**
     * Gets the initial value from preferencesUiState.initialStartDestination.second
     * but doesn't change when it changes until ON_STOP
     * so the user doesn't get a wierd animation every time they navigate through the bottom navigation bar.
     */
    var startDestination by rememberSaveable { preferencesUiState.startDestination.second }

    val currentScreen = InfoAppScreens.valueOf(backStackEntry?.destination?.route ?: startDestination)

    val navBarSelected = navBarScreens.find {
        currentScreen.name.startsWith(it.name)
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                startDestination = preferencesUiState.startDestination.second.value
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val releaseNotesViewModel: ReleaseNotesViewModel = viewModel()

    val releaseNotesUiState = releaseNotesViewModel.uiState.collectAsState()

    val releaseNotesLazyListState = rememberLazyListState()

    val releaseNotesLazyListStateScope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    val snackbarCoroutine = rememberCoroutineScope()

    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
            .animateContentSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = currentScreen.title),
                        style = typography.headlineMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    if (!((currentScreen == InfoAppScreens.ReleaseNotes) || (currentScreen == InfoAppScreens.Community) || (currentScreen == InfoAppScreens.DonateStart))) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.navigate_up_button_description)
                            )
                        }
                    }
                },
                scrollBehavior = topAppBarScrollBehavior
            )
        },
        bottomBar = {
            NavigationBar {
                navBarScreens.forEach { navBarScreen ->
                    NavigationBarItem(
                        selected = navBarScreen == navBarSelected,
                        onClick = {
                            navController.navigate(navBarScreen.name) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            navBarScreen.let {
                                preferencesViewModel.setPreference(
                                    preferencesUiState.startDestination.first,
                                    it.name
                                )
                            }
                        },
                        icon = {
                            when (navBarScreen) {
                                InfoAppScreens.ReleaseNotes -> Icon(
                                    painter = painterResource(id = R.drawable.outline_newsmode),
                                    contentDescription = null
                                )

                                InfoAppScreens.Community -> Icon(
                                    imageVector = Icons.Outlined.Forum,
                                    contentDescription = null
                                )

                                InfoAppScreens.Donate -> Icon(
                                    imageVector = Icons.Outlined.VolunteerActivism,
                                    contentDescription = null
                                )

                                else -> {}
                            }
                        },
                        label = { Text(text = stringResource(id = navBarScreen.title)) }
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding),
        ) {
            composableWithDefaultSlideTransitions(
                route = InfoAppScreens.ReleaseNotes,
            ) {
                ReleaseNotesScreen(
                    releaseNotesUiState.value.entries.toSortedMap().toList().asReversed(),
                    { useCaches, onFinishedUpdating ->
                        releaseNotesViewModel.updateReleaseNotes(
                            useCaches = useCaches,
                            showSnackbarError = {
                                snackbarHostState.showSnackbar(it)
                            },
                            {
                                releaseNotesLazyListStateScope.launch {
                                    withContext(Dispatchers.Main) {
                                        releaseNotesLazyListState.animateScrollToItem(it)
                                    }
                                }
                            },
                            onFinishedUpdating = onFinishedUpdating,
                        )
                    },
                    releaseNotesLazyListState,
                )
            }
            composableWithDefaultSlideTransitions(
                route = InfoAppScreens.Community,
            ) {
                CommunityScreen()
            }
            navigationWithDefaultSlideTransitions(
                route = InfoAppScreens.Donate,
                startDestination = InfoAppScreens.DonateStart.name,
            ) {
                composableWithDefaultSlideTransitions(
                    route = InfoAppScreens.DonateStart,
                ) {
                    DonateStartScreen(
                        onNavigateToGithubSponsorsScreen = {
                            navController.navigate(InfoAppScreens.DonateGithubSponsors.name)
                        },
                        onNavigateToCryptocurrenciesScreen = {
                            navController.navigate(InfoAppScreens.DonateCryptocurrencies.name)
                        },
                        onNavigateToPayPalScreen = {
                            navController.navigate(InfoAppScreens.DonatePaypal.name)
                        },
                        onNavigateToBankTransfersScreen = {
                            navController.navigate(InfoAppScreens.DonateBankTransfers.name)
                        }
                    )
                }
                composableWithDefaultSlideTransitions(
                    route = InfoAppScreens.DonateGithubSponsors,
                ) {
                    GithubSponsorsScreen()
                }
                navigationWithDefaultSlideTransitions(
                    route = InfoAppScreens.DonateCryptocurrencies,
                    startDestination = InfoAppScreens.DonateCryptocurrenciesStart.name
                ) {
                    composableWithDefaultSlideTransitions(
                        route = InfoAppScreens.DonateCryptocurrenciesStart,
                    ) {
                        DonateCryptoCurrencyStartScreen(
                            onNavigateToBitcoinScreen = {
                                navController.navigate(InfoAppScreens.DonateCryptocurrenciesBitcoin.name)
                            },
                            onNavigateToMoneroScreen = {
                                navController.navigate(InfoAppScreens.DonateCryptocurrenciesMonero.name)
                            },
                            onNavigateToZcashScreen = {
                                navController.navigate(InfoAppScreens.DonateCryptocurrenciesZcash.name)
                            },
                            onNavigateToEthereumScreen = {
                                navController.navigate(InfoAppScreens.DonateCryptocurrenciesEthereum.name)
                            },
                            onNavigateToCardanoScreen = {
                                navController.navigate(InfoAppScreens.DonateCryptocurrenciesCardano.name)
                            },
                            onNavigateToLitecoinScreen = {
                                navController.navigate(InfoAppScreens.DonateCryptocurrenciesLitecoin.name)
                            }
                        )
                    }
                    composableWithDefaultSlideTransitions(route = InfoAppScreens.DonateCryptocurrenciesBitcoin) {
                        BitcoinScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            },
                        )
                    }
                    composableWithDefaultSlideTransitions(route = InfoAppScreens.DonateCryptocurrenciesMonero) {
                        MoneroScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                    composableWithDefaultSlideTransitions(route = InfoAppScreens.DonateCryptocurrenciesZcash) {
                        ZcashScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                    composableWithDefaultSlideTransitions(route = InfoAppScreens.DonateCryptocurrenciesEthereum) {
                        EthereumScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                    composableWithDefaultSlideTransitions(route = InfoAppScreens.DonateCryptocurrenciesCardano) {
                        CardanoScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                    composableWithDefaultSlideTransitions(route = InfoAppScreens.DonateCryptocurrenciesLitecoin) {
                        LitecoinScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                }
                composableWithDefaultSlideTransitions(route = InfoAppScreens.DonatePaypal) {
                    PaypalScreen()
                }
                composableWithDefaultSlideTransitions(route = InfoAppScreens.DonateBankTransfers) {
                    BankTransfersScreen()
                }
            }
        }
    }
}

fun getStateNavBarRoute(state: NavBackStackEntry): InfoAppScreens? {
    state.destination.route?.let { return InfoAppScreens.valueOf(it) }
    return null
}

fun getEnterTransition(initialState: NavBackStackEntry, targetState: NavBackStackEntry): EnterTransition {
    val initialNavBarRoute = getStateNavBarRoute(initialState)
    val targetNavBarRoute = getStateNavBarRoute(targetState)

    return if ((initialNavBarRoute != null) && (targetNavBarRoute != null)) {
        slideIn {
            IntOffset(
                if (initialNavBarRoute.ordinal > targetNavBarRoute.ordinal) {
                    -it.width
                } else {
                    it.width
                },
                0
            )
        } + fadeIn()
    } else {
        EnterTransition.None
    }
}

fun getExitTransition(initialState: NavBackStackEntry, targetState: NavBackStackEntry): ExitTransition {
    val initialNavBarRoute = getStateNavBarRoute(initialState)
    val targetNavBarRoute = getStateNavBarRoute(targetState)

    return if ((initialNavBarRoute != null) && (targetNavBarRoute != null)) {
        slideOut {
            IntOffset(
                if (initialNavBarRoute.ordinal > targetNavBarRoute.ordinal) {
                    it.width
                } else {
                    -it.width
                },
                0
            )
        } + fadeOut()
    } else {
        ExitTransition.None
    }
}

fun NavGraphBuilder.composableWithDefaultSlideTransitions(
    route: InfoAppScreens,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    popExitTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    content: @Composable() (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable(
        route.name,
        arguments,
        deepLinks,
        if (enterTransition == null) {
            {
                getEnterTransition(initialState, targetState)
            }
        } else {
            null
        },
        if (exitTransition == null) {
            {
                getExitTransition(initialState, targetState)
            }
        } else {
            null
        },
        if (popEnterTransition == null) {
            {
                getEnterTransition(initialState, targetState)
            }
        } else {
            null
        },
        if (popExitTransition == null) {
            {
                getExitTransition(initialState, targetState)
            }
        } else {
            null
        },
        content
    )
}

fun NavGraphBuilder.navigationWithDefaultSlideTransitions(
    startDestination: String,
    route: InfoAppScreens,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: @JvmSuppressWildcards() (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? =
    null,
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? =
    null,
    popEnterTransition: (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
    )? = enterTransition,
    popExitTransition: (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
    )? = exitTransition,
    builder: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination,
        route.name,
        arguments,
        deepLinks,
        if (enterTransition == null) {
            {
                getEnterTransition(initialState, targetState)
            }
        } else {
            null
        },
        if (exitTransition == null) {
            {
                getExitTransition(initialState, targetState)
            }
        } else {
            null
        },
        if (popEnterTransition == null) {
            {
                getEnterTransition(initialState, targetState)
            }
        } else {
            null
        },
        if (popExitTransition == null) {
            {
                getExitTransition(initialState, targetState)
            }
        } else {
            null
        },
        builder
    )
}