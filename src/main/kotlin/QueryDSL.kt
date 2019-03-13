package com.alediaferia.graphql

operator fun String.invoke(block: Field.Builder.() -> Unit): Field.Builder {
    val builder = Field.Builder()
    builder.block()
    return builder.withName(this)
}

inline operator fun <reified T> String.invoke(vararg args: Pair<String, T>, block: Field.Builder.() -> Unit): Field.Builder {
    val builder = Field.Builder().withName(this)
    for (arg in args) {
        when {
            arg.second is String -> builder.withArg(arg.first, arg.second as String)
            arg.second is Int -> builder.withArg(arg.first, arg.second as Int)
        }
    }
    builder.block()
    return builder
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