package com.hamzasharuf.pulse.data.mappers

interface EntityMapper<Local, Remote> {

    fun mapFromLocal(local: Local): Remote
    fun mapToLocal(remote: Remote): Local

}