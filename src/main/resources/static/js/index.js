var TimeNChart;

function getTimeNPoints() {
    return fetch("/getTimeNPoints").then(function (responce) {
        return responce.json();
    });
}

function formCharts(nTimeCtx) {
    getTimeNPoints().then(function (responce) {
        evChart = new Chart(nTimeCtx, {
            type: 'line',
            data: {
                datasets: [{
                    label: 'Time / N',
                    data: responce,
                    borderColor: "#3e95cd",
                    fill: false
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        id: 'EvTime',
                        type: 'linear',
                        position: 'left'
                    }],
                    xAxes: [{
                        id: 'Intensity',
                        type: 'linear',
                        position: 'bottom'
                    }]
                }
            }
        });
    })
}

document.addEventListener("DOMContentLoaded", function (event) {
    //var TimeNCtx = document.getElementById("TimeNChart").getContext('2d');
    var TimeNCtx = document.getElementById('TimeNChart');
    formCharts(TimeNCtx);
});
