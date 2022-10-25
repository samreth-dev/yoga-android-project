package com.example.yoga

import java.io.Serializable

data class Yoga(val title: String, val details: String, val video: List<Video>) : Serializable