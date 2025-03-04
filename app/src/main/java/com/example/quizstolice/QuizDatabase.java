package com.example.quizstolice;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Result.class, Question.class}, version = 1, exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase {

    private static QuizDatabase instance;

    public abstract QuestionDao questionDao();
    public abstract ResultDao resultDao();

    public static synchronized QuizDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, "quiz_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback) // Dodanie danych początkowych
                    .build();
        }
        return instance;
    }

    private static final Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Thread(() -> {
                QuizDatabase database = instance;
                if (database != null) {
                    QuestionDao questionDao = database.questionDao();

                    // Dodanie pytań dla Europy
                    questionDao.insert(new Question("Albania", "Tirana", "Europa", "albania"));
                    questionDao.insert(new Question("Andora", "Andora", "Europa", "andorra"));
                    questionDao.insert(new Question("Austria", "Wiedeń", "Europa", "austria"));
                    questionDao.insert(new Question("Belgia", "Bruksela", "Europa", "belgium"));
                    questionDao.insert(new Question("Białoruś", "Mińsk", "Europa", "belarus"));
                    questionDao.insert(new Question("Bośnia i Hercegowina", "Sarajewo", "Europa", "bosnia"));
                    questionDao.insert(new Question("Bułgaria", "Sofia", "Europa", "bulgaria"));
                    questionDao.insert(new Question("Chorwacja", "Zagrzeb", "Europa", "croatia"));
                    questionDao.insert(new Question("Czarnogóra", "Podgorica", "Europa", "montenegro"));
                    questionDao.insert(new Question("Cypr", "Nikozja", "Europa", "cyprus"));
                    questionDao.insert(new Question("Czechy", "Praga", "Europa", "czech_republic"));
                    questionDao.insert(new Question("Dania", "Kopenhaga", "Europa", "denmark"));
                    questionDao.insert(new Question("Estonia", "Tallin", "Europa", "estonia"));
                    questionDao.insert(new Question("Finlandia", "Helsinki", "Europa", "finland"));
                    questionDao.insert(new Question("Francja", "Paryż", "Europa", "france"));
                    questionDao.insert(new Question("Grecja", "Ateny", "Europa", "greece"));
                    questionDao.insert(new Question("Hiszpania", "Madryt", "Europa", "spain"));
                    questionDao.insert(new Question("Holandia", "Amsterdam", "Europa", "netherlands"));
                    questionDao.insert(new Question("Irlandia", "Dublin", "Europa", "ireland"));
                    questionDao.insert(new Question("Islandia", "Reykjavik", "Europa", "iceland"));
                    questionDao.insert(new Question("Kazachstan", "Astana", "Europa", "kazakhstan"));
                    questionDao.insert(new Question("Liechtenstein", "Vaduz", "Europa", "liechtenstein"));
                    questionDao.insert(new Question("Litwa", "Wilno", "Europa", "lithuania"));
                    questionDao.insert(new Question("Luksemburg", "Luksemburg", "Europa", "luxembourg"));
                    questionDao.insert(new Question("Łotwa", "Ryga", "Europa", "latvia"));
                    questionDao.insert(new Question("Macedonia Północna", "Skopje", "Europa", "north_macedonia"));
                    questionDao.insert(new Question("Malta", "Valletta", "Europa", "malta"));
                    questionDao.insert(new Question("Mołdawia", "Kiszyniów", "Europa", "moldova"));
                    questionDao.insert(new Question("Monako", "Monako", "Europa", "monaco"));
                    questionDao.insert(new Question("Niemcy", "Berlin", "Europa", "germany"));
                    questionDao.insert(new Question("Norwegia", "Oslo", "Europa", "norway"));
                    questionDao.insert(new Question("Polska", "Warszawa", "Europa", "poland"));
                    questionDao.insert(new Question("Portugalia", "Lizbona", "Europa", "portugal"));
                    questionDao.insert(new Question("Rosja", "Moskwa", "Europa", "russia"));
                    questionDao.insert(new Question("Rumunia", "Bukareszt", "Europa", "romania"));
                    questionDao.insert(new Question("San Marino", "San Marino", "Europa", "san_marino"));
                    questionDao.insert(new Question("Serbia", "Belgrad", "Europa", "serbia"));
                    questionDao.insert(new Question("Słowacja", "Bratysława", "Europa", "slovakia"));
                    questionDao.insert(new Question("Słowenia", "Lublana", "Europa", "slovenia"));
                    questionDao.insert(new Question("Szwajcaria", "Berno", "Europa", "switzerland"));
                    questionDao.insert(new Question("Szwecja", "Sztokholm", "Europa", "sweden"));
                    questionDao.insert(new Question("Turcja", "Ankara", "Europa", "turkey"));
                    questionDao.insert(new Question("Ukraina", "Kijów", "Europa", "ukraine"));
                    questionDao.insert(new Question("Watykan", "Watykan", "Europa", "vatican"));
                    questionDao.insert(new Question("Węgry", "Budapeszt", "Europa", "hungary"));
                    questionDao.insert(new Question("Wielka Brytania", "Londyn", "Europa", "united_kingdom"));
                    questionDao.insert(new Question("Włochy", "Rzym", "Europa", "italy"));

                    // Dodanie pytań dla Azji
                    questionDao.insert(new Question("Afganistan", "Kabul", "Azja", "afghanistan"));
                    questionDao.insert(new Question("Arabia Saudyjska", "Rijad", "Azja", "saudi_arabia"));
                    questionDao.insert(new Question("Armenia", "Erywań", "Azja", "armenia"));
                    questionDao.insert(new Question("Azerbejdżan", "Baku", "Azja", "azerbaijan"));
                    questionDao.insert(new Question("Bahrajn", "Manama", "Azja", "bahrain"));
                    questionDao.insert(new Question("Bangladesz", "Dhaka", "Azja", "bangladesh"));
                    questionDao.insert(new Question("Bhutan", "Thimphu", "Azja", "bhutan"));
                    questionDao.insert(new Question("Brunei", "Bandar Seri Begawan", "Azja", "brunei"));
                    questionDao.insert(new Question("Chiny", "Pekin", "Azja", "china"));
                    questionDao.insert(new Question("Filipiny", "Manila", "Azja", "philippines"));
                    questionDao.insert(new Question("Gruzja", "Tbilisi", "Azja", "georgia"));
                    questionDao.insert(new Question("Indie", "Nowe Delhi", "Azja", "india"));
                    questionDao.insert(new Question("Indonezja", "Dżakarta", "Azja", "indonesia"));
                    questionDao.insert(new Question("Irak", "Bagdad", "Azja", "iraq"));
                    questionDao.insert(new Question("Iran", "Teheran", "Azja", "iran"));
                    questionDao.insert(new Question("Izrael", "Jerozolima", "Azja", "israel"));
                    questionDao.insert(new Question("Japonia", "Tokio", "Azja", "japan"));
                    questionDao.insert(new Question("Jemen", "Sana", "Azja", "yemen"));
                    questionDao.insert(new Question("Jordania", "Amman", "Azja", "jordan"));
                    questionDao.insert(new Question("Kambodża", "Phnom Penh", "Azja", "cambodia"));
                    questionDao.insert(new Question("Katar", "Doha", "Azja", "qatar"));
                    questionDao.insert(new Question("Kazachstan", "Astana", "Azja", "kazakhstan"));
                    questionDao.insert(new Question("Kirgistan", "Biszkek", "Azja", "kyrgyzstan"));
                    questionDao.insert(new Question("Korea Południowa", "Seul", "Azja", "south_korea"));
                    questionDao.insert(new Question("Korea Północna", "Pjongjang", "Azja", "north_korea"));
                    questionDao.insert(new Question("Kuwejt", "Kuwejt", "Azja", "kuwait"));
                    questionDao.insert(new Question("Laos", "Wientian", "Azja", "laos"));
                    questionDao.insert(new Question("Liban", "Bejrut", "Azja", "lebanon"));
                    questionDao.insert(new Question("Malediwy", "Male", "Azja", "maldives"));
                    questionDao.insert(new Question("Malezja", "Kuala Lumpur", "Azja", "malaysia"));
                    questionDao.insert(new Question("Mjanma", "Napyidaw", "Azja", "myanmar"));
                    questionDao.insert(new Question("Mongolia", "Ułan Bator", "Azja", "mongolia"));
                    questionDao.insert(new Question("Nepal", "Katmandu", "Azja", "nepal"));
                    questionDao.insert(new Question("Oman", "Maskat", "Azja", "oman"));
                    questionDao.insert(new Question("Pakistan", "Islamabad", "Azja", "pakistan"));
                    questionDao.insert(new Question("Rosja", "Moskwa", "Azja", "russia"));
                    questionDao.insert(new Question("Singapur", "Singapur", "Azja", "singapore"));
                    questionDao.insert(new Question("Sri Lanka", "Kolombo", "Azja", "sri_lanka"));
                    questionDao.insert(new Question("Syria", "Damaszek", "Azja", "syria"));
                    questionDao.insert(new Question("Tadżykistan", "Duszanbe", "Azja", "tajikistan"));
                    questionDao.insert(new Question("Tajlandia", "Bangkok", "Azja", "thailand"));
                    questionDao.insert(new Question("Timor Wschodni", "Dili", "Azja", "timor_leste"));
                    questionDao.insert(new Question("Turcja", "Ankara", "Azja", "turkey"));
                    questionDao.insert(new Question("Turkmenistan", "Aszchabad", "Azja", "turkmenistan"));
                    questionDao.insert(new Question("Uzbekistan", "Taszkent", "Azja", "uzbekistan"));
                    questionDao.insert(new Question("Wietnam", "Hanoi", "Azja", "vietnam"));
                    questionDao.insert(new Question("Zjednoczone Emiraty Arabskie", "Abu Zabi", "Azja", "uae"));

                    // Ameryka Północna
                    questionDao.insert(new Question("Antigua i Barbuda", "Saint John's", "Ameryka Północna", "antigua_and_barbuda"));
                    questionDao.insert(new Question("Bahamy", "Nassau", "Ameryka Północna", "bahamas"));
                    questionDao.insert(new Question("Barbados", "Bridgetown", "Ameryka Północna", "barbados"));
                    questionDao.insert(new Question("Belize", "Belmopan", "Ameryka Północna", "belize"));
                    questionDao.insert(new Question("Dominika", "Roseau", "Ameryka Północna", "dominica"));
                    questionDao.insert(new Question("Dominikana", "Santo Domingo", "Ameryka Północna", "dominican_republic"));
                    questionDao.insert(new Question("Grenada", "Saint George's", "Ameryka Północna", "grenada"));
                    questionDao.insert(new Question("Gwatemala", "Gwatemala", "Ameryka Północna", "guatemala"));
                    questionDao.insert(new Question("Haiti", "Port-au-Prince", "Ameryka Północna", "haiti"));
                    questionDao.insert(new Question("Honduras", "Tegucigalpa", "Ameryka Północna", "honduras"));
                    questionDao.insert(new Question("Jamajka", "Kingston", "Ameryka Północna", "jamaica"));
                    questionDao.insert(new Question("Kanada", "Ottawa", "Ameryka Północna", "canada"));
                    questionDao.insert(new Question("Kostaryka", "San José", "Ameryka Północna", "costa_rica"));
                    questionDao.insert(new Question("Kuba", "Hawana", "Ameryka Północna", "cuba"));
                    questionDao.insert(new Question("Meksyk", "Meksyk", "Ameryka Północna", "mexico"));
                    questionDao.insert(new Question("Nikaragua", "Managua", "Ameryka Północna", "nicaragua"));
                    questionDao.insert(new Question("Panama", "Panama", "Ameryka Północna", "panama"));
                    questionDao.insert(new Question("Saint Kitts i Nevis", "Basseterre", "Ameryka Północna", "saint_kitts_and_nevis"));
                    questionDao.insert(new Question("Saint Lucia", "Castries", "Ameryka Północna", "saint_lucia"));
                    questionDao.insert(new Question("Saint Vincent i Grenadyny", "Kingstown", "Ameryka Północna", "saint_vincent_and_the_grenadines"));
                    questionDao.insert(new Question("Salwador", "San Salvador", "Ameryka Północna", "el_salvador"));
                    questionDao.insert(new Question("Stany Zjednoczone", "Waszyngton", "Ameryka Północna", "united_states"));

                    // Ameryka Południowa
                    questionDao.insert(new Question("Argentyna", "Buenos Aires", "Ameryka Południowa", "argentina"));
                    questionDao.insert(new Question("Boliwia", "Sucre", "Ameryka Południowa", "bolivia"));
                    questionDao.insert(new Question("Brazylia", "Brasília", "Ameryka Południowa", "brazil"));
                    questionDao.insert(new Question("Chile", "Santiago", "Ameryka Południowa", "chile"));
                    questionDao.insert(new Question("Ekwador", "Quito", "Ameryka Południowa", "ecuador"));
                    questionDao.insert(new Question("Gujana", "Georgetown", "Ameryka Południowa", "guyana"));
                    questionDao.insert(new Question("Kolumbia", "Bogota", "Ameryka Południowa", "colombia"));
                    questionDao.insert(new Question("Paragwaj", "Asunción", "Ameryka Południowa", "paraguay"));
                    questionDao.insert(new Question("Peru", "Lima", "Ameryka Południowa", "peru"));
                    questionDao.insert(new Question("Surinam", "Paramaribo", "Ameryka Południowa", "suriname"));
                    questionDao.insert(new Question("Trynidad i Tobago", "Port-of-Spain", "Ameryka Południowa", "trinidad_tobago"));
                    questionDao.insert(new Question("Urugwaj", "Montevideo", "Ameryka Południowa", "uruguay"));
                    questionDao.insert(new Question("Wenezuela", "Caracas", "Ameryka Południowa", "venezuela"));

                    // Afryka
                    questionDao.insert(new Question("Algieria", "Algier", "Afryka", "algeria"));
                    questionDao.insert(new Question("Angola", "Luanda", "Afryka", "angola"));
                    questionDao.insert(new Question("Benin", "Porto-Novo", "Afryka", "benin"));
                    questionDao.insert(new Question("Botswana", "Gaborone", "Afryka", "botswana"));
                    questionDao.insert(new Question("Burkina Faso", "Wagadugu", "Afryka", "burkina_faso"));
                    questionDao.insert(new Question("Burundi", "Gitega", "Afryka", "burundi"));
                    questionDao.insert(new Question("Czad", "Ndżamena", "Afryka", "chad"));
                    questionDao.insert(new Question("Demokratyczna Republika Konga", "Kinszasa", "Afryka", "drc"));
                    questionDao.insert(new Question("Dżibuti", "Dżibuti", "Afryka", "djibouti"));
                    questionDao.insert(new Question("Egipt", "Kair", "Afryka", "egypt"));
                    questionDao.insert(new Question("Erytrea", "Asmara", "Afryka", "eritrea"));
                    questionDao.insert(new Question("Eswatini", "Mbabane", "Afryka", "eswatini"));
                    questionDao.insert(new Question("Etiopia", "Addis Abeba", "Afryka", "ethiopia"));
                    questionDao.insert(new Question("Gabon", "Libreville", "Afryka", "gabon"));
                    questionDao.insert(new Question("Gambia", "Bandżul", "Afryka", "gambia"));
                    questionDao.insert(new Question("Ghana", "Akra", "Afryka", "ghana"));
                    questionDao.insert(new Question("Gwinea", "Konakry", "Afryka", "guinea"));
                    questionDao.insert(new Question("Gwinea Bissau", "Bissau", "Afryka", "guinea_bissau"));
                    questionDao.insert(new Question("Gwinea Równikowa", "Malabo", "Afryka", "equatorial_guinea"));
                    questionDao.insert(new Question("Kamerun", "Jaunde", "Afryka", "cameroon"));
                    questionDao.insert(new Question("Kenia", "Nairobi", "Afryka", "kenya"));
                    questionDao.insert(new Question("Komory", "Moroni", "Afryka", "comoros"));
                    questionDao.insert(new Question("Kongo", "Brazzaville", "Afryka", "congo"));
                    questionDao.insert(new Question("Lesotho", "Maseru", "Afryka", "lesotho"));
                    questionDao.insert(new Question("Liberia", "Monrovia", "Afryka", "liberia"));
                    questionDao.insert(new Question("Libia", "Trypolis", "Afryka", "libya"));
                    questionDao.insert(new Question("Madagaskar", "Antananarywa", "Afryka", "madagascar"));
                    questionDao.insert(new Question("Malawi", "Lilongwe", "Afryka", "malawi"));
                    questionDao.insert(new Question("Mali", "Bamako", "Afryka", "mali"));
                    questionDao.insert(new Question("Maroko", "Rabat", "Afryka", "morocco"));
                    questionDao.insert(new Question("Mauretania", "Nawakszut", "Afryka", "mauritania"));
                    questionDao.insert(new Question("Mauritius", "Port Louis", "Afryka", "mauritius"));
                    questionDao.insert(new Question("Mozambik", "Maputo", "Afryka", "mozambique"));
                    questionDao.insert(new Question("Namibia", "Windhuk", "Afryka", "namibia"));
                    questionDao.insert(new Question("Niger", "Niamey", "Afryka", "niger"));
                    questionDao.insert(new Question("Nigeria", "Abudża", "Afryka", "nigeria"));
                    questionDao.insert(new Question("RPA", "Pretoria", "Afryka", "south_africa"));
                    questionDao.insert(new Question("Republika Środkowoafrykańska", "Bangi", "Afryka", "central_african_republic"));
                    questionDao.insert(new Question("Republika Zielonego Przylądka", "Praia", "Afryka", "cape_verde"));
                    questionDao.insert(new Question("Rwanda", "Kigali", "Afryka", "rwanda"));
                    questionDao.insert(new Question("Senegal", "Dakar", "Afryka", "senegal"));
                    questionDao.insert(new Question("Seszele", "Victoria", "Afryka", "seychelles"));
                    questionDao.insert(new Question("Sierra Leone", "Freetown", "Afryka", "sierra_leone"));
                    questionDao.insert(new Question("Somalia", "Mogadiszu", "Afryka", "somalia"));
                    questionDao.insert(new Question("Sudan", "Chartum", "Afryka", "sudan"));
                    questionDao.insert(new Question("Sudan Południowy", "Dżuba", "Afryka", "south_sudan"));
                    questionDao.insert(new Question("Tanzania", "Dodoma", "Afryka", "tanzania"));
                    questionDao.insert(new Question("Togo", "Lome", "Afryka", "togo"));
                    questionDao.insert(new Question("Tunezja", "Tunis", "Afryka", "tunisia"));
                    questionDao.insert(new Question("Uganda", "Kampala", "Afryka", "uganda"));
                    questionDao.insert(new Question("Wybrzeże Kości Słoniowej", "Jamusukro", "Afryka", "ivory_coast"));
                    questionDao.insert(new Question("Wyspy Świętego Tomasza i Książęca", "São Tomé", "Afryka", "sao_tome_and_principe"));
                    questionDao.insert(new Question("Zambia", "Lusaka", "Afryka", "zambia"));
                    questionDao.insert(new Question("Zimbabwe", "Harare", "Afryka", "zimbabwe"));

                    // Australia i Oceania
                    questionDao.insert(new Question("Australia", "Canberra", "Australia i Oceania", "australia"));
                    questionDao.insert(new Question("Fidżi", "Suva", "Australia i Oceania", "fiji"));
                    questionDao.insert(new Question("Kiribati", "Tarawa", "Australia i Oceania", "kiribati"));
                    questionDao.insert(new Question("Mikronezja", "Palikir", "Australia i Oceania", "micronesia"));
                    questionDao.insert(new Question("Nauru", "Yaren", "Australia i Oceania", "nauru"));
                    questionDao.insert(new Question("Nowa Zelandia", "Wellington", "Australia i Oceania", "new_zealand"));
                    questionDao.insert(new Question("Palau", "Ngerulmud", "Australia i Oceania", "palau"));
                    questionDao.insert(new Question("Papua-Nowa Gwinea", "Port Moresby", "Australia i Oceania", "papua_new_guinea"));
                    questionDao.insert(new Question("Samoa", "Apia", "Australia i Oceania", "samoa"));
                    questionDao.insert(new Question("Tonga", "Nukuʻalofa", "Australia i Oceania", "tonga"));
                    questionDao.insert(new Question("Tuvalu", "Funafuti", "Australia i Oceania", "tuvalu"));
                    questionDao.insert(new Question("Vanuatu", "Port Vila", "Australia i Oceania", "vanuatu"));
                    questionDao.insert(new Question("Wyspy Marshalla", "Majuro", "Australia i Oceania", "marshall_islands"));
                    questionDao.insert(new Question("Wyspy Salomona", "Honiara", "Australia i Oceania", "solomon_islands"));

                }
            }).start();
        }
    };

}
