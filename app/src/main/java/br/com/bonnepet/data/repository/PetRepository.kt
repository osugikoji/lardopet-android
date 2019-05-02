package br.com.bonnepet.data.repository

import br.com.bonnepet.config.RetrofitConfig
import br.com.bonnepet.data.api.PetApi
import br.com.bonnepet.data.model.PetDTO
import br.com.bonnepet.util.SchedulerProvider
import br.com.bonnepet.util.data.GenderEnum
import br.com.bonnepet.util.data.PetSizeEnum
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.create

/** Repositorio de pet */
class PetRepository {

    private val petApi = RetrofitConfig.getInstance().create<PetApi>()
    private val schedulerProvider = SchedulerProvider(
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    )

    private val petList: MutableList<PetDTO> = mutableListOf(
        PetDTO(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjs6srps1gQqrzujdY5jWx77GtXhiGC5zAHgvWTjhR-GrLTQuS",
            "Tomy",
            "Akita",
            GenderEnum.MALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Timido","Destemido","Covarde")
        ),
        PetDTO(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAu3262iyGDFqBRb_NdSRgn9NGI9M7C1NNzuubwgGWhhfg1ZdoCA",
            "Greeg",
            "Shi-Tzu",
            GenderEnum.MALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUcq9j26ZP-dQQtkw9ARoowmQBq8QsLkVJh758iGvCY1LmhcEw",
            "Chris",
            "Poodle",
            GenderEnum.FEMALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://www.guidedogs.org/wp-content/uploads/2015/05/Dog-Im-Not.jpg",
            "Vask",
            "Golden",
            GenderEnum.MALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "", "Drag", "Vira-Lata", GenderEnum.MALE.name, "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy9rjMHYYBzEhMcOwD2khiOdjCaRY42d1uruRK3RJF7h6vYPhR",
            "John",
            "Husky",
            GenderEnum.FEMALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjs6srps1gQqrzujdY5jWx77GtXhiGC5zAHgvWTjhR-GrLTQuS",
            "Samu",
            "Akita",
            GenderEnum.FEMALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy9rjMHYYBzEhMcOwD2khiOdjCaRY42d1uruRK3RJF7h6vYPhR",
            "Koko",
            "Husky",
            GenderEnum.FEMALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://www.guidedogs.org/wp-content/uploads/2015/05/Dog-Im-Not.jpg",
            "Jojo",
            "Golden",
            GenderEnum.MALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy9rjMHYYBzEhMcOwD2khiOdjCaRY42d1uruRK3RJF7h6vYPhR",
            "Zuzu",
            "Husky",
            GenderEnum.FEMALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://scontent.fcpq4-1.fna.fbcdn.net/v/t1.0-1/p160x160/38679668_1849579558460524_8769734777847676928_n.jpg?_nc_cat=110&_nc_ht=scontent.fcpq4-1.fna&oh=ad2d76c3ca5018fac7b48d79f96bdde0&oe=5D7341BA",
            "Sasa",
            "Pintcher",
            GenderEnum.FEMALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "", "Vish", "Husky", GenderEnum.MALE.name, "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://www.guidedogs.org/wp-content/uploads/2015/05/Dog-Im-Not.jpg",
            "Oisa",
            "Golden",
            GenderEnum.MALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "https://www.guidedogs.org/wp-content/uploads/2015/05/Dog-Im-Not.jpg",
            "Ahyu",
            "Golden",
            GenderEnum.MALE.name,
            "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        ),
        PetDTO(
            "", "Olas", "Akita", GenderEnum.MALE.name, "2 anos e 5 meses",
            PetSizeEnum.SMALL.name,
            arrayListOf("Sneezing"),
            arrayListOf("Shy")
        )
    )

    fun getAllPets(): MutableList<PetDTO> {
        return petList
    }

}