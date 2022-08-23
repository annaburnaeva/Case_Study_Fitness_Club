var table = document.getElementById('table');
if (table) {
    table.addEventListener('mouseover', function (event) {
        event.target.className = "red";
    });
    table.addEventListener('mouseout', function (event) {
        event.target.className = "while";
    });
}

for (var i = 0; i < rows; i++) {
    var tr = document.createElement('tr');
    for (var j = 0; j < cols; j++) {
        var td = document.createElement('td');
        td.className = "while";
        tr.append(td);
    }
    table.append(tr);
}