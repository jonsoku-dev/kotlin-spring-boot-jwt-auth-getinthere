package com.tamastudy.tama.dto.board

import com.tamastudy.tama.entity.Board
import com.tamastudy.tama.entity.BoardCategory
import com.tamastudy.tama.entity.User

data class BoardDto(
        var id: Long? = null,
        var title: String? = null,
        var description: String? = null,
        var categoryId: Long? = null
)

fun BoardDto?.toBoard(user: User, category: BoardCategory): Board {
    return Board(
            id = this?.id,
            title = this?.title,
            description = this?.description,
            user = user,
            category = category
    )
}