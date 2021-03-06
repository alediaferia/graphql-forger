package com.alediaferia.graphqlforgr

import java.lang.StringBuilder

class Field {
    var name: String? = null
    var args: List<Arg> = listOf()
    var selectedFields: List<Field> = listOf()

    class Builder {
        var name : String? = null
        var args: MutableList<Arg> = mutableListOf()
        var selectedFields : MutableList<Field> = mutableListOf()

        fun withName(name: String): Builder {
            this.name = name
            return this
        }

        fun withArg(name: String, value: String): Builder {
            args.add(Arg().apply {
                this.name = name
                this.value = StringValue(value)
            })

            return this
        }

        fun withArg(name: String, value: Int): Builder {
            args.add(Arg().apply {
                this.name = name
                this.value = IntValue(value)
            })

            return this
        }

        fun withArg(name: String, value: Float): Builder {
            args.add(Arg().apply {
                this.name = name
                this.value = FloatValue(value)
            })

            return this
        }

        fun withArg(name: String, value: Boolean): Builder {
            args.add(Arg().apply {
                this.name = name
                this.value = BooleanValue(value)
            })

            return this
        }

        fun selectField(name: String): Builder {
            selectedFields.add(Field().apply { this.name = name })
            return this
        }

        fun selectField(name: String, block: (Builder) -> Unit): Builder {
            val fieldBuilder = Field.Builder().withName(name)
            fieldBuilder.apply(block)
            selectedFields.add(fieldBuilder.build())
            return this
        }

        fun build() =
            Field().apply {
                name = this@Builder.name
                args = this@Builder.args
                selectedFields = this@Builder.selectedFields
            }
    }

    fun asString(): String {
        val builder = StringBuilder()
        builder.append(this.name)
        if (args.isNotEmpty()) {
            builder.append("(")
            for (i in args.indices) {
                val arg = args[i]
                builder.append("${arg.name}: ${arg.value!!.asString()}")
                if (i < args.size - 1)
                    builder.append(", ")
            }
            builder.append(")")
        }

        if (selectedFields.isNotEmpty()) {
            builder.append(" { ")
            for (i in selectedFields.indices) {
                val field = selectedFields[i]
                builder.append(field.asString())
                if (i < selectedFields.size - 1)
                    builder.append(" ")
            }
            builder.append(" }")
        }

        return builder.toString()
    }
}
