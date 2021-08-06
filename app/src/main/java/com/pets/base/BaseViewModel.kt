package com.pets.base

import androidx.lifecycle.ViewModel
import com.pets.core.prefences.PrefManager
import org.koin.java.KoinJavaComponent

/**
 * BaseViewModel.kt
 * An abstract Base View model to share common behavior and additionally clear navigation reference upon invalidation.
 */
abstract class BaseViewModel<View, Repository> : ViewModel() {

    protected val prefManager: PrefManager by KoinJavaComponent.inject(
        PrefManager::class.java
    )

    private var repo: Repository? = null
    abstract fun setRepo(): Repository
    protected fun getRepo(): Repository {
        if (repo == null)
            throw NullPointerException("View is null please call attach method 1st")

        return repo!!
    }

    private var view: View? = null
    open fun attachView(view: View) {
        this.repo = setRepo()
        this.view = view
    }

    protected fun getView(): View {
        if (view == null)
            throw NullPointerException("View is null please call attach method 1st")

        return view!!
    }

    override fun onCleared() {
        view = null
        repo = null
    }
}