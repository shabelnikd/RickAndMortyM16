package com.shabelnikd.character.data.mapper

import com.shabelnikd.character.data.model.CharacterDto
import com.shabelnikd.character.data.model.CharacterLocationDto
import com.shabelnikd.character.data.model.CharacterResponseDto
import com.shabelnikd.character.data.model.InfoDto
import com.shabelnikd.character.data.model.OriginDto
import com.shabelnikd.character.domain.model.Character
import com.shabelnikd.character.domain.model.CharacterLocation
import com.shabelnikd.character.domain.model.CharacterResponse
import com.shabelnikd.character.domain.model.Info
import com.shabelnikd.character.domain.model.Origin

fun CharacterResponseDto.toDomain(): CharacterResponse {
    return CharacterResponse(
        info = this.info?.toDomain() ?: Info(count = -1, pages = -1, next = "", prev = ""),
        results = this.results?.map { it.toDomain() } ?: emptyList()
    )
}

fun CharacterDto.toDomain(): Character {
    return Character(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        status = this.status.orEmpty(),
        species = this.species.orEmpty(),
        type = this.type.orEmpty(),
        gender = this.gender.orEmpty(),
        origin = this.origin?.toDomain() ?: Origin(name = "", url = ""),
        characterLocation = this.characterLocation?.toDomain() ?: CharacterLocation(
            name = "",
            url = ""
        ),
        image = this.image.orEmpty(),
        episode = this.episode.orEmpty(),
        url = this.url.orEmpty(),
        created = this.created.orEmpty()
    )
}

fun OriginDto.toDomain(): Origin {
    return Origin(
        name = this.name.orEmpty(),
        url = this.url.orEmpty()
    )
}

fun CharacterLocationDto.toDomain(): CharacterLocation {
    return CharacterLocation(
        name = name.orEmpty(),
        url = url.orEmpty()
    )
}


fun InfoDto.toDomain(): Info {
    return Info(
        count = this.count ?: 0,
        pages = this.pages ?: 0,
        next = this.next ?: "",
        prev = this.prev ?: ""
    )
}
