$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/weather/current",
        type: "get"
    }).then(function(data) {
       $('#cw1').append(data.city);
       $('#cw2').append(data.description);
       $('#cw3').append(data.temperature).append("°C");
       $('#cw4').append(data.humidity).append("%");
       $('#cw5').append(data.pressure).append(" Pa");
       var time = new Date();
       time.setTime(data.date*1000)
       var year = time.getFullYear();
       var month = "0"+ (time.getMonth()+1);
       var day = "0"+time.getDate();
       var hours = time.getHours();
       hours = hours<10?"0"+hours:hours;
       var minutes = "0"+time.getMinutes();
       var dateTime = day.substr(-2)+ "/"+ month.substr(-2)+"/"+year+" "+hours.substr(-2)+":"+minutes.substr(-2);
       $('#cw6').append(dateTime);
    });
});