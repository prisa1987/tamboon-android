package com.omise.tamboon.presentation

import com.omise.tamboon.presentation.charities.CharitiesViewModel
import com.omise.tamboon.presentation.charities.di.CharityModule
import dagger.Component

@Component(modules = arrayOf(CharityModule::class))
interface AppComponent {

    fun inject(charitiesViewModel: CharitiesViewModel)

}