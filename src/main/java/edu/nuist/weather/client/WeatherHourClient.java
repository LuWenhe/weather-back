package edu.nuist.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-hour-service", url = "${QWeatherAPI.weatherUrl}")
public interface WeatherHourClient {

    /**
     * 实时天气
     * @param location 需要查询地区的LocationID
     * @param key 和风天气用户认证key
     * @return 进行Gzip压缩后的JSON格式数据
     */
    @GetMapping(value = "/v7/weather/now")
    ResponseEntity<byte[]> nowWeather(@RequestParam("location") String location,
                                      @RequestParam(value = "key") String key);

    /**
     * 未来24小时逐小时预报
     * @param location 需要查询地区的LocationID
     * @param key 和风天气用户认证key
     * @return 进行Gzip压缩后的JSON格式数据
     */
    @GetMapping(value = "/v7/weather/24h")
    ResponseEntity<byte[]> hourlyForecastForTwentyFourHours(@RequestParam("location") String location,
                                                                @RequestParam(value = "key") String key);

}
