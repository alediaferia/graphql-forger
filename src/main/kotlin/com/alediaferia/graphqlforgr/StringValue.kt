package com.alediaferia.graphqlforgr

class StringValue(value: String) : Value<String>(value) {
    override fun asString(): String = "\"$value\""
}