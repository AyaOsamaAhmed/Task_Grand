package com.aya.taskgrand.core.mapper

import com.aya.taskgrand.core.usecase.BaseResponse

object AnyMapper {

    fun mapData(res: BaseResponse<Any>): BaseResponse<Any> {
        return res
    }

}