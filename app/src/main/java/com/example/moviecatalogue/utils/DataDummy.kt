package com.example.moviecatalogue.utils

import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity

object DataDummy {

    fun getMovies(): ArrayList<MovieEntity>{
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "movie_1",
                "Tom Clancy's Without Remorse ",
                "2021",
                "1h 48m",
                "04/29/2021 (US)",
                "7.9",
                "Action, Adventure, Thriller, War",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "Stefano Sollima",
                null
        ))
        movies.add(
            MovieEntity(
            "movie_2",
                "Mortal Kombat",
                "2021",
                "1h 50m",
                "04/14/2021 (ID)",
                "7.8",
                "Action, Fantasy, Adventure",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "Simon McQuoid",
                null
        ))
        movies.add(MovieEntity(
            "movie_3",
            "Godzilla vs. Kong",
            "2020",
            "1h 53m",
            "03/24/2021 (ID)",
            "8.1",
            "Science Fiction, Action, Drama",
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "Adam Wingard",
            null
        ))
        movies.add(MovieEntity(
            "movie_4",
            "Nobody",
            "2021",
            "1h 32m",
            "03/26/2021 (US)",
            "8.4",
            "Action, Thriller, Crime, Comedy",
            "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            "Ilya Naishuller",
            null
        ))
        movies.add(
            MovieEntity(
            "movie_5",
                "22 vs. Earth",
                "2021",
                "1h 40m",
                "04/30/2021",
                "7.3",
                "Family, Animation, Comedy",
                "Set before the events of ‘Soul’, 22 refuses to go to Earth, enlisting a gang of 5 new souls in attempt of rebellion. However, 22’s subversive plot leads to a surprising revelation about the meaning of life.",
                "Kevin Nolting",
                null
        ))
        movies.add(
            MovieEntity(
                "movie_6",
                "Monster Pets: A Hotel Transylvania Short",
                "2021",
                "6m",
                "04/02/2021 (US)",
                "7.5",
                "Animation, Comedy, Fantasy",
                "Drac tries out some new monster pets to help occupy Tinkles for playtime.",
                "Jennifer Kluska",
                null
        ))
        movies.add(
            MovieEntity(
                "movie_7",
                "The Unholy",
                "2021",
                "1h 39m",
                "03/31/2021 (ID)",
                "5.6",
                "Horror",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "Evan Spiliotopoulos",
                null
        ))
        movies.add(
            MovieEntity(
                "movie_8",
                "Zack Snyder's Justice League",
                "2021",
                "4h 2m",
                "03/18/2021 (ID)",
                "8.5",
                "Action, Adventure, Fantasy, Science Fiction",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "Zack Snyder",
                null
        ))
        movies.add(
            MovieEntity(
                "movie_9",
                "Miraculous World: Shanghai – The Legend of Ladydragon",
                "2021",
                "52m",
                "04/04/2021 (FR)",
                "7.4",
                "Animation, Comedy, Family, Adventure",
                "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. ",
                "Thomas Astruc",
                null
        ))
        movies.add(
            MovieEntity(
                "movie_10",
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "2020",
                "1h 57m",
                "01/06/2021 (ID)",
                "8.4",
                "Animation, Action, Adventure, Fantasy, Drama",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira.",
                "Haruo Sotozaki",
                null
        ))
        movies.add(
            MovieEntity(
                "movie_11",
                "Raya and the Last Dragon",
                "2021",
                "1h 47m",
                "03/03/2021",
                "8.2",
                "Animation, Adventure, Fantasy, Family, Action\n",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "Carlos López Estrada",
                null
        ))
        movies.add(
            MovieEntity(
                "movie_12",
                "Thunder Force",
                "2021",
                "1h 47m",
                "04/09/2021 (US)",
                "5.8",
                "Action, Adventure, Comedy, Fantasy",
                "In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.",
                "Ben Falcone",
                null
        ))
        movies.add(
            MovieEntity(
                "movie_13",
                "Vanquish",
                "2021",
                "1h 36m",
                "04/16/2021 (US)",
                "6.3",
                "Action, Crime, Thriller",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "George Gallo",
                null
        ))
        movies.add(
            MovieEntity(
                "movie_14",
                "Willy's Wonderland",
                "2021",
                "1h 28m",
                "03/17/2021 (ID)",
                "6.5",
                "Action, Horror, Comedy",
                "When his car breaks down, a quiet loner agrees to clean an abandoned family fun center in exchange for repairs. He soon finds himself waging war against possessed animatronic mascots while trapped inside Willy's Wonderland.",
                "Kevin Lewis",
                null
            ))

        return movies
    }

    fun getTvShows(): ArrayList<TvShowEntity>{

        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                "tv_show_1",
                "The Falcon and the Winter Soldier",
                "2021",
                "50m",
                "7.9",
                "Sci-Fi & Fantasy, Action & Adventure, Drama, War & Politics",
                "Honor the shield",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "Malcolm Spellman",
                null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_2",
                "The Good Doctor",
                "2017",
                "43m",
                "8.6",
                "Drama",
                "His mind is a mystery, his methods are a miracle.",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "David Shore",
                null
        ))
        tvShows.add(TvShowEntity(
            "tv_show_3",
            "The flash",
            "2014",
            "44m",
            "7.7",
            "Drama, Sci-Fi & Fantasy",
            "The fastest man alive.",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. ",
            "Greg berlanti",
            null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_4",
                "Invincible",
                "2021",
                "45m",
                "8.9",
                "Animation, Action & Adventure, Drama, Sci-Fi & Fantasy",
                "-",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "unknown",
                null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_5",
                "Jupiter's Legacy",
                "2021",
                "45m",
                "7.4",
                "Sci-Fi & Fantasy, Action & Adventure, Drama, Mystery",
                "No legacy lives forever.",
                "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
                "Steven S. DeKnight",
                null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_6",
                "Grey's Anatomy",
                "2005",
                "43m",
                "7.2",
                "Drama",
                "The life you save may be your own.",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Shonda Rhimes",
                null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_7",
                "Luis Miguel: The Series",
                "2018",
                "50m",
                "8.1",
                "Drama",
                "-",
                "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
                "unknown",
                null


        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_8",
                "Lucifer",
                "2015",
                "49m",
                "8.3",
                "Crime, Sci-Fi & Fantasy",
                "It's good to be bad.",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "Tom Kapinos",
                null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_9",
                "Riverdale",
                "2017",
                "45m",
                "8.7",
                "Mystery, Drama, Crime",
                "Small town. Big secrets.",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "Roberto Aguirre-Sacasa",
                null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_10",
                "The Bad Batch",
                "2021",
                "55m",
                "9.0",
                "Sci-Fi & Fantasy, Action & Adventure, Animation",
                "-",
                "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                "Dave Filoni",
                null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_11",
                "Who Killed Sara? ",
                "2021",
                "40m",
                "9.0",
                "Drama, Crime, Mystery",
                "_",
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                "José Ignacio Valenzuela",
                null

        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_12",
                "Van Helsing",
                "2016",
                "42m",
                "6.9",
                "Mystery, Sci-Fi & Fantasy, Action & Adventure",
                "-",
                "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost.",
                "Neil LaBute",
                null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_13",
                "Love, Death & Robots",
                "2019",
                "20m",
                "8.2",
                "Animation, Sci-Fi & Fantasy",
                "-",
                "Terrifying creatures, wicked surprises and dark comedy converge in this NSFW anthology of animated stories presented by Tim Miller and David Fincher.",
                "David fincher",
                null
        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_14",
                "Selena: The Series",
                "2020",
                "40m",
                "8.2",
                "Drama",
                "Every legend begins with a dream.",
                "As Mexican-American Tejano singer Selena comes of age and realizes her dreams, she and her family make tough choices to hold on to love and music.",
                "Moisés Zamora",
                null

        ))
        tvShows.add(
            TvShowEntity(
                "tv_show_15",
                "Fear the Walking Dead",
                "2015",
                "43m",
                "7.6",
                "Action & Adventure, Drama",
                "Every decision is life or death.",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "Dave erickson",
                null
        ))

        return tvShows
    }

}