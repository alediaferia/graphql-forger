package com.alediaferia.graphqlforgr.dsl

import com.alediaferia.graphqlforgr.Field
import com.alediaferia.graphqlforgr.Query

operator fun String.invoke(block: Field.Builder.() -> Unit): Field.Builder {
    val builder = Field.Builder()
    builder.block()
    return builder.withName(this)
}

operator fun <T> String.invoke(vararg args: Pair<String, T>, block: (Field.Builder.() -> Unit)? = null): Field.Builder {
    val builder = Field.Builder().withName(this)
    for (arg in args)
        when {
            arg.second is String -> builder.withArg(arg.first, arg.second as String)
            arg.second is Int -> builder.withArg(arg.first, arg.second as Int)
            arg.second is Float -> builder.withArg(arg.first, arg.second as Float)
            arg.second is Boolean -> builder.withArg(arg.first, arg.second as Boolean)
            else -> throw IllegalArgumentException("Unsupported argument type for ${arg.first}: ${arg.second}")
        }

    if (block != null)
        builder.block()
    return builder
}

fun Field.Builder.select(name: String) = selectField(name)

inline fun Field.Builder.select(name: String, block: Field.Builder.() -> Unit): Field.Builder {
    val fieldBuilder = Field.Builder().withName(name)
    fieldBuilder.block()
    selectedFields.add(fieldBuilder.build())
    return this
}

fun Field.Builder.select(fieldBuilder: Field.Builder): Field.Builder {
    selectedFields.add(fieldBuilder.build())
    return this
}

inline fun query(name: String, block: () -> Field.Builder): Query {
    val queryBuilder = Query.Builder()
    queryBuilder.withName(name)
    return queryBuilder.withRootField(block().build()).build()
}