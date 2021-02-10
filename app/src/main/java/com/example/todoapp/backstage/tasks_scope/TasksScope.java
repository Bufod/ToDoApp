package com.example.todoapp.backstage.tasks_scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Область видимости для компонент и провайдеров необходимых для работы с задачами
 * @see
 * <ul>
 *     <Li>
 *         <a href="https://proandroiddev.com/dagger-2-component-relationships-custom-scopes-8d7e05e70a37">
 *             Dagger 2 : Component Relationships & Custom Scopes
 *         </a>
 *     </Li>
 *     <Li>
 *         <a href="https://proandroiddev.com/dagger-2-custom-scopes-6c2339f3350a">
 *             Custom Scopes
 *         </a>
 *     </Li>
 *     <Li>
 *         <a href="https://proandroiddev.com/dagger-2-custom-scopes-6c2339f3350a">
 *             Dagger 2: Custom Scopes and Subcomponent
 *         </a>
 *     </Li>
 * </ul>
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TasksScope {

}