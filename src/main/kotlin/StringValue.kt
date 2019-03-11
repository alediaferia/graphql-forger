package com.alediaferia.graphql

class StringValue(val value: String?) : Value() {
    override fun asString(): String = "\"$value\""
}