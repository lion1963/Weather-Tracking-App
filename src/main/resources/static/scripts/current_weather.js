var currentWeatherURL = "http://localhost:8080/weather/current";

$(document).ready(function() {
    $.ajax({
        url: currentWeatherURL,
        type: "get"
    }).then(function(data) {
       $('#currentWeather_field1').append(data.city);
       $('#currentWeather_field2').append(data.description);
       $('#currentWeather_field3').append(data.temperature).append("°C");
       $('#currentWeather_field4').append(data.humidity).append("%");
       $('#currentWeather_field5').append(data.pressure).append(" Pa");
       var time = new Date();
       time.setTime(data.date*1000)
       var year = time.getFullYear();

       var month = (time.getMonth()+1);
       month = month<10?"0"+month:month;
       var day = time.getDate();
       day = day<10?"0"+day:day;
       var hours = time.getHours();
       hours = hours<10?"0"+hours:hours;
       var minutes = time.getMinutes();
       minutes = minutes<10?"0"+minutes:minutes;

       var dateTime = day+ "/"+ month+"/"+year+" "+hours+":"+minutes;
       $('#currentWeather_field6').append(dateTime);
    });
});