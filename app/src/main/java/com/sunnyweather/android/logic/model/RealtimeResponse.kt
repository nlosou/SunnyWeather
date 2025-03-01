package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

public data class RealtimeResponse(
    val status: String,
    val result: Result
) {
    data class Result(
        val realtime: Realtime,
        val hourly: Hourly,
        @SerializedName("daily") val daily: Daily,
        val astro: List<Astro>,
        val primary: Int
    ) {
        data class Realtime(
            val status: String,
            val temperature: Float,
            val humidity: Float,
            val cloudrate: Float,
            val skycon: String,
            val visibility: Float,
            val dswrf: Float,
            val wind: Wind,
            val pressure: Double,
            val apparent_temperature: Float,
            val precipitation: Precipitation,
            val air_quality: AirQuality,
            val life_index: LifeIndex
        ) {
            data class Wind(
                val speed: Float,
                val direction: Float
            )
            data class Precipitation(
                val local: Local,
                val nearest: Nearest
            ) {
                data class Local(
                    val status: String,
                    val datasource: String,
                    val intensity: Float
                )

                data class Nearest(
                    val status: String,
                    val distance: Float,
                    val intensity: Float
                )
            }

            data class AirQuality(
                val pm25: Int,
                val pm10: Int,
                val o3: Int,
                val so2: Int,
                val no2: Int,
                val co: Float,
                val aqi: Aqi,
                val description: Description
            ) {
                data class Aqi(
                    val chn: Int,
                    val usa: Int
                )

                data class Description(
                    val chn: String,
                    val usa: String
                )
            }

            data class LifeIndex(
                val ultraviolet: Ultraviolet,
                val comfort: Comfort
            ) {
                data class Ultraviolet(
                    val index: Int,
                    val desc: String
                )

                data class Comfort(
                    val index: Int,
                    val desc: String
                )
            }
        }

        data class Hourly(
            val status: String,
            val description: String,
            val precipitation: List<Precipitation>,
            val temperature: List<Temperature>,
            val apparent_temperature: List<ApparentTemperature>,
            val wind: List<Wind>,
            val humidity: List<Humidity>,
            val cloudrate: List<Cloudrate>,
            val skycon: List<Skycon>,
            val pressure: List<Pressure>,
            val visibility: List<Visibility>,
            val dswrf: List<Dswrf>,
            val air_quality: AirQuality
        ) {
            data class Precipitation(
                val datetime: String,
                val value: Float,
                val probability: Int
            )

            data class Temperature(
                val datetime: String,
                val value: Float
            )

            data class ApparentTemperature(
                val datetime: String,
                val value: Float
            )

            data class Wind(
                val datetime: String,
                val speed: Float,
                val direction: Float
            )

            data class Humidity(
                val datetime: String,
                val value: Float
            )

            data class Cloudrate(
                val datetime: String,
                val value: Float
            )

            data class Skycon(
                val datetime: String,
                val value: String
            )

            data class Pressure(
                val datetime: String,
                val value: Double
            )

            data class Visibility(
                val datetime: String,
                val value: Float
            )

            data class Dswrf(
                val datetime: String,
                val value: Float
            )

            data class AirQuality(
                val aqi: List<Aqi>,
                val pm25: List<Pm25>
            ) {
                data class Aqi(
                    val datetime: String,
                    val value: AqiValue
                ) {
                    data class AqiValue(
                        val chn: Int,
                        val usa: Int
                    )
                }

                data class Pm25(
                    val datetime: String,
                    val value: Int
                )
            }
        }

        data class Astro(
            val date: String,
            val sunrise: Sunrise,
            val sunset: Sunset
        ) {
            data class Sunrise(
                val time: String
            )

            data class Sunset(
                val time: String
            )
        }

        data class Daily(
            val status: String,
            val astro: List<Astro>,
            val precipitation_08h_20h: List<Metrics>,
            val precipitation_20h_32h: List<Metrics>,
            val temperature: List<Metrics>,
            val temperature_08h_20h: List<Metrics>,
            val temperature_20h_32h: List<Metrics>,
            val wind: List<Wind>,
            val wind_08h_20h: List<HoursWind>,
            val wind_20h_32h: List<HoursWind>,
            val humidity: List<Metrics>,
            val cloudrate: List<Metrics>,
            val pressure: List<Metrics>,
            val visibility: List<Metrics>,
            val dswrf: List<Metrics>,
            val air_quality: AirQuality,
            val skycon: List<Skycon>,
            val skycon_08h_20h: List<Skycon>,
            val skycon_20h_32h: List<Skycon>,
            val life_index: LifeIndex
        ) {
            data class Metrics(
                val date: String,
                val max: Float?,
                val min: Float?,
                val avg: Float?
            )

            data class Wind(
                val date: String,
                val max: WindData,
                val min: WindData,
                val avg: WindData
            ) {
                data class WindData(
                    val speed: Float,
                    val direction: Float
                )
            }

            data class HoursWind(
                val date: String,
                val max: Wind.WindData,
                val min: Wind.WindData,
                val avg: Wind.WindData
            )

            data class AirQuality(
                val aqi: List<Aqi>,
                val pm25: List<Pm25>
            ) {
                data class Aqi(
                    val date: String,
                    val max: AqiValue,
                    val avg: AqiValue,
                    val min: AqiValue
                ) {
                    data class AqiValue(
                        val chn: Int,
                        val usa: Int
                    )
                }

                data class Pm25(
                    val date: String,
                    val max: Int,
                    val avg: Int,
                    val min: Int
                )
            }

            data class Skycon(
                val date: String,
                val value: String
            )

            data class LifeIndex(
                val ultraviolet: List<Ultraviolet>,
                val carWashing: List<CarWashing>,
                val dressing: List<Dressing>,
                val comfort: List<Comfort>,
                val coldRisk: List<ColdRisk>
            ) {
                data class Ultraviolet(
                    val date: String,
                    val index: String,
                    val desc: String
                )

                data class CarWashing(
                    val date: String,
                    val index: String,
                    val desc: String
                )

                data class Dressing(
                    val date: String,
                    val index: String,
                    val desc: String
                )

                data class Comfort(
                    val date: String,
                    val index: String,
                    val desc: String
                )

                data class ColdRisk(
                    val date: String,
                    val index: String,
                    val desc: String
                )
            }
        }
    }
}
