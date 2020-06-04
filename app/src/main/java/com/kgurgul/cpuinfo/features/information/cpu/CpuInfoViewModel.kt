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

package com.kgurgul.cpuinfo.features.information.cpu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kgurgul.cpuinfo.domain.model.CpuData
import com.kgurgul.cpuinfo.domain.observable.ObservableCpuData
import com.kgurgul.cpuinfo.domain.observe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for CPU information
 *
 * @author kgurgul
 */
class CpuInfoViewModel @Inject constructor(
        observableCpuData: ObservableCpuData
) : ViewModel() {

    private val cpuData: Flow<CpuData> = observableCpuData.observe()

    val viewState = cpuData
            .distinctUntilChanged()
            .map {
                Timber.d("CpuData: $it")
                CpuInfoViewState(it)
            }
            .asLiveData(viewModelScope.coroutineContext)
}
