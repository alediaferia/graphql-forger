package com.alediaferia.graphqlforgr

class IntValue(value: Int) : Value<Int>(value) {
    override fun asString(): String = "$value"
}