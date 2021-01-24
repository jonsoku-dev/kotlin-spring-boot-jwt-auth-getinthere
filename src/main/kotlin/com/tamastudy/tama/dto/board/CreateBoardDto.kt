package com.tamastudy.tama.dto.board

data class CreateBoardRequest(
        var title: String? = null,
        var description: String? = null,
        var categoryId: Long? = null
)