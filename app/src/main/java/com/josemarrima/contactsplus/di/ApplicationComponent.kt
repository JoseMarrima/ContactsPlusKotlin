package com.josemarrima.contactsplus.di

import android.content.Context
import com.josemarrima.contactsplus.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    ViewModelFactoryModule::class,
    FragmentBuilderModule::class,
    AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}