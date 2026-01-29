package com.ae.alice.core.network.client

/**
 * Centralized API endpoints for the Alice app.
 * All endpoint paths are defined here to avoid hardcoding.
 */
object ApiEndpoints {
    
    // Base paths
    private const val API_VERSION = "v1"
    private const val BASE_PATH = "/api/$API_VERSION"
    
    // Car Brands endpoints
    object Brands {
        const val ALL = "$BASE_PATH/brands"
        fun byId(id: String) = "$BASE_PATH/brands/$id"
        fun models(brandId: String) = "$BASE_PATH/brands/$brandId/models"
    }
    
    // Car Models endpoints
    object Models {
        const val ALL = "$BASE_PATH/models"
        fun byId(id: String) = "$BASE_PATH/models/$id"
        fun search(query: String) = "$BASE_PATH/models/search?q=$query"
    }
    
    // Search endpoints
    object Search {
        fun brands(query: String) = "$BASE_PATH/search/brands?q=$query"
        fun models(query: String) = "$BASE_PATH/search/models?q=$query"
        fun all(query: String) = "$BASE_PATH/search?q=$query"
    }
}
