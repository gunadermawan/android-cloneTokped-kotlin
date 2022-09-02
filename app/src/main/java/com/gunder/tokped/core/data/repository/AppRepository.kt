package com.gunder.tokped.core.data.repository

import com.gunder.tokped.core.data.source.local.LocalDataSource
import com.gunder.tokped.core.data.source.remote.RemoteDataSource

class AppRepository(val local:LocalDataSource, val remote:RemoteDataSource) {
}