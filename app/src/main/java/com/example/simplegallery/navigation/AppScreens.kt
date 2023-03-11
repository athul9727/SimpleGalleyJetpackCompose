package com.example.simplegallery.navigation

enum class AppScreens {

    HomeScreen,
    DetailsScreen;

    companion object {
                   fun fromRoute(route:String?): AppScreens {
                       return when(route?.substringBefore("/")){
                                HomeScreen.name ->  HomeScreen
                                DetailsScreen.name ->  DetailsScreen
                                null -> HomeScreen
                                else -> throw java.lang.IllegalArgumentException("Route $route is not recognized")
                       }
                   }
    }
}