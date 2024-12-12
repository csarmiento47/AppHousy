package com.inforcap.apphousy.data

import com.inforcap.apphousy.model.Mansion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext


class MansionRepository {

    private var listMansiones = listOf<Mansion>()

    suspend fun getMansionList(): Flow<List<Mansion>> {

        val response = withContext(Dispatchers.IO) {
            for (i in 0..9) {
                delay(100L)
            }

            listOf(
                Mansion(
                    id = 1,
                    name = "Springfield",
                    price = 450000000,
                    photo = "https://resizer.glanacion.com/resizer/v2/la-casa-de-los-simpson-a-traves-de-los-ocho-4WEY7B6OZNFQDNQTVPTINLNSPA.jpg?auth=e51a737e6709f5427cdda829dc889d6dc7185faefa43b1251ef5cb17d349c44f&width=768&quality=70&smart=false",
                    size = 205,
                    renovation = true,
                    credit = true,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    cause = "Los dueños se van del país"
                ),
                Mansion(
                    id = 2,
                    name = "Langley Falls",
                    price = 430000000,
                    photo = "https://static.wikia.nocookie.net/americandad/images/8/89/Smithhouse.png/revision/latest?cb=20100823115246",
                    size = 205,
                    renovation = true,
                    credit = true,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    cause = "Los dueños se van del país"
                ),
                Mansion(
                    id = 3,
                    name = "Freedonia",
                    price = 2000000000,
                    photo = "https://i.pinimg.com/736x/dd/69/d0/dd69d0ab754e28f4cac31603011eb734.jpg",
                    size = 800,
                    renovation = true,
                    credit = true,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    cause = "Los dueños se van del país"
                ),
                Mansion(
                    id = 4,
                    name = "Hollywood",
                    price = 2100000000,
                    photo = "https://preview.redd.it/why-does-nobody-talk-about-how-cool-bojacks-house-is-this-v0-srfk9gjjqiub1.jpg?width=640&crop=smart&auto=webp&s=c40547d4673dd0838290aca37dcce52a1b712ac7",
                    size = 800,
                    renovation = true,
                    credit = true,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    cause = "Los dueños se van del país"
                ),
                Mansion(
                    id = 5,
                    name = "Chicureo",
                    price = 150000000,
                    photo = "https://www.casaschicureo.cl/images/slides/slider7.jpg",
                    size = 800,
                    renovation = true,
                    credit = true,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                    cause = "Los dueños se van del país"
                ),
            )
        }
        listMansiones = response

        return flowOf(response)
    }

    suspend fun getMansionById(id: Int) : StateFlow<Mansion?> {
        val data: MutableStateFlow<Mansion?> = MutableStateFlow(null)
        getMansionList().collect() {
                list -> val mansion = list.find {
            it.id == id
        }
            data.value = mansion
        }
        return data
    }


}