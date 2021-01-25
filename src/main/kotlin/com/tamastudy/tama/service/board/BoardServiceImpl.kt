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
        private val repository: BoardRepository
) : BoardService {
    override fun findAllWithPage(condition: BoardPagingCondition?, pageable: Pageable?): Page<BoardPagingDto> {
        return repository.searchPageSimple(condition, pageable)
    }

    override fun findAllWithComplexPage(condition: BoardPagingCondition?, pageable: Pageable?): Page<BoardPagingDto> {
        return repository.searchPageComplex(condition, pageable)
    }

    override fun findById(id: Long): BoardDto {
        TODO("Not yet implemented")
    }

    override fun createBoard(board: Board): BoardDto {
        TODO("Not yet implemented")
    }

    override fun updateBoard(board: Board): BoardDto {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }
}