var now = new Date();
var todayDate = now.getDate();
var todayMonth = now.getMonth();
var todayYear = now.getFullYear();

var lastWeek = new Date(todayYear, todayMonth, todayDate - 7);
var lastWeekDate = lastWeek.getDate();
var lastWeekMonth = lastWeek.getMonth();
var lastWeekYear = lastWeek.getFullYear();

var statisticURL = "http://localhost:8080/weather/statistic";

    $(document).ready(function() {
        $('#datepicker1').datepicker();
        $('#datepicker1').datepicker('update', new Date(lastWeekYear, lastWeekMonth, lastWeekDate));
    });

    $(document).ready(function() {
        $('#datepicker2').datepicker();
        $('#datepicker2').datepicker('update', new Date(todayYear, todayMonth, todayDate));
    });

function getStatistic(){
    var date1 = $('#datepicker1').datepicker('getUTCDate');
    var date2 = $('#datepicker2').datepicker('getUTCDate');
    var timestamp1 = (date1.getTime()/1000);
    var timestamp2 = (date2.getTime()/1000)+86400;

    $.ajax({
            url: statisticURL+"?timestamp1="+timestamp1+"&timestamp2="+timestamp2,
            type: "get"

    }).then(function(data) {
        $('#statistic_field1').text(data.avgTemperature + "°C");
        $('#statistic_field2').text(data.minTemperature + "°C");
        $('#statistic_field3').text(data.maxTemperature + "°C");
    });
};

    $(document).ready(function() {
            getStatistic();
        });

    $(document).ready(function() {
            $('#btn').click(function(){
            getStatistic();
            });
            }
        );






