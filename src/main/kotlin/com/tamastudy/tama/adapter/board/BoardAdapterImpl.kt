package com.tamastudy.tama.adapter.board

import com.tamastudy.tama.dto.board.BoardDto
import com.tamastudy.tama.dto.board.CreateBoardRequest
import com.tamastudy.tama.entity.Board
import com.tamastudy.tama.entity.BoardCategory
import com.tamastudy.tama.entity.User
import com.tamastudy.tama.entity.convertBoardCategory
import com.tamastudy.tama.service.board.BoardCategoryService
import com.tamastudy.tama.service.board.BoardService
import com.tamastudy.tama.service.user.UserService
import org.springframework.stereotype.Component

@Component
class BoardAdapterImpl(
        private val userService: UserService,
        private val boardCategoryService: BoardCategoryService,
        private val boardService: BoardService
) : BoardAdapter {
    override fun createBoard(user: User, createBoardRequest: CreateBoardRequest): BoardDto {
        val category = boardCategoryService.findById(createBoardRequest.categoryId).let {
            BoardCategory().convertBoardCategory(it)
        }
        val newBoard = Board().apply {
            this.user = user
            this.title = createBoardRequest.title
            this.description = createBoardRequest.description
            this.category = category
        }
        return boardService.createBoard(newBoard)
    }
}