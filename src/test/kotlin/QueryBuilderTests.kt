package com.alediaferia.graphql

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class QueryBuilderTests {
    @Test
    fun `builds a query with a name`() {
        val builder = Query.Builder()
        val queryName = "Aname"

        assertEquals(Query().apply { name = queryName }.name, (builder.withName(queryName)).build().name)
    }

    @Test
    fun `builds a query with arguments`() {
        val builder = Query.Builder()
        val queryName = "theQuery"

        val resultQuery = """
            query theQuery {
                rootField(id: "a-random-Id") {
                    id
                    name
                }
            }
        """.trim().replace(Regex("\\s+"), " ").replace("\n", " ")

        val rootFieldName = "rootField"

        val query = builder
            .withName(queryName)
            .withRootField(rootFieldName) {
                it.withArg("id", "a-random-Id")
                it.selectField("id")
                it.selectField("name")
            }
            .build()

        val computedQuery = query.toString()
        println(computedQuery)
        assertEquals(resultQuery, computedQuery)
    }

    @Test
    fun `builds a query with nested selection`() {
        val builder = Query.Builder()
        val queryName = "nestedSelection"

        val resultQuery = """
            query $queryName {
                allUsers(type: "friends") {
                    name
                    address {
                        street
                        city
                    }
                }
            }
        """.trim().replace(Regex("\\s+"), " ").replace("\n", " ")

        val query = builder
            .withName(queryName)
            .withRootField("allUsers") {
                it.withArg("type", "friends")

                it.selectField("name")
                it.selectField("address") { address ->
                    address.selectField("street")
                    address.selectField("city")
                }
            }
            .build()

        val computedQuery = query.toString()
        println(computedQuery)
        assertEquals(resultQuery, computedQuery)
    }
}