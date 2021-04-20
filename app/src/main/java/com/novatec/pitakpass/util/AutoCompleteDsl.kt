package com.novatec.pitakpass.util

import com.novatec.pitakpass.util.AutoCompleteManager

/**
 * Created by jahon on 22-Jul-20
 */
fun buildAutoCompleteManager(lambda: AutoCompleteManager.Builder.() -> Unit) =
    AutoCompleteManager.Builder().apply(lambda).create()