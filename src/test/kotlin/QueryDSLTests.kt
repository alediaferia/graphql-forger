package com.alediaferia.graphql

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class QueryDSLTests {
    @Test
    fun `it has an awesome DSL`() {
        val q = query("theQuery") {
            "allUsers" {
                select("name")
                select("address") {
                    select("street")
                    select("city")
                }
            }
        }

        val expectedResult = """
            query theQuery {
                allUsers {
                    name
                    address {
                        street
                        city
                    }
                }
            }
        """.trim().replace(Regex("\\s+"), " ").replace("\n", " ")
        val computedQuery = q.toString()
        println(computedQuery)
        assertEquals(expectedResult, computedQuery)
    }
}