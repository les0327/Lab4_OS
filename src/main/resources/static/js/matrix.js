
function doScheduling(n) {
    return fetch("/doScheduling/" + n).then(function (value) {
        return value.json();
    });
}

document.addEventListener("DOMContentLoaded", function (event) {
    doScheduling(5).then(function (responce) {
        var pin = document.createElement()
        document.getElementById('in').appendChild(document.createTextNode());
        document.getElementById('out').appendChild(document.createTextNode(responce[1]));
    });
});