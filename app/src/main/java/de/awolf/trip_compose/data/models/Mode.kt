package de.awolf.trip_compose.data.models

import androidx.compose.ui.graphics.Color

enum class Mode(private val rawValue: String) {
    TRAM("Tram"),
    CITYBUS("CityBus"),
    INTERCITYBUS("IntercityBus"),
    PLUSBUS("PlusBus"),
    SUBURBANRAILWAY("SuburbanRailway"),
    TRAIN("Train"),
    CABLEWAY("Cableway"),
    FERRY("Ferry"),
    HAILEDSHAREDTAXI("HailedSharedTaxi");

    companion object {
        @Suppress("UNUSED")
        fun getAll(): List<Mode> {
            return listOf(
                TRAM,
                CITYBUS,
                INTERCITYBUS,
                PLUSBUS,
                SUBURBANRAILWAY,
                TRAIN,
                CABLEWAY,
                FERRY,
                HAILEDSHAREDTAXI
            )
        }
    }

    @Suppress("UNUSED")
    fun getIconURL(): String {
        var identifier = rawValue
        if (rawValue == "CityBus" || rawValue == "IntercityBus") {
            identifier = "Bus"
        }
        return "https://www.dvb.de/assets/img/trans-icon/transport-" + identifier + ".svg"
    }

    @Suppress("UNUSED")
    fun getColor(): Color {
        return when (rawValue) {
            "Tram" -> Color(0xffDB0031) //tram red
            "CityBus" -> Color(0xff005D75) //bus blue
            "IntercityBus" -> Color(0xff005D75) //bus blue
            "PlusBus" -> Color(0xffA3177E) //plusbus magenta
            "SuburbanRailway" -> Color(0xff00914D) //sbahn green
            "Train" -> Color(0xff00914D) //train green
            "Ferry" -> Color(0xff00A5DA) //ferry lightblue
            "HailedSharedTaxi" -> Color(0xffFFEC01) //alita taxi yellow
            else -> Color(0xff888888) //gray
        }
    }
}