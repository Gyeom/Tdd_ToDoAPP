package com.study.todolist.viewmodel.todo

import com.study.todolist.data.entity.ToDoEntity
import com.study.todolist.presentation.detail.DetailMode
import com.study.todolist.presentation.detail.DetailViewModel
import com.study.todolist.presentation.detail.ToDoDetailState
import com.study.todolist.presentation.list.ListViewModel
import com.study.todolist.presentation.list.ToDoListState
import com.study.todolist.viewmodel.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class DetailViewModelForWriteTest : ViewModelTest() {

    private val detailViewModel: DetailViewModel by inject { parametersOf(DetailMode.WRITE, id) }
    private val listViewModel: ListViewModel by inject()

    private val id = 0L

    private val todo = ToDoEntity(
        id,
        title = "title 1",
        description = "description 1",
        hasCompleted = false
    )

    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()

        detailViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Write
            )
        )
    }

    @Test
    fun `test insert todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
        val listTestObservable = listViewModel.toDoListLiveData.test()

        detailViewModel.writeToDo(
            title = todo.title,
            description = todo.description
        )

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )

        assert(detailViewModel.detailMode == DetailMode.DETAIL)
        assert(detailViewModel.id == id)

        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Suceess(listOf(todo))
            )
        )
    }

}
