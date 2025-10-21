package com.example.githubaction.calculator

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Defines the scope for the bindings in this module
abstract class AppModule {

    @Binds
    @Singleton // Optional: Makes Hilt provide the same instance every time
    abstract fun bindCalculator(
        // The parameter is the implementation class Hilt already knows how to create
        calculatorImpl: CalculatorImpl
    ): ICalculator // The return type is the interface we want to provide
}