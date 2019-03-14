package com.alediaferia.graphqlforgr

class BooleanValue(value: Boolean) : Value<Boolean>(value) {
    override fun asString(): String = "$value"
}