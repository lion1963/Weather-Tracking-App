var now = new Date();
var todayDate = now.getDate();
var todayMonth = now.getMonth();
var todayYear = now.getFullYear();

var lastWeek = new Date(todayYear, todayMonth, todayDate - 7);
var lastWeekDate = lastWeek.getDate();
var lastWeekMonth = lastWeek.getMonth();
var lastWeekYear = lastWeek.getFullYear();

    $(document).ready(function() {
        $('#d1').datepicker();
        $('#d1').datepicker('update', new Date(lastWeekYear, lastWeekMonth, lastWeekDate));
    });

    $(document).ready(function() {
        $('#d2').datepicker();
        $('#d2').datepicker('update', new Date(todayYear, todayMonth, todayDate));
    });

function getStatistic(){
    var date1 = $('#d1').datepicker('getUTCDate');
    var date2 = $('#d2').datepicker('getUTCDate');
    var timestamp1 = (date1.getTime()/1000);
    var timestamp2 = (date2.getTime()/1000)+86400;

    $.ajax({
            url: "http://localhost:8080/weather/statistic?timestamp1="+timestamp1+"&timestamp2="+timestamp2,
            type: "get"

    }).then(function(data) {
        $('#st1').text(data.avgTemperature);
        $('#st2').text(data.minTemperature);
        $('#st3').text(data.maxTemperature);
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






