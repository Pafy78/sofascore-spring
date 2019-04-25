package com.baudoin.sofascore.repository

import com.baudoin.sofascore.entity.Match
import org.springframework.data.repository.CrudRepository

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

interface MatchRepository : CrudRepository<Match, Integer> {
}