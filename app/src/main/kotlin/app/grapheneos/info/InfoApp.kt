package app.grapheneos.info

import androidx.annotation.StringRes
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
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
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoApp() {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = InfoAppScreens.valueOf(backStackEntry?.destination?.route ?: InfoAppScreens.ReleaseNotes.name)

    val releaseNotesViewModel: ReleaseNotesViewModel = viewModel()

    val releaseNotesUiState = releaseNotesViewModel.uiState.collectAsState()

    val releaseNotesLazyList = rememberLazyListState()

    val snackbarHostState = remember { SnackbarHostState() }

    val snackbarCoroutine = rememberCoroutineScope()

    val navBarSelected = if (currentScreen.name.startsWith("Donate")) {
        InfoAppScreens.Donate
    } else {
        currentScreen
    }

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
                        style = typography.headlineMedium
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
                listOf(
                    InfoAppScreens.ReleaseNotes,
                    InfoAppScreens.Community,
                    InfoAppScreens.Donate
                ).forEach { bottomBarScreen ->
                    NavigationBarItem(
                        selected = bottomBarScreen == navBarSelected,
                        onClick = {
                            navController.navigate(bottomBarScreen.name) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            when (bottomBarScreen) {
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
                        label = { Text(text = stringResource(id = bottomBarScreen.title)) }
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
            startDestination = InfoAppScreens.ReleaseNotes.name,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                slideIn { IntOffset(it.width, 0) } + fadeIn()
            },
            exitTransition = {
                slideOut { IntOffset(-it.width, 0) } + fadeOut()
            },
            popEnterTransition = {
                slideIn { IntOffset(-it.width, 0) } + fadeIn()
            },
            popExitTransition = {
                slideOut { IntOffset(it.width, 0) } + fadeOut()
            }
        ) {
            composable(
                route = InfoAppScreens.ReleaseNotes.name,
                enterTransition = {
                    slideIn { IntOffset(-it.width, 0) } + fadeIn()
                },
                exitTransition = {
                    slideOut { IntOffset(-it.width, 0) } + fadeOut()
                }
            ) {
                ReleaseNotesScreen(
                    releaseNotesUiState.value.entries,
                    { useCaches ->
                        releaseNotesViewModel.updateReleaseNotes(
                            useCaches = useCaches,
                            showSnackbarError = {
                                snackbarHostState.showSnackbar(it)
                            }
                        )
                    },
                    releaseNotesLazyList
                )
            }
            composable(
                route = InfoAppScreens.Community.name,
                enterTransition = {
                    if (getInitialStateNavBarRoute(initialState) == InfoAppScreens.ReleaseNotes) {
                        slideIn { IntOffset(it.width, 0) }
                    } else if (getInitialStateNavBarRoute(initialState) == InfoAppScreens.Donate) {
                        slideIn { IntOffset(-it.width, 0) }
                    } else {
                        slideIn { IntOffset(it.width, 0) }
                    } + fadeIn()
                },
                exitTransition = {
                    if (getTargetStateNavBarRoute(targetState) == InfoAppScreens.ReleaseNotes) {
                        slideOut { IntOffset(it.width, 0) }
                    } else if (getTargetStateNavBarRoute(targetState) == InfoAppScreens.Donate) {
                        slideOut { IntOffset(-it.width, 0) }
                    } else {
                        slideOut { IntOffset(it.width, 0) }
                    } + fadeOut()
                },
            ) {
                CommunityScreen()
            }
            navigation(
                route = InfoAppScreens.Donate.name,
                startDestination = InfoAppScreens.DonateStart.name,
                enterTransition = {
                    if (getInitialStateNavBarRoute(initialState) == InfoAppScreens.ReleaseNotes) {
                        slideIn { IntOffset(it.width, 0) }
                    } else if (getInitialStateNavBarRoute(initialState) == InfoAppScreens.Community) {
                        slideIn { IntOffset(it.width, 0) }
                    } else {
                        slideIn { IntOffset(it.width, 0) }
                    } + fadeIn()
                },
                exitTransition = {
                    if (getTargetStateNavBarRoute(targetState) == InfoAppScreens.ReleaseNotes) {
                        slideOut { IntOffset(it.width, 0) }
                    } else if (getTargetStateNavBarRoute(targetState) == InfoAppScreens.Community) {
                        slideOut { IntOffset(it.width, 0) }
                    } else {
                        slideOut { IntOffset(-it.width, 0) }
                    } + fadeOut()
                },
                popEnterTransition = {
                    if (getInitialStateNavBarRoute(initialState) == InfoAppScreens.ReleaseNotes) {
                        slideIn { IntOffset(it.width, 0) }
                    } else if (getInitialStateNavBarRoute(initialState) == InfoAppScreens.Community) {
                        slideIn { IntOffset(it.width, 0) }
                    } else {
                        slideIn { IntOffset(-it.width, 0) }
                    } + fadeIn()
                },
                popExitTransition = {
                    slideOut { IntOffset(it.width, 0) } + fadeOut()
                }
            ) {
                composable(
                    route = InfoAppScreens.DonateStart.name,
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
                composable(
                    route = InfoAppScreens.DonateGithubSponsors.name,
                ) {
                    GithubSponsorsScreen()
                }
                navigation(
                    route = InfoAppScreens.DonateCryptocurrencies.name,
                    startDestination = InfoAppScreens.DonateCryptocurrenciesStart.name
                ) {
                    composable(
                        route = InfoAppScreens.DonateCryptocurrenciesStart.name,
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
                    composable(route = InfoAppScreens.DonateCryptocurrenciesBitcoin.name) {
                        BitcoinScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            },
                        )
                    }
                    composable(route = InfoAppScreens.DonateCryptocurrenciesMonero.name) {
                        MoneroScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                    composable(route = InfoAppScreens.DonateCryptocurrenciesZcash.name) {
                        ZcashScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                    composable(route = InfoAppScreens.DonateCryptocurrenciesEthereum.name) {
                        EthereumScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                    composable(route = InfoAppScreens.DonateCryptocurrenciesCardano.name) {
                        CardanoScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                    composable(route = InfoAppScreens.DonateCryptocurrenciesLitecoin.name) {
                        LitecoinScreen(
                            showSnackbarError = {
                                snackbarCoroutine.launch {
                                    snackbarHostState.showSnackbar(it)
                                }
                            }
                        )
                    }
                }
                composable(route = InfoAppScreens.DonatePaypal.name) {
                    PaypalScreen()
                }
                composable(route = InfoAppScreens.DonateBankTransfers.name) {
                    BankTransfersScreen()
                }
            }
        }
    }
}

fun getTargetStateNavBarRoute(targetState: NavBackStackEntry): InfoAppScreens {
    return if (targetState.destination.route?.startsWith("Donate") == true) {
        InfoAppScreens.Donate
    } else {
        InfoAppScreens.entries.find {
            it.name == targetState.destination.route
        } ?: InfoAppScreens.ReleaseNotes
    }
}

fun getInitialStateNavBarRoute(initialState: NavBackStackEntry): InfoAppScreens {
    return if (initialState.destination.route?.startsWith("Donate") == true) {
        InfoAppScreens.Donate
    } else {
        InfoAppScreens.entries.find {
            it.name == initialState.destination.route
        } ?: InfoAppScreens.ReleaseNotes
    }
}