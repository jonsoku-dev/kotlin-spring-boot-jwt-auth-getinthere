package com.tamastudy.tama.service.board

import com.tamastudy.tama.dto.board.*
import com.tamastudy.tama.entity.Board
import com.tamastudy.tama.entity.BoardCategory
import com.tamastudy.tama.entity.toBoardCategoryDto
import com.tamastudy.tama.entity.toBoardDto
import com.tamastudy.tama.repository.board.BoardCategoryRepository
import com.tamastudy.tama.repository.board.BoardRepository
import com.tamastudy.tama.repository.user.UserRepository
import javassist.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.function.Supplier

@Service
class BoardServiceImpl(
        private val userRepository: UserRepository,
        private val boardRepository: BoardRepository,
        private val boardCategoryRepository: BoardCategoryRepository
) : BoardService {
    override fun createBoardCategory(createBoardCategoryRequest: CreateBoardCategoryRequest): BoardCategoryDto {
        val newBoardCategory = BoardCategory().apply {
            this.name = createBoardCategoryRequest.name
        }
        return boardCategoryRepository.save(newBoardCategory).let {
            it.toBoardCategoryDto()
        }
    }

    override fun createBoard(userId: Long, createBoardRequest: CreateBoardRequest): BoardDto {
        try {
            val boardCategory = boardCategoryRepository.findByIdOrNull(createBoardRequest.categoryId)
//                ?: throw NoSuchElementException("카테고리 아이디를 확인해주세요. ")

            val user = userRepository.findByIdOrNull(userId)
//                ?: throw NoSuchElementException("유저 아이디를 확인해주세요. ")

            val newBoard = Board().apply {
                this.title = createBoardRequest.title
                this.description = createBoardRequest.description
                this.category = boardCategory
                this.user = user
            }

            return boardRepository.save(newBoard).let {
                it.toBoardDto()
            }
        } catch (e: Exception) {
            e.stackTrace
            throw Exception(e)
        }
    }

    override fun findAllWithPage(condition: BoardPagingCondition?, pageable: Pageable?): Page<BoardPagingDto> {
        return boardRepository.searchPageSimple(condition, pageable)
    }

    override fun findAllWithComplexPage(condition: BoardPagingCondition?, pageable: Pageable?): Page<BoardPagingDto> {
        return boardRepository.searchPageComplex(condition, pageable)
    }

    override fun findById(id: Long): BoardDto {
        return boardRepository.findByIdOrNull(id)?.let {
            it.toBoardDto()
        } ?: run {
            throw NotFoundException("$id 에 해당하는 게시물을 찾을 수 없습니다.")
        }
    }

    override fun updateBoard(userId: Long, boardId: Long, updateBoardRequest: UpdateBoardRequest): BoardDto {
        val user = userRepository.findByIdOrNull(userId) ?: throw NoSuchElementException("유저 아이디를 확인해주세요. ")
        val category = boardCategoryRepository.findByIdOrNull(updateBoardRequest.categoryId)
                ?: throw NoSuchElementException("카테고리 아이디를 확인해주세요. ")
        val updatedBoard = this.findById(boardId).toBoard(user, category).apply {
            this.title = updateBoardRequest.title
            this.description = updateBoardRequest.description
            this.category = category
        }

        return boardRepository.save(updatedBoard).toBoardDto()
    }

    override fun deleteById(userId: Long, boardId: Long) {
        try {
            val board = boardRepository.findOneWithUserById(boardId)
            if (board?.user?.id != userId)
                boardRepository.deleteById(boardId)
        } catch (e: Exception) {
            e.stackTrace
            throw Exception(e)
        }
    }
}