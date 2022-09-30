package com.itlifelang.jetnewremake.di

import javax.inject.Qualifier

@Qualifier
annotation class DefaultDispatcher

@Qualifier
annotation class IoDispatcher

@Qualifier
annotation class MainDispatcher

@Qualifier
annotation class MainIntermediateDispatcher

@Qualifier
annotation class ApplicationScope
