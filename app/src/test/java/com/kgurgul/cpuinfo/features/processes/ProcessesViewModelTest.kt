/*
 * Copyright 2017 KG Soft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kgurgul.cpuinfo.features.processes

import com.kgurgul.cpuinfo.utils.DispatchersProvider
import com.kgurgul.cpuinfo.utils.Prefs
import com.kgurgul.cpuinfo.utils.RxImmediateSchedulerRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.robolectric.RobolectricTestRunner

/**
 * Tests for [ProcessesViewModel]
 *
 * @author kgurgul
 */
@RunWith(RobolectricTestRunner::class)
class ProcessesViewModelTest {

    @Suppress("unused")
    @get:Rule
    val rxSchedulersRule = RxImmediateSchedulerRule()

    private lateinit var dispatchersProvider: DispatchersProvider

    @Before
    fun setup() {
        dispatchersProvider = mock {
            on { io } doReturn Dispatchers.Main
            on { main } doReturn Dispatchers.Main
        }
    }

    @Test
    fun startStopRefreshing() {
        /* Given */
        val generatedProcessItems = getProcessItemsList()
        val prefs = mock<Prefs> {
            onGeneric { get(anyString(), anyBoolean()) } doReturn true
        }
        val psProvider = mock<PsProvider> {
            on { getPsList() } doReturn Single.just(generatedProcessItems)
        }
        val viewModel = spy(ProcessesViewModel(dispatchersProvider, prefs, psProvider))
        doReturn(Flowable.just(1L)).whenever(viewModel).getRefreshingInvoker()

        /* When */
        viewModel.startProcessRefreshing()
        viewModel.stopProcessRefreshing()

        /* Then */
        assertEquals(generatedProcessItems.size, viewModel.processList.size)
        assertEquals(generatedProcessItems[0], viewModel.processList[0])
        assertEquals(generatedProcessItems[1], viewModel.processList[1])
    }

    @Test
    fun refreshingError() {
        /* Given */
        val prefs = mock<Prefs> {
            onGeneric { get(anyString(), anyBoolean()) } doReturn true
        }
        val psProvider = mock<PsProvider>()
        whenever(psProvider.getPsList()).thenReturn(Single.error(NullPointerException()))
        val viewModel = spy(ProcessesViewModel(dispatchersProvider, prefs, psProvider))
        doReturn(Flowable.just(1L)).whenever(viewModel).getRefreshingInvoker()

        /* When */
        viewModel.startProcessRefreshing()
        viewModel.stopProcessRefreshing()

        /* Then */
        assertEquals(0, viewModel.processList.size)
    }

    @Test
    fun sortingAscAndDesc() {
        /* Given */
        val generatedProcessItems = getProcessItemsList()
        val prefs = mock<Prefs> {
            onGeneric { get(anyString(), anyBoolean()) } doReturn true
        }
        val psProvider = mock<PsProvider> {
            on { getPsList() } doReturn Single.just(generatedProcessItems)
        }
        val viewModel = spy(ProcessesViewModel(dispatchersProvider, prefs, psProvider))
        doReturn(Flowable.just(1L)).whenever(viewModel).getRefreshingInvoker()

        /* When */
        viewModel.startProcessRefreshing()
        viewModel.stopProcessRefreshing()
        val sortedListAsc = viewModel.getProcessSortedList(true)
        val sortedListDesc = viewModel.getProcessSortedList(false)

        /* Then */
        assertEquals(generatedProcessItems.size, viewModel.processList.size)
        assertEquals(generatedProcessItems.size, sortedListAsc.size)
        assertEquals(generatedProcessItems.size, sortedListDesc.size)
        assertEquals(generatedProcessItems[0], sortedListAsc[0])
        assertEquals(generatedProcessItems[1], sortedListAsc[1])
        assertEquals(generatedProcessItems[1], sortedListDesc[0])
        assertEquals(generatedProcessItems[0], sortedListDesc[1])
    }

    /**
     * Return generated [List] of [ProcessItem]
     */
    private fun getProcessItemsList(): List<ProcessItem> {
        val processItems = ArrayList<ProcessItem>()
        val p1 = ProcessItem("/init", "1", "0", "0", "root", "444", "724")
        val p2 = ProcessItem("kthreadd", "2", "1", "2", "root", "0", "0")
        processItems.add(p1)
        processItems.add(p2)
        return processItems
    }
}