package com.alediaferia.graphqlforgr

abstract class Value<T>(val value: T) {
    abstract fun asString(): String
}