package com.alediaferia.graphqlforgr.dsl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class QueryDSLTests {
    @Test
    fun `it has an awesome DSL`() {
        val q = query("theQuery") {
            "allUsers"("type" to "users", "limit" to 10) {
                select("name")
                select("address") {
                    select("city")
                    select("street"("postCode" to true))
                }
            }
        }

        val expectedResult = """
            query theQuery {
                allUsers(type: "users", limit: 10) {
                    name
                    address {
                        city
                        street(postCode: true)
                    }
                }
            }
        """.trim().replace(Regex("\\s+"), " ").replace("\n", " ")
        val computedQuery = q.toString()
        println(computedQuery)
        assertEquals(expectedResult, computedQuery)
    }

    @Test
    fun `select - raises for unsupported args`() {
        assertThrows<IllegalArgumentException> {
            query("hello") {
                "field"("type" to mapOf<String, String>())
            }
        }
    }
}
