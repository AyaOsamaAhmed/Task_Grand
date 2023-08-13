package com.aya.taskgrand.base

import android.os.Parcelable

interface BaseParcelable : Parcelable {
    fun unique(): Any
}