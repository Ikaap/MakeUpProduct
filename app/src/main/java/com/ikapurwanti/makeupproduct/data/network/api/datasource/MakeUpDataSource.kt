package com.ikapurwanti.makeupproduct.data.network.api.datasource

import com.ikapurwanti.makeupproduct.data.network.api.model.MakeUpResponseItem
import com.ikapurwanti.makeupproduct.data.network.api.service.MakeUpService

interface MakeUpDataSource {
    suspend fun getMakeUp(): List<MakeUpResponseItem>
}

class MakeUpApiDataSource(private val service: MakeUpService): MakeUpDataSource {
    override suspend fun getMakeUp(): List<MakeUpResponseItem> {
        return service.getMakeUp()
    }

}