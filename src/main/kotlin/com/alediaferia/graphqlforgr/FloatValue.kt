package com.alediaferia.graphqlforgr

class FloatValue(value: Float) : Value<Float>(value) {
    override fun asString(): String = "$value"
}