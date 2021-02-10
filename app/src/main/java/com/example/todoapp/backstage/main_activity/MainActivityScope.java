package com.example.todoapp.backstage.main_activity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

//Link: https://proandroiddev.com/dagger-2-component-relationships-custom-scopes-8d7e05e70a37
//Link: https://proandroiddev.com/dagger-2-custom-scopes-6c2339f3350a
//Link: https://chetan.medium.com/dagger-2-custom-scopes-and-subcomponent-33b85897efd2
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MainActivityScope {

}
