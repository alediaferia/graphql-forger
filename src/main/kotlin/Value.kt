package com.alediaferia.graphql

abstract class Value<T>(val value: T) {
    abstract fun asString(): String
}