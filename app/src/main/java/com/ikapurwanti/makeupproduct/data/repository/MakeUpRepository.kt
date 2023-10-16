package com.ikapurwanti.makeupproduct.data.repository

import com.ikapurwanti.makeupproduct.data.network.api.datasource.MakeUpDataSource
import com.ikapurwanti.makeupproduct.model.MakeUpViewParam
import com.ikapurwanti.makeupproduct.model.toMakeUpViewParam
import com.ikapurwanti.makeupproduct.model.toMakeUpViewParams
import com.ikapurwanti.retrofitexample.utils.ResultWrapper
import com.ikapurwanti.retrofitexample.utils.proceedFlow
import kotlinx.coroutines.flow.Flow

interface MakeUpRepository {
    suspend fun getMakeUp(): Flow<ResultWrapper<List<MakeUpViewParam>>>
}

class MakeUpRepositoryImpl(private val dataSource: MakeUpDataSource) : MakeUpRepository {
    override suspend fun getMakeUp(): Flow<ResultWrapper<List<MakeUpViewParam>>> {
        return proceedFlow { dataSource.getMakeUp().toMakeUpViewParams() }
    }
}

