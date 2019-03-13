package com.alediaferia.graphqlforgr.dsl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class QueryDSLTests {
    @Test
    fun `it has an awesome DSL`() {
        val q = query("theQuery") {
            "allUsers"("type" to "users", "limit" to 10) {
                select("name")
                select("address") {
                    select("street")
                    select("city")
                }
            }
        }

        val expectedResult = """
            query theQuery {
                allUsers(type: "users", limit: 10) {
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
