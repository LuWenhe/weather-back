package edu.nuist.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-day-service", url = "${QWeatherAPI.weatherUrl}")
public interface WeatherDayClient {

    /**
     * 未来3天预报
     * @param location 需要查询地区的LocationID
     * @param key 和风天气用户认证key
     * @return 进行Gzip压缩后的JSON格式数据
     */
    @GetMapping(value = "/v7/weather/3d")
    ResponseEntity<byte[]> dailyForecastForThreeDays(@RequestParam("location") String location,
                                                     @RequestParam(value = "key") String key);

    /**
     * 未来7天预报
     * @param location 需要查询地区的LocationID
     * @param key 和风天气用户认证key
     * @return 进行Gzip压缩后的JSON格式数据
     */
    @GetMapping(value = "/v7/weather/7d")
    ResponseEntity<byte[]> dailyForecastForSevenDays(@RequestParam("location") String location,
                                                     @RequestParam(value = "key") String key);

    /**
     * 未来10天预报
     * @param location 需要查询地区的LocationID
     * @param key 和风天气用户认证key
     * @return 进行Gzip压缩后的JSON格式数据
     */
    @GetMapping(value = "/v7/weather/10d")
    ResponseEntity<byte[]> dailyForecastForTenDays(@RequestParam("location") String location,
                                                   @RequestParam(value = "key") String key);

    /**
     * 未来15天预报
     * @param location 需要查询地区的LocationID
     * @param key 和风天气用户认证key
     * @return 进行Gzip压缩后的JSON格式数据
     */
    @GetMapping(value = "/v7/weather/15d")
    ResponseEntity<byte[]> dailyForecastForFifteenDays(@RequestParam("location") String location,
                                                       @RequestParam(value = "key") String key);

}
