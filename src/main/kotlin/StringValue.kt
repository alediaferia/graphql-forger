package com.alediaferia.graphql

class StringValue(value: String) : Value<String>(value) {
    override fun asString(): String = "\"$value\""
}