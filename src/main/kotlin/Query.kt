package com.alediaferia.graphql

import java.lang.StringBuilder

class Query {
    public var name: String? = null
    public var rootField: Field? = null

    public class Builder {
        private var name: String? = null
        private var rootField: Field? = null

        fun withRootField(name: String): Query.Builder {
            return withRootField(name) { }
        }
        fun withRootField(name: String, block: (Field.Builder) -> Unit): Query.Builder {
            val fieldBuilder = Field.Builder().withName(name)
            fieldBuilder.apply(block)
            rootField = fieldBuilder.build()
            return this
        }

        infix fun withName(name: String): Builder {
            this.name = name
            return this
        }

        fun build() =
                Query().apply {
                    name = this@Builder.name
                    rootField = this@Builder.rootField
                }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        name?.let {
            builder.append("query $it ")
        }
        builder.append("{ ")
        builder.append(rootField!!.asString())
        if (rootField!!.selectedFields.isEmpty())
            builder.append("{ }")
        builder.append(" }")

        return builder.toString()
    }
}

