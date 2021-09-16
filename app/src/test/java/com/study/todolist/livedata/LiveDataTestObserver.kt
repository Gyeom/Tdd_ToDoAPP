package com.study.todolist.livedata

import androidx.lifecycle.Observer

// 값이 바뀔 때마다 내부에서 하나씩 값을 들고 있는 상태를 보여주게 됨.
// 추후에 기대하는 값과 실제 값의 순서가 같은지에대해서 비교.

class LiveDataTestObserver<T> : Observer<T> {

    private val values: MutableList<T> = mutableListOf()

    override fun onChanged(t: T) {
        values.add(t)
    }

    fun assertValueSequence(sequence: List<T>): LiveDataTestObserver<T> {
        var i = 0
        val actualIterator = values.iterator()
        val expectedIterator = sequence.iterator()

        var actualNext: Boolean
        var expectedNext: Boolean

        while (true) {
            actualNext = actualIterator.hasNext()
            expectedNext = expectedIterator.hasNext()


            if (!actualNext || !expectedNext) break

            val actual: T = actualIterator.next()
            val expected: T = expectedIterator.next()

            if (actual != expected) {
                throw AssertionError("actual: ${actual}, expected: ${expected}, index: $i")
            }

            i++
        }

        if (actualNext) {
            throw AssertionError("More values received than expected ($i)")
        }
        if (expectedNext) {
            throw AssertionError("Fewer values received than expected ($i)")
        }

        return this
    }
}
