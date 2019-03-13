package com.alediaferia.graphql

class IntValue(value: Int) : Value<Int>(value) {
    override fun asString(): String = "$value"
}