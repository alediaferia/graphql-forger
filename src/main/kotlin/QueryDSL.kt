package com.alediaferia.graphql

operator fun String.invoke(block: Field.Builder.() -> Unit): Field.Builder {
    val builder = Field.Builder()
    builder.block()
    return builder.withName(this)
}

infix fun Field.Builder.select(name: String) = selectField(name)

inline fun Field.Builder.select(name: String, block: Field.Builder.() -> Unit): Field.Builder {
    val fieldBuilder = Field.Builder().withName(name)
    fieldBuilder.block()
    selectedFields.add(fieldBuilder.build())
    return this
}

inline fun query(name: String, block: () -> Field.Builder): Query {
    val queryBuilder = Query.Builder()
    queryBuilder.withName(name)
    return queryBuilder.withRootField(block().build()).build()
}